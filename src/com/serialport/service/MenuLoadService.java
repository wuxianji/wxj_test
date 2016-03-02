package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ��Ʒ����
 * @author Administrator
 *
 */
public class MenuLoadService {

    public static void load(String message) {
        String data = Configure.MENU_LOAD + Configure.DATA_END;
        SerialPortWrite.write(data);
    }
    
    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String menuCode = message.substring(20, 30);
            String cateCode = message.substring(30, 34);
            String menuName = message.substring(34, 74);
            String price = message.substring(74, 92);
            String menuUnit = message.substring(92, 100);
            String isRepeat = message.substring(108, 110);
            String remark = message.substring(110, 200);
            String helpCode = message.substring(200, 220);
            
            System.out.println("��Ʒ���룺" + new String(BaseService.hexStringToBytes(menuCode)) 
                                + " �����룺" + new String(BaseService.hexStringToBytes(cateCode)) 
                                + " ��Ʒ���ƣ�" + new String(BaseService.hexStringToBytes(menuName)) 
                                + " ��Ʒ���ۣ�" + new String(BaseService.hexStringToBytes(price))
                                + " ��Ʒ��λ��" + new String(BaseService.hexStringToBytes(menuUnit))
                                + " ȷ���ظ���" + new String(BaseService.hexStringToBytes(isRepeat))
                                + " ע�ͣ�" + new String(BaseService.hexStringToBytes(remark)) 
                                + " �����룺" + new String(BaseService.hexStringToBytes(helpCode)));
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
    
    public static void main(String[] args) {
        String ss = "cfe3c0b1c5a3c8e2b7b920202020202020202020";
        System.out.println(new String(BaseService.hexStringToBytes(ss)));
    }
}
