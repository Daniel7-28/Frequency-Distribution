package StrategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class implements the Map/Hash table strategy to count frequencies in an ArrayList.
 * @author Fernando J. Bermudez && Juan D. Pérez Sepúlveda
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class MapFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public MapFD() {
		super("Map");
	}
	
	/**
	 * Method that counts the frequency of a dataSet with a Hash table
	 * It uses a Hash table to count the frequency of each elements inside dataSet instead of Map.Entry
	 * like the previous strategies in this experiment
	 * 
	 * @param dataSet	The Data Set to count and distribute the frequency of its elements
	 * @return results 	The resulting entries of each elements inside dataSet
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		Hashtable<E, Integer> buckets = new Hashtable<E, Integer>(dataSet.size());
		ArrayList<Entry<E, Integer>> results = new ArrayList<Entry<E, Integer>>();
		buckets.hashCode();
		
		/*
		 * in this we verify if the key is in the hHshtable
		 * if not, we add to the Hashtable
		 * if it is, then we modify it value incrementing by one every time the element is repeated 
		 */
		for (int i = 0; i < dataSet.size(); i++) {
			if(!buckets.containsKey(dataSet.get(i))) {
				buckets.put(dataSet.get(i), 1);				
			}
			else {
				buckets.computeIfPresent(dataSet.get(i), (key, val) -> + 1); // this verify if is present and modify his value incrementing it by one
			}
		}
		//this loop pass all the hashtable to a Arraylist that store Map.entry
		for (Map.Entry<E, Integer> entry : buckets.entrySet()) {
			E key = entry.getKey();
			Integer val = entry.getValue();
			Entry<E, Integer> e = new AbstractMap.SimpleEntry<E,Integer>(key, val);
			results.add(e);		
		}

		return results; 
	}

}
