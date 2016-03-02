package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 开台
 * @author Administrator
 *
 */
public class FoundService {

    public static boolean IS_FOUND = false;
    
    public static void found() {
        //包总数 + 包序号
        String pageData = "01" + "01";
        //台号101(4个字节，不够补空格(20))
        String tableNum = "31303120";
        //人数(2个字节，不够补空格(20))
        String peopleNum = "0820";
        //服务员号1001(5个字节，不够补空格(20))
        String workNum = "3130303120";
        //出厂号后7位
        String factoryNumber = "33323235303832";
        //流水号(六位，每次+1)
        String orderNum = "30303a30363a3532";
        
        String enableData = pageData + tableNum + peopleNum + workNum + factoryNumber + orderNum;
        
        //有效字节数  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //校验和加1
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.FOUND + hexPageNum + enableData + check + Configure.DATA_END;
        
        SerialPortWrite.write(data);
    }
    
    public static void result(String message) {
        String result = message.substring(0, message.length()- 4).substring(16);
        System.out.println("开台:" + new String(BaseService.hexStringToBytes(result)));
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
        
        IS_FOUND = true;
    }
}
