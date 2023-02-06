package StrategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import DataStructures.SortedList.*;

/**
 * This class implements the SortedList strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez && Juan D. Pérez Sepúlveda
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SortedListFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	/**
	 * Our SortedList classes require the data type to be Comparable.
	 * However, Map.Entry and AbstractMap.SimpleEntry are not Comparable,
	 * so we extend AbstractMap.SimpleEntry and create a Comparable
	 * version that we can use with our SortedList.
	 * Note: The K (key) of this class will be the E of SortedListFD,
	 *       so it will be Comparable.
	 * @author Fernando J. Bermudez
	 *
	 * @param <K>  The type of the key of each entry
	 * @param <V>  The type of the value of each entry
	 */
	@SuppressWarnings("serial")
	private static class ComparableEntry<K extends Comparable<K>, V> extends AbstractMap.SimpleEntry<K, V>
	implements Comparable<Map.Entry<K, V>> {

		public ComparableEntry(K key, V value) {
			super(key, value);
		}

		@Override
		public int compareTo(Map.Entry<K, V> entry) {
			/* Entries will be compared based on their keys, which are Comparable */
			return getKey().compareTo(entry.getKey());
		}

	} // End of ComparableEntry class

	/* Constructor */
	public SortedListFD() {
		super("SortedList");
	}

	/**
	 * Method that counts the frequency of a dataSet with a SortedList
	 * It uses Map.Entry to count the frequency of each elements inside dataSet
	 * Then it pass all the entries with the number of frequency of each one to an 
	 * ArrayList that contains Map.Entry
	 * 
	 * @author Juan D. Pérez Sepúlveda
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		SortedList<E> sortedElements = new SortedArrayList<>(dataSet.size());
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(dataSet.size());
		
		//this for loop adds the objects and the adds is responsible to put them in order
		for(int i = 0; i < dataSet.size(); i++) {
				sortedElements.add(dataSet.get(i)); 
		}
		
		//this for it use to compare with all the elements and to create a new entry to add to the ArrayList results
		for(int i = 0; i < sortedElements.size();) {
			int count = 0;
			int trackingIndex = 0;
			
			//this for count how many times an element is repeated 
			for(int j = 0; j < sortedElements.size(); j++) {
				if(sortedElements.get(i).compareTo(sortedElements.get(j)) == 0 && i < sortedElements.size()) {
					count++; //count the elements
				} else if(sortedElements.get(i).compareTo(sortedElements.get(j)) > 0 && i < sortedElements.size()) {
					trackingIndex++; //keep tracking the index
				} else break;
			}
			
			i = count + trackingIndex; 
			if(i >= sortedElements.size()) break;
			
			Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(sortedElements.get(i), count);
			results.add(entry);
			count = 0;
			trackingIndex = 0;
		}
		
		return results; 	
	}

}