package com.netcracker.edu.fapi.util;

import java.util.Base64;

public class Converter {
    public static String convertByteArrayToBase64(byte[] array) {
        if (array != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(array);
        }
        return null;
    }

    public static byte[] convertBase64ToByteArray(String base64) {
        if (base64 != null) {
            Base64.Decoder encoder = Base64.getDecoder();
            return encoder.decode(base64);
        }
        return null;
    }

}
