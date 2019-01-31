package com.anmnight.localserver.manager;

public class FormatUnit {

    public static int ConverterInt(String ss) {
        try {
            return Integer.valueOf(ss);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Float ConverterFloat(String ss) {
        try {
            return Float.valueOf(ss);
        } catch (Exception e) {
            return 0F;
        }
    }


    public static Long ConverterLong(String ss) {
        try {
            return Long.valueOf(ss);
        } catch (Exception e) {
            return 0L;
        }
    }
}
