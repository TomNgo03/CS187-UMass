package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

	protected Node<T> head;
	protected Node<T> tail;
	protected int size;

	public Queue() {
		// TODO 1
	}

	public Queue(Queue<T> other) {
		// TODO 2
		Node<T> nn = other.head;
		while (nn != null) {
			this.enqueue(nn.getData());
			nn = nn.getNext();
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO 3
		boolean check = false;
		if (head == null){
			check = true;
		}
		return check;
	}

	@Override
	public int getSize() {
		// TODO 4
		return size;
	}

	@Override
	public void enqueue(T element) {
		// TODO 5
		Node<T> node = new Node<T>(element);
		size+=1;
		if (tail == null) {
			head = node;
		}

		else {
			tail.setNext(node);
		}
		tail = node;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		// TODO 6
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		else {
			T delete = head.getData();
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
			size-=1;
			return delete;
		}
	}

	@Override
	public T peek() throws NoSuchElementException {
		// TODO 7
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return head.getData();
		}
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO 8
		Queue<T> reservation = new Queue<T>();
		helper(head, reservation);
		return reservation;
	}

	private void helper(Node<T> node, Queue<T> reservation){
		if (node != null){
			helper(node.getNext(), reservation);
			reservation.enqueue(node.getData());
		}
	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
	
	public T getData() {
		return data;
	}

	public Node<T> getNext(){
		return next;
	}

	public void setData(T data){
		this.data = data;
	}

	public void setNext(Node<T> next){
		this.next = next;
	}
}

