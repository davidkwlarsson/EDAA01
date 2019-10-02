package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;
	private boolean added;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		root = add(root, x);

		return added;
	}

	private BinaryNode<E> add(BinaryNode<E> node, E x) {
		if (node == null) {
			added = true;
			size++;
			return new BinaryNode<E>(x);
		} else if (x.compareTo(node.element) == 0) {
			added = false;
			return node;
		} else if (x.compareTo(node.element) < 0) {
			node.left = add(node.left, x);
			return node;
		} else {
			node.right = add(node.right, x);
			return node;
		}

	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {

		return height(root);
	}

	private int height(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		} else {
			int left = 1 + height(n.left);
			int right = 1 + height(n.right);

			if (right > left) {
				return right;
			} else {
				return left;
			}
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		print(root);

	}

	private void print(BinaryNode<E> n) {
		if (n != null) {
			print(n.left);
			System.out.print(n.element + ", ");
			print(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] vec = (E[]) new Comparable[size];
		toArray(root, vec, 0);
		root = buildTree(vec, 0, vec.length - 1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n != null) {
			index = toArray(n.left, a, index);
			a[index++] = n.element;
			index = toArray(n.right, a, index);
		}

		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if (first > last) {
			return null;
		} else {
			BinaryNode<E> tempRoot;
			int middle = (last + first + 1) / 2;
			tempRoot = new BinaryNode<E>(a[middle]);
			tempRoot.left = buildTree(a, first, middle - 1);
			tempRoot.right = buildTree(a, middle + 1, last);
			return tempRoot;
		}
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
