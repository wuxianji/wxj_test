package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * �ײ���������
 * @author Administrator
 *
 */
public class GroupContentService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String groupCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String menuCode = new String(BaseService.hexStringToBytes(message.substring(24, 34)));
            String number = new String(BaseService.hexStringToBytes(message.substring(34, 52)));
            String price = new String(BaseService.hexStringToBytes(message.substring(52, 70)));
            String unit = new String(BaseService.hexStringToBytes(message.substring(70, 78)));
            String defaultStatus = new String(BaseService.hexStringToBytes(message.substring(78, 80)));
            String GroupCode = new String(BaseService.hexStringToBytes(message.substring(80, 84)));
            
            System.out.println("�ײͱ�ţ�" + groupCode + " ��Ʒ��ţ�" + menuCode
                               + " ������" + number + " ���ۣ�" + price
                               + " ��λ��" + unit + " ȱʡ��" + defaultStatus
                               + " �ײ���ţ�" + GroupCode);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
