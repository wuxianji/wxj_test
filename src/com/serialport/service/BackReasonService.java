package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * �˲���������
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
            
            System.out.println("�˲����ɱ�ţ�" + reasonCode + " �˲��������ƣ�" + reasonName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
