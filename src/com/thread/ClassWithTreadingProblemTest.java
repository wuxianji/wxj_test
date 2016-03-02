package com.thread;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassWithTreadingProblemTest {

    @Test
    public void twoThreadsShouldFailEventually() throws Exception{
        final ClassWithThreadingProblem classWithThreadinngProblem = 
                new ClassWithThreadingProblem();
        
        Runnable runnable = new Runnable(){
            public void run(){
                classWithThreadinngProblem.takeNextId();
            }
        };
        
        for (int i = 0; i < 100000; ++i) {
            int startingId = classWithThreadinngProblem.nextId;
            int expectedResult = 2 + startingId;
            
            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            
            t1.start();
            t2.start();
            
            t1.join();
            t2.join();
            
            int endingId = classWithThreadinngProblem.nextId;
            System.out.println(endingId + "--" + expectedResult);
            if (endingId != expectedResult) {
                return;
            }
        }
        
        fail("Should hava exposed a threading issue but it did not.");
    }
}
