package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ���
 * @author Administrator
 *
 */
public class OrderService {

    public static void upload() {
        
        String data = baseData();
        
        SerialPortWrite.write(data);
    }
    
    private static String baseData() {
        //������ + �����
        String pageData = "02" + "01";
        //̨��101(7���ֽڣ��������ո�(20))
        String tableNum = "31303120202020";
        //�������(ǰ4λ)
        String factoryNumberBefor = "30303033";
        String blank = "2020202020";
        //����ע�ͣ�ÿ��ע��3����ţ����ɷ�4��ע��
        String remark1 = "202020";
        String remark2 = "202020";
        String remark3 = "202020";
        String remark4 = "202020";
        
        String blank2 = "202020";
        //��ˮ��
        String orderNum = "010103";
        //�������(��6λ)
        String factoryNumberAfter = "323235303832";
        
        String enableData = pageData + tableNum + factoryNumberBefor + blank + remark1 + remark2 + remark3 + remark4 + blank2 + orderNum + factoryNumberAfter;
        
        //��Ч�ֽ���  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //У���
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.ORDER + hexPageNum + enableData + check + Configure.DATA_END;
        
        return data;
    }
    
    private static String menus() {
        //������ + �����
        String pageData = "02" + "02";
        //̨��101(7���ֽڣ��������ո�(20))
        String tableNum = "31303120202020";
        //��Ʒ��ţ�5���ֽڣ�����ǰ�油0x30
        String menuCode = "3030313031";
        //��Ʒ����
        String number = "20202032";
        
        //ע�ͱ��,û��Ϊ�ո�
        String remark1 = "202020";
        String remark2 = "202020";
        String remark3 = "202020";
        String remark4 = "202020";
        
        //�ײͱ�ţ�û��Ϊ�ո�
        String groupNum = "2020";
        //�Ƚб�־������Ϊ0x30���Ƚ�Ϊ0x31
        String waitStatus = "30";
        //��ˮ��
        String orderNum = "010102";
        //ֻ��
        String few = "20202020";
        //������
        String people = "0220";
        
        String enableData = pageData + tableNum + menuCode + number 
                            + remark1 + remark2 +  remark3 + remark4 
                            + groupNum + waitStatus + orderNum + few + people;
        
      //��Ч�ֽ���  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //У���
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.ORDER + hexPageNum + enableData + check + Configure.DATA_END;
        
        return data;
    }
    
    public static void result(String message) {
        if (message.equals(Configure.LOAD_SUCCESS)) {
            SerialPortWrite.write(menus());
        }else if (message.equals(Configure.LOAD_FAIL)) {
            SerialPortWrite.write(menus());
        }else {
            String result = message.substring(0, message.length() - 4).substring(16);
            System.out.println("��˽��:" + new String(BaseService.hexStringToBytes(result)));
        }
    }
    
}
