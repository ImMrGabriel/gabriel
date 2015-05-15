package com.github.immrgabriel.patterns.decoratorInputStream.test;

import com.github.immrgabriel.patterns.decoratorInputStream.LoverCaseInputStream;

import java.io.*;

public class InputTest {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write("I know the Decorator Pattern therefore I RULE!".getBytes("UTF-8"));
        try(InputStream in = new LoverCaseInputStream(
                                new BufferedInputStream(
                                    new ByteArrayInputStream(out.toByteArray())))) {
            int c;
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
        }
    }
}
