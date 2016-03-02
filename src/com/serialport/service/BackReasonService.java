package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 退菜理由下载
 * @author Administrator
 *
 */
public class BackReasonService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String reasonCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String reasonName = new String(BaseService.hexStringToBytes(message.substring(24, 64)));
            
            System.out.println("退菜理由编号：" + reasonCode + " 退菜理由名称：" + reasonName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
