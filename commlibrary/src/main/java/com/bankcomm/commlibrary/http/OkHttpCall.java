/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bankcomm.commlibrary.http;


import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

import static com.bankcomm.commlibrary.http.Utils.throwIfFatal;

/**
 * An invocation of a Retrofit method that sends a request to a webserver and returns a response.
 * Each call yields its own HTTP request and response pair. Use {@link #clone} to make multiple
 * calls with the same parameters to the same webserver; this may be used to implement polling or
 * to retry a failed call.
 *
 * @param <T> Successful response body type.
 */
final class OkHttpCall<T> implements Call<T> {

    private final okhttp3.Call rawCall;

    private volatile boolean canceled;
    private boolean executed;
    private @Nullable
    Throwable creationFailure;

    OkHttpCall(
            okhttp3.Call rawCall) {

        this.rawCall = rawCall;

    }


    @Override
    public Response<T> execute() throws IOException {

        synchronized (this) {
            if (executed) throw new IllegalStateException("Already executed.");
            executed = true;

            if (creationFailure != null) {
                if (creationFailure instanceof IOException) {
                    throw (IOException) creationFailure;
                } else if (creationFailure instanceof RuntimeException) {
                    throw (RuntimeException) creationFailure;
                } else {
                    throw (Error) creationFailure;
                }
            }
        }

        if (canceled) {
            rawCall.cancel();
        }

        return parseResponse(rawCall.execute());
    }

    @Override
    public void cancel() {
        canceled = true;

        okhttp3.Call call;
        synchronized (this) {
            call = rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    public boolean isCanceled() {
        if (canceled) {
            return true;
        }
        synchronized (this) {
            return rawCall != null && rawCall.isCanceled();
        }
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Call<T> clone() {
        return new OkHttpCall<>(rawCall);
    }

    @Override
    public Request request() {

        okhttp3.Call call = rawCall;
        if (call != null) {
            return call.request();
        }
        if (creationFailure != null) {
            if (creationFailure instanceof IOException) {
                throw new RuntimeException("Unable to create request.", creationFailure);
            } else if (creationFailure instanceof RuntimeException) {
                throw (RuntimeException) creationFailure;
            } else {
                throw (Error) creationFailure;
            }
        }
        try {
            return rawCall.request();
        } catch (RuntimeException | Error e) {
            throwIfFatal(e); // Do not assign a fatal error to creationFailure.
            creationFailure = e;
            throw e;
        }
    }


    /**
     * pares okhttp3.Response to Rest.Response
     *
     * @param rawResponse
     * @return
     * @throws IOException
     */
    Response<T> parseResponse(okhttp3.Response rawResponse) throws IOException {
        ResponseBody rawBody = rawResponse.body();

        // Remove the body's source (the only stateful object) so we can pass the response along.
        rawResponse = rawResponse.newBuilder()
                .body(new NoContentResponseBody(rawBody.contentType(), rawBody.contentLength()))
                .build();

        int code = rawResponse.code();
        if (code < 200 || code >= 300) {
            try {
                // Buffer the entire body to avoid future I/O.
                ResponseBody bufferedBody = Utils.buffer(rawBody);
                return Response.error(bufferedBody, rawResponse);
            } finally {
                rawBody.close();
            }
        }

        if (code == 204 || code == 205) {
            rawBody.close();
            return Response.success(null, rawResponse);
        }


        Type jsonType = new TypeToken<T>() {
        }.getType();

        T body = new Gson().fromJson(rawBody.string(), jsonType);


        return Response.success(body, rawResponse);
    }

    static final class NoContentResponseBody extends ResponseBody {
        private final MediaType contentType;
        private final long contentLength;

        NoContentResponseBody(MediaType contentType, long contentLength) {
            this.contentType = contentType;
            this.contentLength = contentLength;
        }

        @Override
        public MediaType contentType() {
            return contentType;
        }

        @Override
        public long contentLength() {
            return contentLength;
        }

        @Override
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;
        IOException thrownException;

        ExceptionCatchingResponseBody(ResponseBody delegate) {
            this.delegate = delegate;
        }

        @Override
        public MediaType contentType() {
            return delegate.contentType();
        }

        @Override
        public long contentLength() {
            return delegate.contentLength();
        }

        @Override
        public BufferedSource source() {
            return Okio.buffer(new ForwardingSource(delegate.source()) {
                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    try {
                        return super.read(sink, byteCount);
                    } catch (IOException e) {
                        thrownException = e;
                        throw e;
                    }
                }
            });
        }

        @Override
        public void close() {
            delegate.close();
        }

        void throwIfCaught() throws IOException {
            if (thrownException != null) {
                throw thrownException;
            }
        }
    }
}
