package com.sinohx.design.abstratory;

public class encodeDecodeFactoryImpl implements encodeDecodeFactory {

    @Override
    public IEncodeDecode GetEncoderDecoder() {
        return new Concrete2Impl();
    }
}
