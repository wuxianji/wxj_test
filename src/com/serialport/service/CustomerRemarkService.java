package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ���ؿͻ�Ҫ��
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
            
            System.out.println("ע�ͱ�ţ�" + remarkCode + " ע�����ƣ�" + remarkName
                                + " �����룺" + helpCode);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
