package cn.ist.simulation.simulation.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VersionVectorUtil {

    /**
     * Return the difference of two version vectors. V1 and V2 are only comparable
     * if V1[j] >= V2[j] where j != index
     * 
     * @param V1
     * @param V2
     * @return Integer.MAX_VALUE - uncomparable
     */
    public static int compare(Map<Integer, Integer> V1, Map<Integer, Integer> V2, int index) {
        if (V1.size() == V2.size()) {
            for (Entry<Integer, Integer> entry : V1.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (key != index && value < V2.get(key)) {
                    return Integer.MAX_VALUE;
                }
            }
            return V1.get(index) - V2.get(index);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Merge two version vectors and return a new one in the format same as the
     * first input
     * 
     * @param V1 the same format as the return version vector
     * @param V2
     * @return
     */
    public static Map<Integer, Integer> merge(Map<Integer, Integer> V1, Map<Integer, Integer> V2) {
        Map<Integer, Integer> V3 = new HashMap<Integer, Integer>();
        for (Entry<Integer, Integer> entry : V1.entrySet()) {
            Integer key = entry.getKey();
            if (V2.containsKey(entry.getKey())) {
                V3.put(entry.getKey(), Math.max(V1.get(key), V2.get(key)));
            } else {
                V3.put(entry.getKey(), entry.getValue());
            }
        }
        return V3;
    }

    /**
     * 
     * @param V1
     * @return
     */
    public static Map<Integer, Integer> increase(Map<Integer, Integer> V1, int index) {
        Map<Integer, Integer> tempV = new HashMap<Integer, Integer>();
        tempV.putAll(V1);
        if (tempV.containsKey(index)) {
            tempV.put(index, tempV.get(index) + 1);
        } else {
            tempV.put(index, 1);
        }
        return tempV;
    }

    /**
     * 
     * @param V1
     * @return
     */
    public static Map<Integer, Integer> duplicate(Map<Integer, Integer> V1) {
        Map<Integer, Integer> tempV = new HashMap<Integer, Integer>();
        tempV.putAll(V1);
        return tempV;
    }
}