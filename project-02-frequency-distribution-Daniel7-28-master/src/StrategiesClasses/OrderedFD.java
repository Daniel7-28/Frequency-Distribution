package StrategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 * This class implements the Ordered strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez && Juan D. Pérez Sepúlveda
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class OrderedFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public OrderedFD() {
		super("Ordered");
	}

	/**
	 * Method that counts the frequency of a dataSet with a SORTED COPY of dataSet
	 * It uses Map.Entry to count the frequency of each elements inside dataSet
	 * and pass the entries with it frequencies to a ArrayList
	 * 
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {

		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>();
		ArrayList<E> sortList = new ArrayList<>();
		sortList = dataSet;
		sortList.sort(null);

		int count = 0;	//count the times an element is repeated
		int trackingIndex = 0;	//keeps tracking the index
		boolean isEqual = true;	//verify if the element is equal or not
		
		/*
		 * this loop is to compare the next element with the previous one
		 * if is equal the variable count increase until the next element is different from previous
		 * trackingIndex is to dont lose where the index is
		 * if is not equal we change to the other element to count how many times repeats itself
		 * finally create a Map with the element and the number of time it repeats 
		 */
		
		for(int i = 1; i < sortList.size(); i++) {
			if(sortList.get(i-1).equals(sortList.get(i))) {
				count++;
				trackingIndex++;
				isEqual = true;
			}
			else {
				trackingIndex++;	
				isEqual = false;
			}
			if(!isEqual) {
			i = trackingIndex + 2;
			count = 0;
			}
			
			Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(sortList.get(i), count);
			results.add(entry);
			
			if(i >= sortList.size()) break;	
		}			
		return results; 
	}

}
