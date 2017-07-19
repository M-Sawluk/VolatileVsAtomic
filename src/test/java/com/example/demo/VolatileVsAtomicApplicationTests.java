package com.example.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executor;

@RunWith(Parameterized.class)
public class VolatileVsAtomicApplicationTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Parameterized.Parameter(0)
	public int threads;
	@Parameterized.Parameter(1)
	public int expected;

	public Atomic atomic = new Atomic();;


	public Volatile aVolatile;

	public VolatileVsAtomicApplicationTests() {
	}

	@Parameterized.Parameters
	public static Iterable<Object[]> data(){
		return Arrays.asList(new Object[][]{{100,100}
		,{100_000,100_000}
		,{999,999}});
	}

	@Test
	public void testParams(){
		logger.error("threads : {} , expected {}" , threads, expected);
		Assert.assertEquals(threads,expected);
	}

	@Test
	public void testAtomic() throws InterruptedException {

		atomic.adder.set(0);

		for (int i = 0; i <threads ; i++) {
			startAtomicThread();
		}
		Thread.sleep(500);
		logger.error("Atomic value: {} , expected {}" , atomic.adder.get(), expected);
		Assert.assertEquals(atomic.adder.getAndDecrement(),expected);

	}

	public void startAtomicThread(){
		Thread thread = new Thread(()->atomic.adder.incrementAndGet());
		thread.start();
	}

	@Test
	public void testVolatile() throws InterruptedException {

		aVolatile = new Volatile();

		for (int i = 0; i <threads ; i++) {
			startaVolatileThread();
		}
		Thread.sleep(500);
		logger.error("Atomic value: {} , expected {}" , aVolatile.getadder(), expected);
		Assert.assertEquals(aVolatile.getadder(),expected);

	}

	public void startaVolatileThread() throws InterruptedException {
		Thread thread = new Thread(()->aVolatile.add());
		thread.start();
	}

}
