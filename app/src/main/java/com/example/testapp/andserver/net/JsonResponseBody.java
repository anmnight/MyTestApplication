package com.example.testapp.andserver.net;

import com.yanzhenjie.andserver.http.ResponseBody;
import com.yanzhenjie.andserver.util.MediaType;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;

import androidx.annotation.NonNull;

public class JsonResponseBody implements ResponseBody {


    public JsonResponseBody(String body) {
        this.mBody = body.getBytes();
    }

    private byte[] mBody;

    @Override
    public long contentLength() {
        return mBody.length;
    }

    @Override
    public MediaType contentType() {
        return MediaType.APPLICATION_JSON_UTF8;
    }

    @Override
    public void writeTo(@NonNull OutputStream output) throws IOException {
        IOUtils.write(mBody, output);
    }



}
