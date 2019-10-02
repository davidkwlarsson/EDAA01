package testqueue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import queue.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> intQ1;
	private FifoQueue<Integer> intQ2;

	@Test
	public final void testSameFifoQueue() {
		intQ1 = new FifoQueue<Integer>();
		intQ2 = intQ1;
		int d = 3;
		int a = 1;
		int c = 2;
		intQ1.offer(d);
		intQ1.offer(a);
		intQ1.offer(c);
		
		try{
			intQ1.append(intQ2);
			fail("same FifoQueue should thorw exception");
		}catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public final void testTwoNotEmptyFifoQueue() {
		intQ1 = new FifoQueue<Integer>();
		intQ2 = new FifoQueue<Integer>();
		intQ1.offer(1);
		intQ1.offer(2);
		intQ1.offer(3);
		intQ2.offer(4);
		intQ2.offer(5);
		intQ2.offer(6);
		intQ1.append(intQ2);
		
		assertTrue("",intQ1.poll() == 1);
		assertTrue("",intQ1.poll() == 2);
		assertTrue("",intQ1.poll() == 3);
		assertTrue("",intQ1.poll() == 4);
		assertTrue("",intQ1.poll() == 5);
		assertTrue("",intQ1.poll() == 6);
		
	}
	@Test
	public final void testTwoEmptyQueues() {
		intQ1 = new FifoQueue<Integer>();
		intQ2 = new FifoQueue<Integer>();
		try{
			intQ1.append(intQ2);
			fail("next of empty iterator should thorw exception");
		}catch (IllegalArgumentException e) {
			
		}
		
		
		
	}
	@Test
	public final void testEmptytoNotEmpty() {
		intQ1 = new FifoQueue<Integer>();
		intQ2 = new FifoQueue<Integer>();
		intQ2.offer(1);
		intQ2.offer(2);
		intQ2.offer(3);
		intQ2.offer(4);
		intQ1.append(intQ2);
		assertTrue("",intQ1.poll() == 1);
		assertTrue("",intQ1.poll() == 2);
		assertTrue("",intQ1.poll() == 3);
		assertTrue("",intQ1.poll() == 4);
		
	}
	@Test
	public final void testNotEmptyToEmpty() {
		intQ1 = new FifoQueue<Integer>();
		intQ2 = new FifoQueue<Integer>();
		intQ2.offer(1);
		intQ2.offer(2);
		intQ2.offer(3);
		intQ2.offer(4);
		intQ2.append(intQ1);
		assertTrue("",intQ2.poll() == 1);
		assertTrue("",intQ2.poll() == 2);
		assertTrue("",intQ2.poll() == 3);
		assertTrue("",intQ2.poll() == 4);
		
	}

}
