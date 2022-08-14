package app;

import java.util.Comparator;


public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO sort
		/* else {
			int low = 0;
			int high = list.size() - 1;
			int pivot = low + (high-low)/2 ;

			boolean done = false;
			while(!done){
				while(list.compare(low, pivot, comparator) < 0){
					low += 1;
				}
				while (list.compare(pivot, high, comparator) < 0){
					high -= 1;
				}
				if (low >= high){
					done = true;
				}
				else {
					list.swap(high,low);
					low += 1;
					high -=1;
				}
			}


		} */
		
		quicksort(0, list.size() - 1);
		return list;
	}

	private void quicksort(int low, int high){
		int p;
		if (low < high){
			p = partition(low, high);
			quicksort(low, p-1);
			quicksort(p+1, high);
		}
	}

	private int partition(int low, int high){
		int pivot = (low + high)/2;
		int curr = low;
		int pointer = low;
		list.swap(high, pivot);

		pivot = high;
		high -= 1;

		while ( curr <= high){
			if (list.compare(curr, pivot,comparator) < 0){
				list.swap(curr, pointer);
				pointer += 1;
			}
			curr ++;
		}
		list.swap(pivot, pointer);
		
		return pointer;
	
	}
}

