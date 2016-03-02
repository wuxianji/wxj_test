package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 退菜
 * @author Administrator
 *
 */
public class BackService {

    public static void back() {
        //包总数 + 包序号
        String pageData = "01" + "01";
        //7个字节，不足后面补空格
        String tableNum = "31303120202020";
        //菜品编号，5个字节，不足前面补0x30
        String menuCode = "3030313031";
        //空格
        String blank = "2020202020";
        //菜品数量，7个字节，不足后面补空格
        String number = "31202020202020";
        //只数
        String few = "20202020";
        //退菜原因,10个字节。没有补空格
        String remark = "20202020202020202020";
        //出厂号后7位
        String factoryNumber = "33323235303832";
        //流水号
        String orderNum = "30303a30373a3234";
        
        String enableData = pageData + tableNum + menuCode + blank + number + few + remark + factoryNumber + orderNum;
        
        //有效字节数  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //校验和
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.FOOD_BACK + hexPageNum + enableData + check + Configure.DATA_END;
        
        SerialPortWrite.write(data);
    }
    
    public static void result(String message) {
        String result = message.substring(0, message.length()- 4).substring(16);
        System.out.println("退菜:" + new String(BaseService.hexStringToBytes(result)));
        SerialPortWrite.write(Configure.LOAD_SUCCESS);
    }
}
