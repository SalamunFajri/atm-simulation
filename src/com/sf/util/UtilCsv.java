package com.sf.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class UtilCsv {

    private  final char DEFAULT_SEPARATOR = ',';

    public List<String> parseLine(String cvsLine) {
        return this.parseLine(cvsLine, DEFAULT_SEPARATOR);
    }

    public List<String> parseLine(String line, char separators) {
        List<String> result = new ArrayList<>();
        if (line == null && line.isEmpty()) {
            return result;
        }
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        AtomicReference<StringBuffer> currentValue = new AtomicReference<>(new StringBuffer());
        char finalSeparators = separators;

        line.chars().forEach(ch -> {
            if ((char)ch == finalSeparators) {
                result.add(currentValue.toString());
                currentValue.set(new StringBuffer());
            } else if (((char)ch == '\r') || ((char)ch == '\n')) {
                return;
            } else {
                currentValue.get().append((char)ch);
            }});

        result.add(currentValue.toString());
        return result;
    }

}
