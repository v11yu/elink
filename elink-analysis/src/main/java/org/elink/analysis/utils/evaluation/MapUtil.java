package org.elink.analysis.utils.evaluation;
import java.util.*;
public class MapUtil {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
	{
	    List<Map.Entry<K, V>> list =
	        new LinkedList<Map.Entry<K, V>>( map.entrySet() );
	    Collections.sort( list, new Comparator<Map.Entry<K, V>>()
	    {
	        public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
	        {
	            return (o2.getValue()).compareTo( o1.getValue() );
	        }
	    } );
	
	    Map<K, V> result = new LinkedHashMap<K, V>();
	    for (Map.Entry<K, V> entry : list)
	    {
	        result.put( entry.getKey(), entry.getValue() );
	    }
	    return result;
	}
	public static void main(String[] args) {
		Random random = new Random(System.currentTimeMillis());
        Map<String, Double> testMap = new HashMap<String, Double>();
        for(int i = 0 ; i < 100 ; ++i) {
            testMap.put( "SomeString" + random.nextDouble(), random.nextDouble());
        }
        testMap = MapUtil.sortByValue( testMap );
        for(Map.Entry<String, Double> entry : testMap.entrySet()) {
        	System.out.println(entry.getValue());
        }
	}
}
