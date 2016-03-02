package com.thread;

public class ClassWithThreadingProblem {

    int nextId;
    
    public int takeNextId() {
        return nextId++;
    }
}
