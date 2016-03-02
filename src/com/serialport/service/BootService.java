package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * ������֤
 * @author Administrator
 *
 */
public class BootService extends BaseService{

    public static boolean boot = false; //Ĭ��δ����
    
    //��������
    public static void boot() {
        if (!boot) {
            SerialPortWrite.write(Configure.BOOT_BACK);
            boot = true;
        }else {
//            LoginService.login();
        }
    }
    
    //�������ݳɹ�
    public static void bootSuccess(String message) {
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
    }
    
    //��������ʧ��
    public static void bootFail(String message) {
        SerialPortWrite.write(Configure.LOAD_FAIL);
    }
}
