package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class ArrayBasedSwapList<T> implements SwapList<T> {
	private final ArrayList<T> arrayList;
	private int swaps = 0;
	private int comparisons = 0;

	public ArrayBasedSwapList(T[] array) {
		arrayList = new ArrayList<T>(Arrays.asList(array));
	}

	public ArrayBasedSwapList(Collection<T> coll) {
		arrayList = new ArrayList<T>(coll);
	}

	@Override
	public int compare(int index1, int index2, Comparator<T> comparator) {
		comparisons++;
		return comparator.compare(arrayList.get(index1), arrayList.get(index2));
	}

	@Override
	public void swap(int index1, int index2) {
		swaps++;
		T temp = arrayList.get(index1);
		arrayList.set(index1, arrayList.get(index2));
		arrayList.set(index2, temp);
	}

	@Override
	public int size() {
		return arrayList.size();
	}

	// Returns true iff the list is sorted in ascending order according to the given comparator.
	@Override
	public boolean isSorted(Comparator<T> comparator) {
		// TODO isSorted
		boolean isSorted = true;
		int comp;
		if (arrayList.size() == 0 || arrayList.size() ==  1){
			return isSorted;
		}
		else {
			for(int i=0; i < arrayList.size()-1 ; i++){
				for (int j = i+1; j < arrayList.size(); j++){
					comp = comparator.compare(arrayList.get(i), arrayList.get(j));
					if (comp > 0){
						isSorted = false;
						break;
					}
				}
			}
		}
		
		return isSorted;
	}

	public int getSwaps() {
		return swaps;
	}

	public int getComparisons() {
		return comparisons;
	}

	// Returns a whole-number percentage of elements sorted. Traverses the list once and tallys all
	// correct ordered consecutive pairs (ex: [1, 2] is correct but [2, 1] is not).  Then divides 
	// this by the number of comparisons completed.
	// Ex: [1, 2, 3, 4] returns 100 (3 correctly sorted pairs ( [1,2], [2,3], [3,4] ) divided by 3 total comparisons)
	// Ex: [4, 3, 2, 1] returns 0
	// Ex: [4, 1, 2, 3, 5] returns 75
	@Override
	public int scoreList(Comparator<T> comparator) {
		// TODO scoreList
		int count = 0;
		int score = 0;
		for (int m=0; m < arrayList.size()-1; m++){
			if (comparator.compare(arrayList.get(m), arrayList.get(m+1)) > 0){
				count +=1;
			}
		}
		score = 100 * (arrayList.size()-1-count) / (arrayList.size() -1);
		System.out.println(score);
		return score ;
	}
	
	@Override
	public String toString() {
		return arrayList.toString();
	}

	@Override
	public SwapList<T> quicksort(SwapList<T> list, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
}
