package search;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	List<T> states;
	List<T> predecessors;
	Queue<T> queue;

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
		states = new ArrayList<T>();
		predecessors = new ArrayList<T>();
		queue = new LinkedList<T>();
	}

	@Override
	public List<T> solve() {
        		// TODO
		List<T> path = new ArrayList<T>();
		T temp = searchProblem.getInitialState();
		List<T> children = new ArrayList<T>();
		queue.add(temp);
		visitedStates.add(temp);
		predecessors.add(temp);
		states.add(temp);

		while (!searchProblem.isGoalState(queue.peek()) && !queue.isEmpty()){
			T parent = queue.peek();
			if (!searchProblem.getSuccessors(queue.peek()).isEmpty()){
				children = searchProblem.getSuccessors(queue.peek());
				if (children != null){
					for (int index = 0; index < children.size(); index++){
						if (children.get(index) != null && !visitedStates.contains(children.get(index))){
							queue.add(children.get(index));
							visitedStates.add(children.get(index));
							states.add(children.get(index));
							predecessors.add(states.indexOf(children.get(index)), parent);
						}
					}
				}
			}
			queue.remove();
		}

		T curr = queue.peek();
		path.add(queue.peek());
		if(!queue.isEmpty()){
			T parent;
			while (!curr.equals(temp)){
				parent = predecessors.get(states.indexOf(curr));
				path.add(parent);
				curr = parent;
			}
			Collections.reverse(path);
		}
		if (!isValid(path)){
			throw new RuntimeException();
		}
		return path;
	}
}
