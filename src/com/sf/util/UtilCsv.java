package com.sf.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class UtilCsv {

    private  final char DEFAULT_SEPARATOR = ',';

    public List<String> parseLine(String cvsLine) {
        return this.parseLine(cvsLine, DEFAULT_SEPARATOR);
    }

    public List<String> parseLine(String cvsLine, char separators) {
        List<String> result = new ArrayList<>();
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        AtomicReference<StringBuffer> currentValue = new AtomicReference<>(new StringBuffer());
        char finalSeparators = separators;

        cvsLine.chars().forEach(ch -> {
            if (ch == finalSeparators) {
                result.add(currentValue.toString());
                currentValue.set(new StringBuffer());
            } else if ((ch == '\r') || (ch == '\n')) {
            } else {
                currentValue.get().append(ch);
            }});

        result.add(currentValue.toString());
        return result;
    }

}
