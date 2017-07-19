package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Atomic {

    public AtomicInteger adder ;

    public Atomic() {
        this.adder = new AtomicInteger(0) ;
    }
}
