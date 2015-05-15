package com.github.immrgabriel.serializeBase64;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestSerialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = getByteArrayOutputStream();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(new String(baos.toByteArray()));
        System.out.println("-----------------------------------------------------------------------------------------");

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bais);

        Map<Product, Integer> map = (Map<Product, Integer>) in.readObject();

        System.out.println(map);

    }

    private static ByteArrayOutputStream getByteArrayOutputStream() throws IOException {
        Map<Product, Integer> map = new ConcurrentHashMap<>();
        map.put(new Product(1, "first"), 3);
        map.put(new Product(22, "twice"), 3);
        map.put(new Product(333, "bla bla 12390 привет мёд!"), 3);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);

        out.writeObject(map);
        return baos;
    }
}
