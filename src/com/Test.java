package com;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        
//        SerialPortWrite write = new SerialPortWrite();
        
        File file = new File("E:/�½� �ı��ĵ�.txt");
        if (file.exists()) {
            System.out.println(file.getPath());
            file.renameTo(new File("E:/ss.txt"));
            System.out.println(file.getPath());
        }else {
            System.out.println("�ļ������ڣ�");
        }
    }
}
