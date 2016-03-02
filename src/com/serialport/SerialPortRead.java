package com.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

public class SerialPortRead implements Runnable, SerialPortEventListener {
    static CommPortIdentifier portId;
    // ö����
    static Enumeration portList;

    public static SerialPort serialPort;
    InputStream inputStream;
    Thread readThread;

    public static void main(String[] args) {
        portList = CommPortIdentifier.getPortIdentifiers();
        /*
         * ����������getPortIdentifiers�������һ��ö�ٶ��󣬸ö����ְ�����ϵͳ�й���ÿ���˿ڵ�CommPortIdentifier����
         * �� ע������Ķ˿ڲ�������ָ���ڣ�Ҳ�������ڡ�������������Դ�������getPortIdentifiers(CommPort)������Ѿ���Ӧ
         * �ó���򿪵Ķ˿����Ӧ��CommPortIdentifier����getPortIdentifier(String
         * portName)��ȡָ���˿��������硰COM1���� ��CommPortIdentifier����
         */
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            /* getPortType�������ض˿����� */
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                /* ��Windows�µĵ�һ������ */
                if (portId.getName().equals("COM3")) {
                    SerialPortRead reader = new SerialPortRead();
                }
            }
        }
    }

    public SerialPortRead() {
        try {
            /*
             * open������ͨѶ�˿ڣ����һ��CommPort������ʹ�����ռ�˿ڡ�����˿���������Ӧ�ó���ռ�ã���ʹ��
             * CommPortOwnershipListener�¼�����
             * ������һ��PORT_OWNERSHIP_REQUESTED�¼���ÿ���˿ڶ�����һ��
             * InputStream��һ��OutputStream
             * ������˿�����open�����򿪵ģ���ô�κε�getInputStream��������
             * ��ͬ�����������󣬳�����close�����á���������������һ��ΪӦ�ó��������ڶ����������ڶ˿ڴ� ʱ�����ȴ��ĺ�������
             */
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (PortInUseException e) {
        }
        try {
            /* ��ȡ�˿ڵ����������� */
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {
        }
        try {
            /* ע��һ��SerialPortEventListener�¼������������¼� */
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
        }
        /* ���ݿ��� */
        serialPort.notifyOnDataAvailable(true);
        try {
            /* ���ô��ڳ�ʼ�������������ǲ����ʣ�����λ��ֹͣλ��У�� */
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
        }
        readThread = new Thread(this);
        readThread.start();
    }

    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
        }
    }

    // �����¼�
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
        case SerialPortEvent.BI:/* Break interrupt,ͨѶ�ж� */
        case SerialPortEvent.OE:/* Overrun error����λ���� */
        case SerialPortEvent.FE:/* Framing error����֡���� */
        case SerialPortEvent.PE:/* Parity error��У����� */
        case SerialPortEvent.CD:/* Carrier detect���ز���� */
        case SerialPortEvent.CTS:/* Clear to send��������� */
        case SerialPortEvent.DSR:/* Data set ready�������豸���� */
        case SerialPortEvent.RI:/* Ring indicator������ָʾ */
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*
                                                  * Output buffer is
                                                  * empty��������������
                                                  */
            break;
        case SerialPortEvent.DATA_AVAILABLE:/*
                                             * Data available at the serial
                                             * port���˿��п������ݡ������������飬������ն�
                                             */
            byte[] readBuffer = new byte[256];
            int numBytes = 0;
            try {
                while (inputStream.available() > 0) {
                    numBytes = inputStream.read(readBuffer);
                }

                StringBuilder readStr = new StringBuilder();
                for (int i = 0; i < numBytes; i++) {
                    String s = Integer.toHexString(readBuffer[i] & 0xFF);
                    readStr.append(s.length() == 1 ? ("0" + s) : s);
                }
                System.out.println("�������ݣ�" + readStr);
                
                Transfer.transfer(readStr.toString());
                
            } catch (IOException e) {
            }
            break;
        }
    }

}
