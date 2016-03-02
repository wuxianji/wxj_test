package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 下载推荐表
 * @author Administrator
 *
 */
public class RecommendedService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String groupCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String groupName = new String(BaseService.hexStringToBytes(message.substring(24, 64)));
            
            System.out.println("推荐菜品编号：" + groupCode + " 推荐菜品名称：" + groupName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
