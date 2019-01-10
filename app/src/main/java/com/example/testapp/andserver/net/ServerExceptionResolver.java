package com.example.testapp.andserver.net;

import com.yanzhenjie.andserver.annotation.Resolver;
import com.yanzhenjie.andserver.error.BasicException;
import com.yanzhenjie.andserver.framework.ExceptionResolver;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.HttpResponse;
import com.yanzhenjie.andserver.util.StatusCode;
import androidx.annotation.NonNull;

@Resolver
public class ServerExceptionResolver implements ExceptionResolver {
    @Override
    public void onResolve(@NonNull HttpRequest request, @NonNull HttpResponse response, @NonNull Throwable e) {

        e.printStackTrace();
        if (e instanceof BasicException) {
            BasicException exception = (BasicException) e;
            response.setStatus(exception.getStatusCode());
        } else {
            response.setStatus(StatusCode.SC_INTERNAL_SERVER_ERROR);
        }

        String body = ResponseJsonUnit.INSTANCE.failJson(e.getMessage());
        response.setBody(new JsonResponseBody(body));
    }
}
