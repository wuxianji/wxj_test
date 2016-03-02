package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 智能基站登入
 * @author Administrator
 *
 */
public class LoginService {

    public static boolean IS_LOGIN = false;
    
    public static void login() {
        
        //包总数 + 包序号
        String pageData = "01" + "01";
        //出厂编号
        String factoryNumber = "30303033323235303832";
        //工号1001
        String workNumber = "31303031";
        //密码1001
        String password = "31303031";
        //空格
        String blank = "20202020";
        
        //有效数据
        String enableData = pageData + factoryNumber + workNumber + password + blank;
        
        //有效字节数  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //校验和加1
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.LOGIN + hexPageNum + enableData + check + Configure.DATA_END;
        
        SerialPortWrite.write(data);
    }
    
    public static void loginResult(String message) {
        String result = message.substring(16,18);//01成功，02失败
        if (result.equals("01")) {
            String workName = message.substring(18, 32);//服务员名称
            System.out.println("登陆成功,登陆服务员:" + new String(BaseService.hexStringToBytes(workName)));
        }else {
            System.out.println("登陆失败");
        }
        
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
        
        IS_LOGIN = true;
    }
    
}
