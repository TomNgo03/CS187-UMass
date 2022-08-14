package app;

import java.util.Comparator;


public class InsertionSorter<T> extends AbstractSorter<T> {

	public InsertionSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO sort
		int j = 0;
		if (list.size() == 0 || list.size() == 1){
			return list;
		}
		else {
			for (int i = 1; i < list.size(); ++i){
				j = i;
				while (j > 0 && list.compare(j,j-1,comparator) < 0){
					list.swap(j-1,j);
					--j;
				}
			}

		}
		return list;
	}
}
