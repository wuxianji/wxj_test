package com.serialport;

import com.serialport.service.BackReasonService;
import com.serialport.service.BackService;
import com.serialport.service.BootService;
import com.serialport.service.CateLoadService;
import com.serialport.service.CustomerRemarkService;
import com.serialport.service.FoundService;
import com.serialport.service.GroupContentService;
import com.serialport.service.GroupService;
import com.serialport.service.LoginService;
import com.serialport.service.MenuLoadService;
import com.serialport.service.MessageService;
import com.serialport.service.OrderService;
import com.serialport.service.RecommendedContentService;
import com.serialport.service.RecommendedService;
import com.serialport.service.RoomService;

public class Transfer {

    public static void transfer(String message) {
        if (message.equals(Configure.BOOT_RECEIVE)) {
            if (!BootService.boot) {
                BootService.boot();
            }else if (!LoginService.IS_LOGIN) {
                LoginService.login();
            }else if (!FoundService.IS_FOUND){
                FoundService.found();
            }else {
//                OrderService.upload();
                BackService.back();
//                MenuLoadService.result(message);
            }
        }
        if (message.startsWith(Configure.BOOT_DATA)) {
            BootService.bootSuccess(Configure.BOOT_DATA);
        }
        if (message.startsWith(Configure.LOGIN)) {
            LoginService.loginResult(message);
        }
        if (message.startsWith(Configure.FOUND)) {
            FoundService.result(message);
        }
        if (message.equals(Configure.LOAD_SUCCESS) || message.startsWith(Configure.ORDER)) {
            OrderService.result(message);
        }
        if (message.startsWith(Configure.FOOD_BACK)) {
            BackService.result(message);
        }
        if (message.startsWith(Configure.MENU_LOAD)) {
            MenuLoadService.load(message);
        }
        if (message.startsWith(Configure.MENU_LOAD_BEGIN)) {
            MenuLoadService.result(message);
        }
        if (message.startsWith(Configure.CATE_LOGD_BEGIN)) {
            CateLoadService.result(message);
        }
        if (message.startsWith(Configure.ROOM_LOAD_BEGIN)) {
            RoomService.result(message);
        }
        if (message.startsWith(Configure.GROUP_LOAD_BEGIN)) {
            GroupService.result(message);
        }
        if (message.startsWith(Configure.GROUP_CONTENT_BEGIN)) {
            GroupContentService.result(message);
        }
        if (message.startsWith(Configure.RECOMMENDED_LOAD)) {
            RecommendedService.result(message);
        }
        if (message.startsWith(Configure.RECOMMENDED_CONTENT_LOAD)) {
            RecommendedContentService.result(message);
        }
        if (message.startsWith(Configure.CUSTOMER_REMARK_LOAD)) {
            CustomerRemarkService.result(message);
        }
        if (message.startsWith(Configure.BACK_REASON_LOAD)) {
            BackReasonService.result(message);
        }
        if (message.startsWith(Configure.MESSAGE_LOAD)) {
            MessageService.result(message);
        }
    }
}
