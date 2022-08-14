package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.UnboundedQueueInterface;
import structures.*;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem.
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */

	UnboundedQueueInterface<File> f;

	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
		if (!rootNode.exists()){
			throw new FileNotFoundException();
		}

		f = new Queue<File>();
		f.enqueue(rootNode);

	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
			boolean test = true;
			if (f.isEmpty()){
				test = false;
			}
            return test;
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
			if (f.isEmpty()){
				throw new NoSuchElementException();
			}
			File file = f.dequeue();

			if (file.isDirectory()){
				File[] children = file.listFiles();
				Arrays.sort(children);
				for (File child : children){
					f.enqueue(child);
				}
			}

            return file;
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}