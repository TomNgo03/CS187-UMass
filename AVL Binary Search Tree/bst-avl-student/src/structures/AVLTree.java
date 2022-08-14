	package structures;

	public class AVLTree <T extends Comparable<T>> implements BSTInterface<T> {
		protected BSTNode<T> root;
		private int size;

		public AVLTree() {
			root = null;
			size = 0;
		}

		public boolean isEmpty() {
			// DO NOT MODIFY
			return root == null;
		}

		public int size() {
			// DO NOT MODIFY
			return size;
		}

		public BSTNode<T> getRoot() {
			// DO NOT MODIFY
			return root;
		}
		
		public void printTree() {
			System.out.println("------------------------");
			if (root != null) root.printSubtree(0);
		}

		public boolean remove(T element) {
			// Do not need to implement this method.
			return false;
		}

		public T get(T element) {
			// Do not need to implement this method.
			return null;
		}

		public int height() {
			return height(this.root);
		}

		public int height(BSTNode<T> node) {
			// return the node height
			return node != null ? node.getHeight() : -1;
		}
		
		public void updateHeight(BSTNode<T> node) {
			// TODO: update node height to 1 + the maximal height between left and right subtree
			int leftChildHeight = height(node.getLeft());
			int rightChildHeight = height(node.getRight());
			node.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);
			
		}
		
		public int balanceFactor(BSTNode<T> node) {
			// TODO: compute the balance factor by substracting right subtree height by left subtree height
			if (node == null){
				return 0;
			}
			return height(node.getRight()) - height(node.getLeft());
		}

		public BSTNode<T> rotateLeft(BSTNode<T> node) {
			// TODO: implement left rotation algorithm

			BSTNode<T> rightChild = node.getRight();
			BSTNode<T> papa = node.getParent();
			BSTNode<T> RLChild = rightChild.getLeft();

			if (papa != null){
				if (papa.getData().compareTo(rightChild.getData()) <= 0){
					papa.setRight(rightChild);
				}
				else {
					papa.setLeft(rightChild);
				}
			}
			else {
				root = rightChild;
			}

			if (RLChild != null){
				RLChild.setParent(node);
			}

			rightChild.setLeft(node);
			node.setParent(rightChild);

			node.setRight(RLChild);
			rightChild.setParent(papa);

			updateHeight(node);
			updateHeight(rightChild);

			return rightChild;
		}
		
		public BSTNode<T> rotateRight(BSTNode<T> node) {
			// TODO: implement right rotation algorithm

			BSTNode<T> leftChild = node.getLeft();
			BSTNode<T> papa = node.getParent();
			BSTNode<T> LRChild = leftChild.getRight();

			if (papa != null){
				if (papa.getData().compareTo(leftChild.getData()) <= 0){
					papa.setRight(leftChild);
				}
				else {
					papa.setLeft(leftChild);
				}
			}
			else {
				root = leftChild;
			}

			if (LRChild != null){
				LRChild.setParent(node);
			}

			leftChild.setRight(node);
			node.setParent(leftChild);

			node.setLeft(LRChild);
			leftChild.setParent(papa);


			updateHeight(node);
			updateHeight(leftChild);

			return leftChild;
		}

		// When inserting a new node, updating the height of each node in the path from root to the new node.
		// Check the balance factor of each updated height and run rebalance algorithm if the balance factor
		// is less than -1 or larger than 1 with following algorithm
		// 1. if the balance factor is less than -1
		//    1a. if the balance factor of left child is less than or equal to 0, apply right rotation
	    //    1b. if the balance factor of left child is larger than 0, apply left rotation on the left child,
		//        then apply right rotation
		// 2. if the balance factor is larger than 1
		//    2a. if the balance factor of right child is larger than or equal to 0, apply left rotation
	    //    2b. if the balance factor of right child is less than 0, apply right rotation on the right child,
		//        then apply left rotation
		public void add(T t) {
			// TODO
			BSTNode<T> node = new BSTNode<T>(t, null, null);

			if(t == null) {
				throw new NullPointerException();
			}
			else {
				if (root == null) {
					root = node;
					size++;
					return;
				}

				BSTNode<T> cur = root;
				while(cur != null) {
					if (t.compareTo(cur.getData()) <= 0) {
						if (cur.getLeft() == null) {
							cur.setLeft(node);
							node.setParent(cur);
							cur = null;
						}
						else {
							cur = cur.getLeft();
						}
					}

					else {
						if (cur.getRight() == null) {
							cur.setRight(node);
							node.setParent(cur);
							cur = null;
						}
						else {
							cur = cur.getRight();
						}					
					}
				}
				size++;

				cur = node.getParent();
				while (cur != null) {
					updateHeight(cur);
					cur = AVLBalance(cur).getParent();

				}
			}
		}


		private BSTNode<T> AVLBalance(BSTNode<T> node){
			if (balanceFactor(node) < -1) {
				if (balanceFactor(node.getLeft()) > 0) {
					node.setLeft(rotateLeft(node.getLeft()));
				}
				return rotateRight(node);
			}
			else if (balanceFactor(node) > 1) {
				if (balanceFactor(node.getRight()) < 0) {
					node.setRight(rotateRight(node.getRight()));
				}
				return rotateLeft(node);
			}
			else {
				return node;
			}
		}
	}


