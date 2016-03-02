package com.windows;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

import org.xvolks.jnative.Convention;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.logging.JNativeLogger.SEVERITY;
import org.xvolks.jnative.misc.MSG;
import org.xvolks.jnative.misc.basicStructures.HWND;
import org.xvolks.jnative.pointers.NullPointer;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;
import org.xvolks.jnative.pointers.memory.NativeMemoryBlock;
import org.xvolks.jnative.util.Kernel32;
import org.xvolks.jnative.util.constants.winuser.WindowsConstants;
import org.xvolks.test.KillProcess;
import org.xvolks.test.callbacks.TestCallback;
import org.xvolks.test.callbacks.linux.LinuxCallback;

/**
 * 
 * $Id: JNativeTester.java,v 1.28 2007/05/20 12:05:05 thubby Exp $
 * 
 * This software is released under the LGPL.
 * 
 * @author Created by Marc DENTY - (c) 2006 JNative project
 */
public class JNativeTester {
    private static File searchFile(final String pattern, String root) {
        System.out.print("Searching " + pattern + " in " + root);
        for (File f : new File(root).listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return Pattern.matches(pattern, pathname.getName());
            }
        })) {
            if (f.isFile()) {
                System.out.println("... found " + f);
                return f;
            }
        }
        System.out
                .println("... Not found, sorry cannot demonstrate JNative with "
                        + pattern);
        return null;
    }

    public static void main(String[] args) throws NativeException,
            IllegalAccessException, InterruptedException, IOException {
        
        System.getProperties().put("jnative.loadNative", "manual");
        
        loadLib();
        if (System.getProperty("os.name").toLowerCase().indexOf(
                "windows") != -1) {
            User32.MessageBox(0,
                    "Demonstrates JNative in action with many Win32 calls",
                    "Welcome to JNative", 0);

            User32.MessageBox(
                            0,
                            "Demarrage de la calculatrice,\nATTENTION : ne cliquez pas sur OK\nsi vous avez ouvert la calculatrice\navec des donnees non sauvegardees : elles seraient perdues",
                            "Tuer un precessus par son nom", 0);
            User32.MessageBox(
                            0,
                            "Starting calculator,\nCAUTION : DO NOT click OK\nif you have calc open\nwith unsaved data : they will be lost",
                            "Kill a process by name", 0);
            try {
                Runtime.getRuntime().exec("calc.exe");
                Runtime.getRuntime().exec("calc.exe");
                Runtime.getRuntime().exec("calc.exe");
                Runtime.getRuntime().exec("calc.exe");
                Thread.sleep(3000);
                if (0 != new KillProcess("calc.exe", true).killProcess())
                    User32.MessageBox(0, "Process not found", "Error", 0);
            } catch (IOException e) {
                e.printStackTrace();
                User32.MessageBox(0, e.getMessage(), "Error launching notepad",
                        0);
            }

            Pointer p = new Pointer(MemoryBlockFactory.createMemoryBlock(500));
            p.setStringAt(0, "This is a test");
            System.err.println("Serching in >" + p.getAsString() + "<");
            System.err.println("Java    found test at offset "
                    + p.getAsString().indexOf("test"));
            System.err.println("JNative found test at offset "
                    + JNative.searchNativePattern(p, "test".getBytes(), 500));
            if (JNative.searchNativePattern(p, "toto".getBytes(), 50) < 0) {
                System.err.println("toto not found");
            }

            try {
                User32.MessageBox(0,
                        Kernel32.GlobalMemoryStatusEx().toString(),
                        "GlobalMemoryStatusEx", 0);
                System.out.println();
                String[] ss = JNative.getDLLFileExports(System.getenv("WINDIR")
                        + "\\system32\\user32.dll");

                String me = "";
                for (String s : ss) {
                    if (s.toLowerCase().indexOf("proc") != -1)
                        me += s + "\n";
                }
                User32.MessageBox(
                                0,
                                me,
                                "Exported functions of User32.dll containing the word proc",
                                0);
                try {
                    ss = JNative.getDLLFileExports("c:/windows/twain_32.dll");
                    for (String s : ss) {
                        System.err.println(s);
                    }
                } catch (NativeException e) {
                }
            } catch (Exception e) {
                e.printStackTrace();
                User32.MessageBox(0, "Error #" + Kernel32.GetLastError(),
                        "GlobalMemoryStatusEx failed", 0);
            }

            // if(true) return;
            System.err.println(User32.MessageBox(0, Kernel32
                    .GetDiskFreeSpaceEx("c:").toString(), "Free space on "
                    + Kernel32.GetComputerName(), 0x33));
            System.err.println("Module : " + JNative.getCurrentModule());
            HWND hwnd = new HWND(User32.CreateWindowEx(0, "Button", "TATA",
                    WindowsConstants.WS_OVERLAPPEDWINDOW, 20, 30, 200, 300, 0, 0, JNative
                            .getCurrentModule(), 0));
            /*
             * JNative.registerWindowProc(hwnd, new WindowProc() {
             * 
             * /** Method windowProc
             * 
             * @param hwnd an int [in] Handle to the window. @param uMsg an int
             * [in] Specifies the message. @param wParam an int [in] Specifies
             * additional message information. The contents of this parameter
             * depend on the value of the uMsg parameter. @param lParam an int
             * Specifies additional message information. The contents of this
             * parameter depend on the value of the uMsg parameter. @return an
             * int The return value is the result of the message processing and
             * depends on the message sent.
             */
            /*
             * public int windowProc(int hwnd, int uMsg, int wParam, int lParam) {
             * System.err.println(hwnd + " " + uMsg + " " + wParam + " " +
             * lParam); try { if (uMsg == WM.WM_CREATE.getValue()) // Initialize
             * the window. return 0;
             * 
             * else if (uMsg == WM.WM_PAINT.getValue()) // Paint the window's
             * client area. return 1;
             * 
             * else if (uMsg == WM.WM_SIZE.getValue()) // Set the size and
             * position of the window. return 0;
             * 
             * else if (uMsg == WM.WM_DESTROY.getValue()) // Clean up
             * window-specific data objects. return 0; // // Process other
             * messages. //
             * 
             * else // return 1; return User32.defWindowProc(new HWND(hwnd), new
             * UINT(uMsg), new WPARAM(wParam), new LPARAM(lParam)).getValue(); }
             * catch (Exception e) { e.printStackTrace(); return 0; } }
             * 
             * });
             */
            User32.ShowWindow(hwnd, WindowsConstants.SW_SHOW);
            User32.UpdateWindow(hwnd);

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
                default:
                }
                User32.TranslateMessage(msg);
                User32.DispatchMessage(msg);
            }

            System.err.println(hwnd.getValue());
            System.err.println(Kernel32.GetLastError());
            Thread.sleep(6000);

            try {
                TestCallback.runIt();
            } catch (Exception e) {
                User32.MessageBox(0, e.toString(), e.getClass().getName(), 0);
            }
        }
        System.exit(0);
    }

    public static void loadLib() throws IOException {
        final File f;
        if (JNative.isWindows()) {
            f = new File("JNativeCpp.dll");
        } else {
            throw new IllegalStateException("This OS is acctually not supported, please contact jnative@free.fr if you want it supported!");
        }
        if (f.exists()) {
            JNative.getLogger().log(SEVERITY.INFO, "Loading native lib "+f.getAbsolutePath());
            System.load(f.getCanonicalPath());
        } else {
            JFileChooser jfc = new JFileChooser(new File("."));
            jfc.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(JNative.isWindows() ? ".dll" : ".so");
                }

                @Override
                public String getDescription() {
                    return JNative.isWindows() ? "DLL : Dynamic Link Library" : " .so : Shared library";
                }
                
            });
            
            if(JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null)) {
                JNative.getLogger().log(SEVERITY.INFO, "Loading native lib "+jfc.getSelectedFile().getCanonicalPath());
                System.load(jfc.getSelectedFile().getCanonicalPath());
            } else {
                return;
            }
        }
    }

}