package com.serialport.service;

import com.serialport.Configure;
import com.serialport.SerialPortWrite;

/**
 * Ì×²Í±íÏÂÔØ
 * @author Administrator
 *
 */
public class GroupService {

    public static void result(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }else {
            String groupCode = new String(BaseService.hexStringToBytes(message.substring(20, 24)));
            String groupName = new String(BaseService.hexStringToBytes(message.substring(24, 64)));
            
            System.out.println("Ì×²Í±àºÅ£º" + groupCode + " Ì×²ÍÃû³Æ£º" + groupName);
            
            SerialPortWrite.write(Configure.LOAD_SUCCESS);
        }
    }
}
