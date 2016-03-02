package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 下载客户要求
 * @author Administrator
 *
 */
public class CustomerRemarkService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String remarkCode = new String(BaseService.hexStringToBytes(message.substring(20, 26)));
            String remarkName = new String(BaseService.hexStringToBytes(message.substring(26, 58)));
            String helpCode = new String(BaseService.hexStringToBytes(message.substring(58, 66)));
            
            System.out.println("注释编号：" + remarkCode + " 注释名称：" + remarkName
                                + " 助记码：" + helpCode);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
