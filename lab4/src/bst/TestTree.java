package bst;

import java.util.Random;

public class TestTree {
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BSTVisualizer visual = new BSTVisualizer("Visualizer", 1000, 700);
		System.out.println(tree.size());
		
		for(int i = 0; i <= 6; i++) {
				tree.add(2*i + 1);
			}
        Random rand = new Random();
//		
//		for(int i = 1; i < 100; i++) {
//			tree.add(rand.nextInt(20));
//		}
		//visual.drawTree(tree);
		System.out.println(tree.height());
		System.out.println(tree.size());
		tree.printTree();
		
		tree.rebuild();
		visual.drawTree(tree);
	}

}
