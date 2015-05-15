package com.github.immrgabriel.AdvancedJava.Serialization.serialize00;

import java.io.*;

public class Operations {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataObject object = new DataObject();
        object.setMyData("secret data of world!");
        object.object.b = false;
        System.out.println("<< " + object);

        DataObjectRW objectRW = new DataObjectRW();
        objectRW.setMyData("secret data of world!");
        objectRW.object.b = false;
        System.out.println("<< " + objectRW);


        File file = new File("src/" + Operations.class.getPackage().getName().replaceAll("[.]", "////") + "/store.bin");

        try (FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream so = new ObjectOutputStream(fo)) {
            so.writeObject(object);
            so.writeObject(objectRW);
            so.flush();
        }

        DataObject readObject;
        DataObjectRW readObjectRW;
        try(FileInputStream fi = new FileInputStream(file);
            ObjectInputStream si = new ObjectInputStream(fi)) {
            readObject = (DataObject) si.readObject();
            readObjectRW = (DataObjectRW) si.readObject();
        }

        if(readObject != null) {
            System.out.println(">> " + readObject);
        }
        if(readObjectRW != null) {
            System.out.println(">> " + readObjectRW);
        }

    }
}
