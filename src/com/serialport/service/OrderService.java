package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 点菜
 * @author Administrator
 *
 */
public class OrderService {

    public static void upload() {
        
        String data = baseData();
        
        SerialPortWrite.write(data);
    }
    
    private static String baseData() {
        //包总数 + 包序号
        String pageData = "02" + "01";
        //台号101(7个字节，不够补空格(20))
        String tableNum = "31303120202020";
        //出厂编号(前4位)
        String factoryNumberBefor = "30303033";
        String blank = "2020202020";
        //整单注释，每个注释3个编号，共可放4个注释
        String remark1 = "202020";
        String remark2 = "202020";
        String remark3 = "202020";
        String remark4 = "202020";
        
        String blank2 = "202020";
        //流水号
        String orderNum = "010103";
        //出厂编号(后6位)
        String factoryNumberAfter = "323235303832";
        
        String enableData = pageData + tableNum + factoryNumberBefor + blank + remark1 + remark2 + remark3 + remark4 + blank2 + orderNum + factoryNumberAfter;
        
        //有效字节数  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //校验和
        String check = BaseService.getCheckDigit(enableData);
        
        String data = Configure.ORDER + hexPageNum + enableData + check + Configure.DATA_END;
        
        return data;
    }
    
    private static String menus() {
        //包总数 + 包序号
        String pageData = "02" + "02";
        //台号101(7个字节，不够补空格(20))
        String tableNum = "31303120202020";
        //菜品编号，5个字节，不足前面补0x30
        String menuCode = "3030313031";
        //菜品数量
        String number = "20202032";
        
        //注释编号,没有为空格
        String remark1 = "202020";
        String remark2 = "202020";
        String remark3 = "202020";
        String remark4 = "202020";
        
        //套餐编号，没有为空格
        String groupNum = "2020";
        //等叫标志，正常为0x30，等叫为0x31
        String waitStatus = "30";
        //流水号
        String orderNum = "010102";
        //只数
        String few = "20202020";
        //客人数
        String people = "0220";
        
        String enableData = pageData + tableNum + menuCode + number 
                            + remark1 + remark2 +  remark3 + remark4 
                            + groupNum + waitStatus + orderNum + few + people;
        
      //有效字节数  
        String hexPageNum = Integer.toHexString(enableData.length() / 2);
        
        //校验和
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
            System.out.println("点菜结果:" + new String(BaseService.hexStringToBytes(result)));
        }
    }
    
}
