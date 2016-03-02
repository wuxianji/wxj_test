package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ��̨
 * @author Administrator
 *
 */
public class FoundService {

    public static boolean IS_FOUND = false;
    
    public static void found() {
        //������ + �����
        String pageData = "01" + "01";
        //̨��101(4���ֽڣ��������ո�(20))
        String tableNum = "31303120";
        //����(2���ֽڣ��������ո�(20))
        String peopleNum = "0820";
        //����Ա��1001(5���ֽڣ��������ո�(20))
        String workNum = "3130303120";
        //�����ź�7λ
        String factoryNumber = "33323235303832";
        //��ˮ��(��λ��ÿ��+1)
        String orderNum = "30303a30363a3532";
        
        String enableData = pageData + tableNum + peopleNum + workNum + factoryNumber + orderNum;
        
        //��Ч�ֽ���  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //У��ͼ�1
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.FOUND + hexPageNum + enableData + check + Configure.DATA_END;
        
        SerialPortWrite.write(data);
    }
    
    public static void result(String message) {
        String result = message.substring(0, message.length()- 4).substring(16);
        System.out.println("��̨:" + new String(BaseService.hexStringToBytes(result)));
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
        
        IS_FOUND = true;
    }
}
