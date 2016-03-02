package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ����Ϣ������
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
            
            System.out.println("����Ϣ��ţ�" + groupCode + " ����Ϣ���ƣ�" + groupName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
