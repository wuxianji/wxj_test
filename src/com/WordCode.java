package com;

public class WordCode {


    public static void main(String[] args) {
        String str = "ƿ";
        byte[] b = str.getBytes();
        for (int i = 0, max = b.length; i < max; i++) {
            System.out.print(Integer.toHexString(b[i] & 0xff));
        }
    }

}
