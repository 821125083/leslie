package com.leslie.main;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
public class Main {

    public static void main(String[] args) {


        //synchronized
        Vector<String> strings = new Vector<String>();

        //synchronized
        List<String> strings1 = Collections.synchronizedList(new ArrayList<String>());

        //ReentrantLock
        CopyOnWriteArrayList<String> strings2 = new CopyOnWriteArrayList<>();




    }
}
