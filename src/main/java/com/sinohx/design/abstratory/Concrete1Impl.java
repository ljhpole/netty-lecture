package com.sinohx.design.abstratory;

public class Concrete1Impl implements IEncodeDecode{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String Encoder(String strData) {
        return "Concrete1Impl";
    }
}
