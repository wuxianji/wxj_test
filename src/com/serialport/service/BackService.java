package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * �˲�
 * @author Administrator
 *
 */
public class BackService {

    public static void back() {
        //������ + �����
        String pageData = "01" + "01";
        //7���ֽڣ�������油�ո�
        String tableNum = "31303120202020";
        //��Ʒ��ţ�5���ֽڣ�����ǰ�油0x30
        String menuCode = "3030313031";
        //�ո�
        String blank = "2020202020";
        //��Ʒ������7���ֽڣ�������油�ո�
        String number = "31202020202020";
        //ֻ��
        String few = "20202020";
        //�˲�ԭ��,10���ֽڡ�û�в��ո�
        String remark = "20202020202020202020";
        //�����ź�7λ
        String factoryNumber = "33323235303832";
        //��ˮ��
        String orderNum = "30303a30373a3234";
        
        String enableData = pageData + tableNum + menuCode + blank + number + few + remark + factoryNumber + orderNum;
        
        //��Ч�ֽ���  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //У���
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.FOOD_BACK + hexPageNum + enableData + check + Configure.DATA_END;
        
        SerialPortWrite.write(data);
    }
    
    public static void result(String message) {
        String result = message.substring(0, message.length()- 4).substring(16);
        System.out.println("�˲�:" + new String(BaseService.hexStringToBytes(result)));
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
    }
}
