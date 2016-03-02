package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 包房下载
 * @author Administrator
 *
 */
public class RoomService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String tableCode = new String(BaseService.hexStringToBytes(message.substring(20, 28)));
            String tableName = new String(BaseService.hexStringToBytes(message.substring(28, 48)));
            
            System.out.println("台号：" + tableCode + " 台桌名称：" + tableName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
