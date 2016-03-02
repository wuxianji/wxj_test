package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ���ܻ�վ����
 * @author Administrator
 *
 */
public class LoginService {

    public static boolean IS_LOGIN = false;
    
    public static void login() {
        
        //������ + �����
        String pageData = "01" + "01";
        //�������
        String factoryNumber = "30303033323235303832";
        //����1001
        String workNumber = "31303031";
        //����1001
        String password = "31303031";
        //�ո�
        String blank = "20202020";
        
        //��Ч����
        String enableData = pageData + factoryNumber + workNumber + password + blank;
        
        //��Ч�ֽ���  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //У��ͼ�1
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.LOGIN + hexPageNum + enableData + check + Configure.DATA_END;
        
        SerialPortWrite.write(data);
    }
    
    public static void loginResult(String message) {
        String result = message.substring(16,18);//01�ɹ���02ʧ��
        if (result.equals("01")) {
            String workName = message.substring(18, 32);//����Ա����
            System.out.println("��½�ɹ�,��½����Ա:" + new String(BaseService.hexStringToBytes(workName)));
        }else {
            System.out.println("��½ʧ��");
        }
        
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
        
        IS_LOGIN = true;
    }
    
}
