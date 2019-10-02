package map;

import java.util.Random;

public class MainTest {

	public static void main(String[] args) {
		Random rand = new Random();
		SimpleHashMap<Integer, Integer> hashMap = new SimpleHashMap<Integer, Integer>(10);
		System.out.println(hashMap.show());
		System.out.println(hashMap.size());
		System.out.println(hashMap.isEmpty());
		
		for(int i = 0; i < 8; i++) {
				Integer temp = rand.nextInt();
				hashMap.put(temp, temp);
		}
		System.out.println(hashMap.show());
		System.out.println(hashMap.size());
		System.out.println(hashMap.isEmpty());

	}

}
