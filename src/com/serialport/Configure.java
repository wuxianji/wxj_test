package com.serialport;

public class Configure {

    /**
     * ��ͷ
     */
    private final static String DATA_HEADER = "006b94";
    
    /**
     * ����ͷ
     */
    private final static String DATA_HEADER_CONTRARY = "00fe01";
    
    /**
     * ������
     */
    public final static String DATA_END = "ff";
    
    /**
     * ��վ���ֽ�������
     */
    public final static String BOOT_RECEIVE = DATA_HEADER + DATA_END;
    
    /**
     * ��վ���ֻظ�����
     */
    public final static String BOOT_BACK = DATA_HEADER + "05fa" + DATA_END;
    
    /**
     * WX����վ���Ϳ�������(��ͷ+��־ + ����־(ff-��־))
     */
    public final static String BOOT_DATA = DATA_HEADER + "05fa";
    
    /**
     * ����ɹ�
     */
    public final static String LOAD_SUCCESS = DATA_HEADER + "01fe" + DATA_END;
    
    /**
     * ����ʧ�ܣ����·�����һ������
     */
    public final static String LOAD_FAIL = DATA_HEADER + "02fd" + DATA_END;
    
    /**
     * ��վ����(��ͷ+��־+����־)
     */
    public final static String LOGIN = DATA_HEADER + "1de2";
    
    /**
     * ��̨
     */
    public final static String FOUND = DATA_HEADER + "0df2";
    
    /**
     * ���
     */
    public final static String ORDER = DATA_HEADER + "12ed";
    
    /**
     * �˲�
     */
    public final static String FOOD_BACK = DATA_HEADER + "15ea";
    
    /**
     * ���ز�Ʒ
     */
    public final static String MENU_LOAD = DATA_HEADER + "23dc";
    
    /**
     * ��ʼ���ز�Ʒ
     */
    public final static String MENU_LOAD_BEGIN = DATA_HEADER_CONTRARY + "03fc";
    
    /**
     * �������
     */
    public final static String CATE_LOGD_BEGIN = DATA_HEADER_CONTRARY + "04fb";
    
    /**
     * ���ذ���
     */
    public final static String ROOM_LOAD_BEGIN = DATA_HEADER_CONTRARY + "05fa";
    
    /**
     * �ײͱ�����
     */
    public final static String GROUP_LOAD_BEGIN = DATA_HEADER_CONTRARY + "06f9";
    
    /**
     * �ײ����ݱ�����
     */
    public final static String GROUP_CONTENT_BEGIN = DATA_HEADER_CONTRARY + "07f8";
    
    /**
     * �Ƽ�������
     */
    public final static String RECOMMENDED_LOAD = DATA_HEADER_CONTRARY + "08f7";
    
    /**
     * �Ƽ�������
     */
    public final static String RECOMMENDED_CONTENT_LOAD = DATA_HEADER_CONTRARY + "09f6";
    
    /**
     * �ͻ�ע������
     */
    public final static String CUSTOMER_REMARK_LOAD = DATA_HEADER_CONTRARY + "0af5";
    
    /**
     * �˲���������
     */
    public final static String BACK_REASON_LOAD = DATA_HEADER_CONTRARY + "0bf4";
    
    /**
     * ����Ϣ������
     */
    public final static String MESSAGE_LOAD = DATA_HEADER_CONTRARY + "0cf3";
}
