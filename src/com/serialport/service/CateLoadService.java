package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ���ز�Ʒ���
 * @author Administrator
 *
 */
public class CateLoadService {

    public static void load(String message) {
        
    }
    
    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String cateCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String cateName = new String(BaseService.hexStringToBytes(message.substring(24, 44)));
            
            System.out.println("����ţ�" + cateCode + " ������ƣ�" + cateName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
