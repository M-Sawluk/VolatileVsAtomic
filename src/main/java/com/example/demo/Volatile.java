package com.example.demo;

import org.springframework.stereotype.Component;

/**
 * Created by Mike on 2017-07-19.
 */
@Component
public class Volatile {

    private volatile int adder;

    public Volatile() {
        this.adder = 0;
    }

    public void add(){
        adder++;
    }
    public int getadder(){
        return adder;
    }

}
