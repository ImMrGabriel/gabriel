package com.github.immrgabriel.AdvancedJava.Serialization.serialize00;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class DataObjectRW extends NonSerializable implements Serializable{
    private static final long serialVersionUID = 2L;

    int i = 2015;
    String s = "year";
    transient String[] def = {"day", "of", "the", "year"};
    CustomObject object = new CustomObject();

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(getMyData());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setMyData((String) in.readObject());
    }

    @Override
    public String toString() {
        return "DataObjectRW{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", def=" + Arrays.toString(def) +
                ", object.b=" + object.b +
                "} " + super.toString();
    }
}
