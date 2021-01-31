package com.sinohx.design.abstratory;

public class AppTest {

    IEncodeDecode  iEncodeDecode;
    public AppTest(String strTemp){
        iEncodeDecode = new encodeDecodeFactoryImpl().GetEncoderDecoder();
    }
    public  String intTest(){
        String temp = iEncodeDecode.Encoder("lijinghui");
        return temp;
    }
}
