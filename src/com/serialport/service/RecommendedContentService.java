package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * 下载推荐内容表
 * @author Administrator
 *
 */
public class RecommendedContentService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String recommendCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String menuCode = new String(BaseService.hexStringToBytes(message.substring(24, 34)));
            String number = new String(BaseService.hexStringToBytes(message.substring(34, 52)));
            String price = new String(BaseService.hexStringToBytes(message.substring(52, 70)));
            String unit = new String(BaseService.hexStringToBytes(message.substring(70, 78)));
            
            System.out.println("推荐菜品编号：" + recommendCode + " 菜品编号：" + menuCode
                                + " 数量：" + number + " 单价：" + price
                                + " 单位：" + unit);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
