package com.sf.util;

import java.util.Random;

public class UtilCls {

    public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static int random6Digits() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 100000 + r.nextInt(100000));
    }

}
