package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 短信息表下载
 * @author Administrator
 *
 */
public class MessageService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String groupCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String groupName = new String(BaseService.hexStringToBytes(message.substring(24, 64)));
            
            System.out.println("短信息编号：" + groupCode + " 短信息名称：" + groupName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
