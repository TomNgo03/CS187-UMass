package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int getSize() {
		// TODO
		return sizeHelper(root);
	}

	private int sizeHelper(BSTNode<T> node){
		if (node == null){
			return 0;
		}
		else {
			return 1 + sizeHelper(node.getLeft()) + sizeHelper(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		if (t == null){
			throw new NullPointerException();
		}
		else {
			return containsHelper(root, t);
		}
	}

	private boolean containsHelper(BSTNode<T> node, T t){
		if (node == null){
			return false;
		}
		if (node.getData() == t){
			return true;
		}
		if (node.getData().compareTo(t) > 0){
			return containsHelper(node.getLeft(), t);
		}
		else {
			return containsHelper(node.getRight(), t);
		}
	}

	public boolean removeElement(T t) {
		// TODO
		if (t == null){
			throw new NullPointerException();
		}
		boolean check = contains(t);
		if (check){
			root = removeHelper(root, t);
		}
		return check;
	}

	private BSTNode<T> removeHelper(BSTNode<T> node, T t){
		if (t.compareTo(node.getData()) > 0){
			node.setRight(removeHelper(node.getRight(), t));
			return node;
		}
		else if (t.compareTo(node.getData()) < 0){
			node.setLeft(removeHelper(node.getLeft(), t));
			return node;
		}
		else {
			if (node.getRight() == null){
				return node.getLeft();
			}
			else if (node.getLeft() == null){
				return node.getRight();
			}
			else {
				node.setLeft(removeRightmostFromSubtree(node.getLeft()));
				node.setData(getHighestValueFromSubtree(node.getLeft()));
				return node;
			}
		}
	}

	public T getHighestValueFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValueFromSubtree(node.getRight());
		}
	}

	public T getLowestValueFromSubtree(BSTNode<T> node) {
		// TODO
		if (node.getLeft() == null) {
			return node.getData();
		}
		else {
			return getLowestValueFromSubtree(node.getLeft());
		}
	}

	private BSTNode<T> removeRightmostFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmostFromSubtree(node.getRight()));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	public BSTNode<T> removeLeftmostFromSubtree(BSTNode<T> node) {
		// TODO
		if (node.getLeft() == null) {
			return node.getRight();
		}
		else {
			node.setLeft(removeLeftmostFromSubtree(node.getLeft()));
			if (node.getLeft() != null){
				node.getLeft().setParent(node);
			}
			return node;
		}
	}

	public T getElement(T t) {
		// TODO
		if (t == null){
			throw new NullPointerException();
		}
		else {
			return getElementHelper(root, t);
		}
	}

	private T getElementHelper(BSTNode<T> node, T t){
		if (t == null){
			return null;
		}
		if (node.getData().compareTo(t) == 0){
			return node.getData();
		}
		else if (node.getData().compareTo(t) > 0){
			return getElementHelper(node.getLeft(), t);
		}
		else {
			return getElementHelper(node.getRight(), t);
		}

	}

	public void addElement(T t) {
		// TODO
		if (t == null){
			throw new NullPointerException();
		}
		else {
			BSTNode<T> insertion = new BSTNode<T>(t, null, null);
			root = addElementHepler(root, insertion);
		}
	}

	private BSTNode<T> addElementHepler(BSTNode<T> node, BSTNode<T> insert){
		if (node == null){
			return insert;
		}
		if (insert.getData().compareTo(node.getData()) <= 0){
			node.setLeft(addElementHepler(node.getLeft(), insert));
		}
		else {
			node.setRight(addElementHepler(node.getRight(), insert));
		}
		return node;
	}

	@Override
	public T getMin() {
		// TODO
		if (root == null){
			return null;
		}
		else {
			return getMinHelper(root);
		}
	}

	private T getMinHelper(BSTNode<T> node){
		BSTNode<T> curr = node;
		while (curr.getLeft() != null){
			curr = curr.getLeft();
		}
		return curr.getData();
	}


	@Override
	public T getMax() {
		// TODO
		if (root == null){
			return null;
		}
		else {
			return getMaxHelper(root);
		}
	}

	private T getMaxHelper(BSTNode<T> node){
		BSTNode<T> curr = node;
		while (curr.getRight() != null){
			curr = curr.getRight();
		}
		return curr.getData();
	}

	@Override
	public int height() {
		// TODO
		return getHeight(root);
	}

	private int getHeight(BSTNode<T> node){
		if (node == null){
			return -1;
		}
		else {
			return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
		}
	}

	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}

	private void preorderTraverse(Queue<T> queue, BSTNode<T> node){
		if (node != null){
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}

	private void postorderTraverse(Queue<T> queue, BSTNode<T> node){
		if (node != null){
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		if (other == null){
			throw new NullPointerException();
		}
		else {
			return equalHelper(root, other.getRoot());
		}
	}

	private boolean equalHelper(BSTNode<T> old, BSTNode<T> curr){
		if (old == null && curr == null){
			return true;
		}
		else if (old == null || curr == null){
			return false;
		}
		else {
			if (old.getData().equals(curr.getData())){
				return true;
			}
			else {
				return equalHelper(old.getLeft(), curr.getLeft()) && equalHelper(old.getRight(), curr.getRight()); 
			}
		}
	}

	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		if (other == null){
			throw new NullPointerException();
		}
		else {
			boolean check = false;
			Iterator<T> one = this.inorderIterator();
			Iterator<T> two = other.inorderIterator();
			while (one.hasNext() && two.hasNext()) 
				if (!one.next().equals(two.next())){
					return check;
				}
			return !one.hasNext() && !two.hasNext();
		}
	}
	
	@Override
	public int countRange(T min, T max) {
    	// TODO
		int count = 0;
		Iterator<T> newList = this.inorderIterator();
		while(newList.hasNext()){
			T temp = newList.next();
			if (temp.compareTo(min) > 0 && temp.compareTo(max) < 0){
				count ++;
			}
		}
		return count;
  }


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.addElement(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.removeElement(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.addElement(r);
		}
		System.out.println(tree.getSize());
		System.out.println(tree.height());
		System.out.println(tree.countRange("a", "g"));
		System.out.println(tree.countRange("c", "f"));
	}
}