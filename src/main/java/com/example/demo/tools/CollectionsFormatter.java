package com.example.demo.tools;

import java.util.Collection;
import java.util.stream.Collectors;

public class CollectionsFormatter {
    public static String toStringList(Collection<?> c) {
        String d = "\n############### NEXT OBJECT #################\n";
        String s = d + c.stream().map(Object::toString).collect(Collectors.joining(d));
        return String.format("\n%s\n\n", s);
    }
}
