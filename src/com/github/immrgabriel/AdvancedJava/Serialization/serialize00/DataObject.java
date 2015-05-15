package com.github.immrgabriel.AdvancedJava.Serialization.serialize00;

import java.io.Serializable;
import java.util.Arrays;

public class DataObject extends NonSerializable implements Serializable{
    private static final long serialVersionUID = 1L;

    int i = 2015;
    String s = "year";
    transient String[] def = {"day", "of", "the", "year"};
    CustomObject object = new CustomObject();

    @Override
    public String toString() {
        return "DataObject{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", def=" + Arrays.toString(def) +
                ", object.b=" + object.b +
                "} " + super.toString();
    }
}
