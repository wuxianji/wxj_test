package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 下载菜品类别
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
            
            System.out.println("类别编号：" + cateCode + " 类别名称：" + cateName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
