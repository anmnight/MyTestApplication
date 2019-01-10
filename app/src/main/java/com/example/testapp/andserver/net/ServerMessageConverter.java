package com.example.testapp.andserver.net;

import com.anmnight.commlibrary.unit.JsonUnit;
import com.yanzhenjie.andserver.annotation.Converter;
import com.yanzhenjie.andserver.framework.MessageConverter;
import com.yanzhenjie.andserver.http.ResponseBody;
import com.yanzhenjie.andserver.util.IOUtils;
import com.yanzhenjie.andserver.util.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Converter
public class ServerMessageConverter implements MessageConverter {


    @Override
    public ResponseBody convert(@NonNull Object output, @Nullable MediaType mediaType) {

        String data = JsonUnit.INSTANCE.objToString(output);

        return new JsonResponseBody(ResponseJsonUnit.INSTANCE.successJson(data));
    }

    @Nullable
    @Override
    public <T> T convert(@NonNull InputStream stream, @Nullable MediaType mediaType, Type type) throws IOException {

        Charset charset = mediaType == null ? null : mediaType.getCharset();

        if (charset == null) {

            return JsonUnit.INSTANCE.stringToObject(IOUtils.toString(stream), type);
        }

        return JsonUnit.INSTANCE.stringToObject(IOUtils.toString(stream, charset), type);

    }
}
