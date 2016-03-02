package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 套餐内容下载
 * @author Administrator
 *
 */
public class GroupContentService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String groupCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String menuCode = new String(BaseService.hexStringToBytes(message.substring(24, 34)));
            String number = new String(BaseService.hexStringToBytes(message.substring(34, 52)));
            String price = new String(BaseService.hexStringToBytes(message.substring(52, 70)));
            String unit = new String(BaseService.hexStringToBytes(message.substring(70, 78)));
            String defaultStatus = new String(BaseService.hexStringToBytes(message.substring(78, 80)));
            String GroupCode = new String(BaseService.hexStringToBytes(message.substring(80, 84)));
            
            System.out.println("套餐编号：" + groupCode + " 菜品编号：" + menuCode
                               + " 数量：" + number + " 单价：" + price
                               + " 单位：" + unit + " 缺省：" + defaultStatus
                               + " 套餐组号：" + GroupCode);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
