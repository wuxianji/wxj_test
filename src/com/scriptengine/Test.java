package com.scriptengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Test {

    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String fileName = "src/com/scriptengine/cookie.js";
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        engine.eval(reader);
        if (engine instanceof Invocable) {
//            Invocable invoke = (Invocable) engine;
//            String sum = (String) invoke.invokeFunction("t_d[t_c]", "2");
//            System.out.println(sum);
        }
        reader.close();
    }
}
