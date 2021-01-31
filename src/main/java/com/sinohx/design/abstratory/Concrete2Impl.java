package com.sinohx.design.abstratory;

public class Concrete2Impl implements IEncodeDecode{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String Encoder(String strData) {
        return "Concrete2Impl";
    }
}
