package com.serialport;

import java.io.IOException;
import java.io.OutputStream;

public class SerialPortWrite {   
    
    public static void write(String message) {
        OutputStream outputStream = null;
        
        try {
            outputStream = SerialPortRead.serialPort.getOutputStream();
           
            System.out.println("发送数据：" + message);
            outputStream.write(hexStringToBytes(message));
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    //获得16进制字byte数组
    private static byte[] hexStringToBytes(String hexString) {       
        
        int length = hexString.length() / 2;       
        char[] hexChars = hexString.toCharArray();       
        byte[] d = new byte[length];       
        for (int i = 0; i < length; i++) {           
            int pos = i * 2;           
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));       
        }   
        return d;   
   }    
    
   private static byte charToByte(char c) {
       return (byte) "0123456789abcdef".indexOf(c);
   }
    
}
