package com.example.testapp;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class TestLog {

    public static void ptl(String message) {
        System.out.println(message);
    }

    public static void err(String message) {
        System.err.println(message);
    }


    @Test
    public void test() {

        String message = "011185123";
        String result = encode(message);
        System.out.println(result);

        String decodeResult = decode(result);
        System.out.println(decodeResult);


    }


    private String encode(String message) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + 49);
        }
        return new String(chars);
    }

    private String decode(String message) {
        char[] chars = message.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - 49);
        }
        StringBuilder buffer = new StringBuilder(new String(chars));
        buffer.insert(3, "$");
        return buffer.toString();

    }

}
