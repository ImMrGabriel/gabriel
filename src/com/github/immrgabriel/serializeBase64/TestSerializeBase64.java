package com.github.immrgabriel.serializeBase64;

import java.io.*;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestSerializeBase64 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String string64 = getString64();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(string64);
        System.out.println("-----------------------------------------------------------------------------------------");

        byte[] bytes = Base64.getDecoder().decode(string64);

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);

        Map<Product, Integer> map = (Map<Product, Integer>) in.readObject();

        System.out.println(map);

    }

    private static String getString64() throws IOException {
        Map<Product, Integer> map = new ConcurrentHashMap<>();
        map.put(new Product(1, "first"), 3);
        map.put(new Product(22, "twice"), 3);
        map.put(new Product(333, "bla bla 12390 привет мёд!"), 3);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);

        out.writeObject(map);

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
