package test;

import static org.junit.Assert.*;

import org.junit.Test;

import set.RemoveDuplicates;

public class TestRemoveDuplicates {
	

	@Test
	public void testSorting() {
		
		int[] ints = new int[]{3, 5, 213, 32, 5, 5, 32 , 1000};
		int[] test = RemoveDuplicates.uniqueElements(ints);
		
		assertArrayEquals("Wrong new array", new int[]{3,5,32,213, 1000},test );
	}
	@Test
	public void testNegative() {
		
		int[] ints = new int[]{3, 5, -213, -32, -45};
		int[] test = RemoveDuplicates.uniqueElements(ints);
		
		assertArrayEquals("Wrong new array", new int[]{-213, -45, -32, 3, 5}, test);
		
	}
	@Test
	public void sameNbr() {
		int[] ints = new int[]{5, 5, 5, 5, 5};
		int[] test = RemoveDuplicates.uniqueElements(ints);
		
		assertArrayEquals("Wrong new array", new int[]{5},test );
		
	}
	@Test
	public void testEmpty() {
		int[] ints = new int[]{};
		int[] test = RemoveDuplicates.uniqueElements(ints);
		
		assertArrayEquals("Wrong new array", new int[]{},test );
		
	}
}
