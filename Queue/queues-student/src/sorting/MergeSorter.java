package sorting;

import java.io.ObjectOutputStream.PutField;

import structures.Queue;

/**
 * A class containing methods to sort queues and merge sorted queues.
 * 
 * "Sorted" means in ascending order: the front of the queue is the smallest
 * element, and the rear of the queue is the largest.
 * 
 * e1 is less than or equal to e2 if and only if (e1.compareTo(e2) <= 0)
 *
 */
public class MergeSorter<T extends Comparable<T>> {
	/**
	 * Returns a new queue containing the elements from the input queue
	 * in sorted order.
	 * 
	 * Implement this method recursively:
	 * 
	 *   In the base case, return the queue with no further work.
	 *
	 *   Otherwise:
	 * 
	 *     First, call split to split the input queue into two smaller output queues.
	 * 
	 *     Then, recursively mergeSort each of these two smaller queues. 
	 * 
	 *     Finally, return the result of merging these two queues.
	 * 
	 * @param queue an input queue
	 * @return a sorted copy of the input queue
	 */
	public Queue<T> mergeSort(Queue<T> queue) {
		Queue<T> input = new Queue<T>(queue);
		if (input.getSize() == 0 || input.getSize() == 1){
			return input;
		}
		else {
			Queue<T> output1 = new Queue<T>();	// output queue 1		
			Queue<T> output2 = new Queue<T>();	// output queue 2
        	// TODO 1
			split(input, output1, output2);
			output1 = mergeSort(output1);
			output2 = mergeSort(output2);
            return merge(output1, output2);
		}
	}

	/**
	 * Split elements from the input queue into the output queues, roughly
	 * half and half.
	 * 
	 * @param input a queue
	 * @param output1 a queue into which about half of the elements in input should go
	 * @param output2 a queue into which the other half of the elements in input should go
	 */
	void split(Queue<T> input, Queue<T> output1, Queue<T> output2) {
        	// TODO 2
		if (input.isEmpty()) {
			return;
		}
			output1.enqueue(input.dequeue());

		if (input.isEmpty()) {
			return;
		}
			output2.enqueue(input.dequeue());

		split(input, output1, output2);

	}
	
	/**
	 * Merge sorted input queues into an output queue in sorted order,
	 * and returns that queue. To do so: while there are still elements
	 * in at least one of the input queues, compare the front elements
	 * and pick the smaller among the two, dequeue it, and enqueue it
	 * to the output queue. Remember, the Queue class has a peek method
	 * which allows you to check the front element without removing it. 
	 * 
	 * @param input1 a sorted queue
	 * @param input2 a sorted queue
	 * @return a sorted queue consisting of all elements from input1 and input2
	 */
	Queue<T> merge(Queue<T> input1, Queue<T> input2) {
		Queue<T> output = new Queue<T>();
        	// TODO 3
			/* if (input1.isEmpty() && input2.isEmpty()){
				return output;
			} 			
			if (input1.isEmpty()){
				return input2;
			}
			if (input2.isEmpty()){
				return input1;
			}
			else if (!input1.isEmpty() && !input2.isEmpty()){
				T a = input1.dequeue();
				T b = input2.dequeue();
				while(true){
					if (a.compareTo(b) < 0){
						output.enqueue(a);
						if (input1.isEmpty()){
							output.enqueue(b);
							break;
						}
						a = input1.dequeue();
					}
					else {
						output.enqueue(b);
						if (input2.isEmpty()){
							output.enqueue(a);
							break;
						}
						b = input2.dequeue();
					}
				}
			}
			if (input1.getSize() > 0){
				while(!input1.isEmpty()){
					T a = input1.dequeue();
					output.enqueue(a);
				}
			}
			if (input2.getSize() > 0){
				while (!input2.isEmpty()){
					T b = input1.dequeue();
					output.enqueue(b);
				}
			} */
			mergeHelper(input1, input2, output);
            return output; 
	}

	void mergeHelper(Queue<T> input1, Queue<T> input2, Queue<T> output){
		if (input1.isEmpty() && input2.isEmpty()){
			return;
		}
		if (input1.isEmpty()){
			output.enqueue(input2.dequeue());
		}
		else if (input2.isEmpty()){
			output.enqueue(input1.dequeue());
		}
		else {
			if (input1.peek().compareTo(input2.peek()) <= 0){
				output.enqueue(input1.peek());
				input1.dequeue();
			}
			else {
				output.enqueue(input2.peek());
				input2.dequeue();
			}
		}
		mergeHelper(input1, input2, output); 
	}
}
