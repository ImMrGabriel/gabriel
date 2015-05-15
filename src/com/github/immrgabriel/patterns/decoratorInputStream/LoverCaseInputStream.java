package com.github.immrgabriel.patterns.decoratorInputStream;

import com.sun.istack.internal.NotNull;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoverCaseInputStream extends FilterInputStream{

    public LoverCaseInputStream(@NotNull InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        return (b == -1) ? b : Character.toLowerCase((char) b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
