package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * �ײͱ�����
 * @author Administrator
 *
 */
public class GroupService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String groupCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String groupName = new String(BaseService.hexStringToBytes(message.substring(24, 64)));
            
            System.out.println("�ײͱ�ţ�" + groupCode + " �ײ����ƣ�" + groupName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
