package search;

import java.util.List;
import java.util.Stack;

import javax.management.RuntimeErrorException;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
		// TODO
		Stack<T> stack = new Stack<T>();
		stack.push(searchProblem.getInitialState());
		visitedStates.add(searchProblem.getInitialState());
		T temp = null;
		while(!stack.isEmpty()){
			temp = getNext(stack.peek());
			if (searchProblem.isGoalState(temp)){
				stack.push(temp);
				return stack;
			}
			if (temp == null){
				stack.pop();
			}
			else {
				visitedStates.add(temp);
				stack.push(temp);
			}
		}
		return stack;
	}


	private T getNext(T t){
		for (T node : searchProblem.getSuccessors(t)){
			if (!visitedStates.contains(node)){
				return node;
			}
		}
		return null;
	}
}
