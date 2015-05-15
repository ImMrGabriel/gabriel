package com.github.immrgabriel.AdvancedJava.Serialization.serialize00;

public class NonSerializable {
    String myData = "nothing";

    public String getMyData() {
        return myData;
    }

    public void setMyData(String myData) {
        this.myData = myData;
    }

    @Override
    public String toString() {
        return "NonSerializable{" +
                "myData='" + myData + '\'' +
                '}';
    }
}
