package com.serialport;

public class Configure {

    /**
     * 包头
     */
    private final static String DATA_HEADER = "006b94";
    
    /**
     * 反包头
     */
    private final static String DATA_HEADER_CONTRARY = "00fe01";
    
    /**
     * 结束符
     */
    public final static String DATA_END = "ff";
    
    /**
     * 基站握手接收命令
     */
    public final static String BOOT_RECEIVE = DATA_HEADER + DATA_END;
    
    /**
     * 基站握手回复命令
     */
    public final static String BOOT_BACK = DATA_HEADER + "05fa" + DATA_END;
    
    /**
     * WX给基站发送开机数据(包头+标志 + 反标志(ff-标志))
     */
    public final static String BOOT_DATA = DATA_HEADER + "05fa";
    
    /**
     * 处理成功
     */
    public final static String LOAD_SUCCESS = DATA_HEADER + "01fe" + DATA_END;
    
    /**
     * 处理失败，重新发送上一条数据
     */
    public final static String LOAD_FAIL = DATA_HEADER + "02fd" + DATA_END;
    
    /**
     * 基站登入(包头+标志+反标志)
     */
    public final static String LOGIN = DATA_HEADER + "1de2";
    
    /**
     * 开台
     */
    public final static String FOUND = DATA_HEADER + "0df2";
    
    /**
     * 点菜
     */
    public final static String ORDER = DATA_HEADER + "12ed";
    
    /**
     * 退菜
     */
    public final static String FOOD_BACK = DATA_HEADER + "15ea";
    
    /**
     * 下载菜品
     */
    public final static String MENU_LOAD = DATA_HEADER + "23dc";
    
    /**
     * 开始下载菜品
     */
    public final static String MENU_LOAD_BEGIN = DATA_HEADER_CONTRARY + "03fc";
    
    /**
     * 下载类别
     */
    public final static String CATE_LOGD_BEGIN = DATA_HEADER_CONTRARY + "04fb";
    
    /**
     * 下载包房
     */
    public final static String ROOM_LOAD_BEGIN = DATA_HEADER_CONTRARY + "05fa";
    
    /**
     * 套餐表下载
     */
    public final static String GROUP_LOAD_BEGIN = DATA_HEADER_CONTRARY + "06f9";
    
    /**
     * 套餐内容表下载
     */
    public final static String GROUP_CONTENT_BEGIN = DATA_HEADER_CONTRARY + "07f8";
    
    /**
     * 推荐表下载
     */
    public final static String RECOMMENDED_LOAD = DATA_HEADER_CONTRARY + "08f7";
    
    /**
     * 推荐表下载
     */
    public final static String RECOMMENDED_CONTENT_LOAD = DATA_HEADER_CONTRARY + "09f6";
    
    /**
     * 客户注释下载
     */
    public final static String CUSTOMER_REMARK_LOAD = DATA_HEADER_CONTRARY + "0af5";
    
    /**
     * 退菜理由下载
     */
    public final static String BACK_REASON_LOAD = DATA_HEADER_CONTRARY + "0bf4";
    
    /**
     * 短信息表下载
     */
    public final static String MESSAGE_LOAD = DATA_HEADER_CONTRARY + "0cf3";
}
