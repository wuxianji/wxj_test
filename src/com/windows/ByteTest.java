package com.windows;

import java.nio.charset.Charset;

public class ByteTest {

    public static void main(String[] args) {
        //[48, 95, 13, 0]
        //[52, 52, 53, 56, 51, 49, 50, 0]
        //[48, 0]
       byte[] b = new byte[]{48};
       
       System.out.println(new String(b, Charset.forName("gbk")));
    }

}
