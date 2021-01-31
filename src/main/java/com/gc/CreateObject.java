package com.gc;

public class CreateObject {
    public static byte  icount = 0;
    public static byte[] GetObject(int num){
        byte[] bytes = new byte[num* 1024 * 1024];
        bytes[0] = icount++;
        bytes[1] = icount++;
        return bytes;
    }
}
