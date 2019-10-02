package set;

public class RemoveDuplicates {
	public static void main(String[] args) {
		int[] ints = new int[] { 7, 5, 3, 2, 2, 7 };
		int[] fixed = uniqueElements(ints);
		System.out.print("{");
		for (int i : fixed) {
			if (i == fixed[fixed.length - 1]) {
				System.out.print(i + "}");
			} else {
				System.out.print(i + ",");
			}
		}

	}

	public static int[] uniqueElements(int[] ints) {
		MaxSet<Integer> nbrs = new MaxSet<Integer>();
		
		for (int i : ints) {
			nbrs.add(i);
			
		}
		int sort[] = new int[nbrs.size()];
		for (int i = sort.length - 1; i >=0 ; i --) {
			int tempMax = nbrs.getMax();
			sort[i] = tempMax;
			nbrs.remove(tempMax);
		}
		return sort;
	}
}
