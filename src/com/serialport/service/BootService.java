package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 开机验证
 * @author Administrator
 *
 */
public class BootService extends BaseService{

    public static boolean boot = false; //默认未开机
    
    //开机握手
    public static void boot() {
        if (!boot) {
            SerialPortWrite.write(Configure.BOOT_BACK);
            boot = true;
        }else {
//            LoginService.login();
        }
    }
    
    //下载数据成功
    public static void bootSuccess(String message) {
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
    }
    
    //下载数据失败
    public static void bootFail(String message) {
        SerialPortWrite.write(Configure.LOAD_FAIL);
    }
}
