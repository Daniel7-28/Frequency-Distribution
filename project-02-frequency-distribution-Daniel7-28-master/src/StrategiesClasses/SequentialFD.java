package StrategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class corresponds to the Sequential strategy to count frequencies in an
 * array list.
 * @author Fernando J. Bermudez && Juan D. Pérez Sepúlveda
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SequentialFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public SequentialFD() {
		super("Sequential");
	}
	/**
	 * Method that counts the frequency of a dataSet with a regular ArrayList
	 * It uses Map.Entry to count the frequency of each elements inside dataSet
	 * one by one without any sorting or re-arrangement of the elements
	 * 
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {

		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>();
		
		for(E e : dataSet) {
			boolean entryFound = false; //verify if the elements already exist
			/*this loop iterate to all the ArrayList and compare if the element is equal to the second one comparing
			*if not, move to the other element.
			*then finally create a new entry element if is not equal to the first we are comparing
			*/
			
			for(int i = 0; i < results.size() && !entryFound; i++) {
				Map.Entry<E, Integer> entry = results.get(i);
				
				if(entry.getKey().equals(e)) {
					entry.setValue(entry.getValue() + 1);
					entryFound = true;
				}
			}
			if(!entryFound) {
				//creating new entry for the first instance found of object e
				Map.Entry<E,Integer> entry = new AbstractMap.SimpleEntry<E,Integer>(e, 1);
				results.add(entry);
			}
		}
		return results; 
	}
}
