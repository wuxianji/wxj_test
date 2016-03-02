package com.serialport.service;


public class BaseService{

   //获取校验位
    public static String getCheckDigit(String value) {
        
        char[] hexChars = value.toCharArray();       
        
        int sum = 0;
        for (int i = 0; i < value.length() / 2; i++) {           
            int pos = i * 2;           
            sum += (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
        }   
        sum = sum & 255;
        sum = sum > 127 ? (sum + 129) & 255 : sum + 1; //超过7f加81，没超过加1
        
        String hexString = Integer.toHexString(sum);
        
        return (sum < 17) ? ("0" + hexString) : hexString;
    }
    
    private static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }
    
    public static byte[] hexStringToBytes(String hexString) {       
        
        int length = hexString.length() / 2;       
        char[] hexChars = hexString.toCharArray();       
        byte[] d = new byte[length];       
        for (int i = 0; i < length; i++) {           
            int pos = i * 2;           
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));       
        }   
        return d;   
   }    
}
