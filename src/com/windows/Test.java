package com.windows;

import java.util.Timer;
import java.util.TimerTask;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.MSG;
import org.xvolks.jnative.misc.SHELLEXECUTEINFO;
import org.xvolks.jnative.misc.basicStructures.HWND;
import org.xvolks.jnative.misc.basicStructures.LPARAM;
import org.xvolks.jnative.misc.basicStructures.UINT;
import org.xvolks.jnative.misc.basicStructures.WPARAM;
import org.xvolks.jnative.util.Shell32;
import org.xvolks.jnative.util.WindowProc;
import org.xvolks.jnative.util.constants.winuser.WM;
import org.xvolks.jnative.util.constants.winuser.WindowsConstants;
import org.xvolks.test.callbacks.EnumCallback;

import com.jfinal.kit.PathKit;

public class Test extends Thread{

    @Override
    public void run(){
        try {
            HWND hwnd = getHWND();

          registerHandle(hwnd);
          
          sendMessage(hwnd);
          
          getMessage(hwnd);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {

//        startWx();
         getHWND();
         showHandle();
        // getName();
//        System.out.println(PathKit.getPath(""));
//        new Test().start();
    }

    private static void startWx() throws Exception {

        HWND hwnd = getHWND();

//        SHELLEXECUTEINFO execInfo = new SHELLEXECUTEINFO();
////        execInfo.lpFile = "WxServ.exe";
//        execInfo.lpFile = "WX.exe";
//        execInfo.lpDirectory = "D:\\wx\\";
//        execInfo.nShow = WindowsConstants.SW_SHOWNORMAL;
//        execInfo.lpVerb = "open";
//        execInfo.hwnd = hwnd;
//        execInfo.lpParameters = hwnd.getValueAsString();
//
//        if (Shell32.ShellExecuteEx(execInfo)) {
//            System.out.println("WX启动成功。。。。。。");
//        }
        
        registerHandle(hwnd);
        
        sendMessage(hwnd);
        
        getMessage(hwnd);
    }

    private static HWND getHWND() throws Exception {
//         HWND hwnd = new HWND(12312312);
        HWND hwnd = new HWND(User32.CreateWindowEx(0, "Button", "java",
                WindowsConstants.WS_OVERLAPPEDWINDOW, 20, 30, 200, 300, 0, 0,
                JNative.getCurrentModule(), 0));

        // 无线点菜机管理
        // HWND hwnd = User32.FindWindow(null, "Wxserv");
        if (hwnd.getValue() > 0) {
            System.out.println("window exists -- " + hwnd.getValue());
            // User32.SendMessage(hWnd, new UINT(0x10), new WPARAM(0), new
            // LPARAM(0));
        } else {
            System.out.println("window doesn't exists");
        }

        return hwnd;
    }

    private static void sendMessage(HWND hwnd) throws Exception {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("开始发送消息。。。。。。" + wxHandle);
                try {
                    User32.PostMessage(hwnd, new UINT(3), new WPARAM(4), new LPARAM(2));
//                    User32.PostMessage(new HWND(wxHandle), new UINT(16), new WPARAM(0), new LPARAM(0));
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NativeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, 5000, 5000);
    }

    private static void getMessage(HWND hwnd) throws Exception {

        MSG msg = new MSG();
        boolean lQuit = false;
        while (!lQuit) {
            int result = User32.GetMessage(msg, hwnd, 0, 0);
            System.out.println("result:" + result);
            switch (result) {
            case -1:
                System.err.println("Error occured");
                lQuit = true;
                break;
            case 0:
                System.err.println("WM_QUIT received");
                lQuit = true;
                break;
            default: ;
            }
            User32.TranslateMessage(msg);
            User32.DispatchMessage(msg);
        }
        System.out.println("msg:" + msg.getValueAsString());
    }

    private static int wxHandle = 0;
    
    private static void registerHandle(HWND hwnd) throws Exception{
        JNative.registerWindowProc(hwnd, new WindowProc() {

            public int windowProc(int hwnd, int uMsg, int wParam, int lParam) {
                System.err.println(hwnd + " " + uMsg + " " + wParam + " "
                        + lParam);
                
                wxHandle = wxHandle > 0 ? wxHandle : lParam;
                
                try {
                    if (uMsg == WM.WM_CREATE.getValue()) // Initialize the
                        return 0;
                    else if (uMsg == WM.WM_PAINT.getValue()) // Paint the
                                                             // window's client
                                                             // area.
                        return 1;
                    else if (uMsg == WM.WM_SIZE.getValue()) // Set the size and
                                                            // position of the
                                                            // window.
                        return 0;
                    else if (uMsg == WM.WM_DESTROY.getValue()) // Clean up
                                                               // window-specific
                                                               // data objects.
                        return 0; // // Process other messages. //
                    else
                        // return 1;
                        return User32.DefWindowProc(new HWND(hwnd),
                                new UINT(uMsg), new WPARAM(wParam),
                                new LPARAM(lParam)).getValue();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

        });
    }

    private static void showHandle() throws Exception {
        EnumCallback c = new EnumCallback();
        System.err.println("inactive callbacks : "
                + JNative.getAvailableCallbacks());
        System.err.println("getAvailableCallbacks "
                + JNative.getAvailableCallbacks());

        if (User32.EnumWindows(c, 0)) {
            System.err.println("EnumWindows suceeded");
        } else {
            System.err.println("EnumWindows failed");
        }
        c.toString();
    }
}
