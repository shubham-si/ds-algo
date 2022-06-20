package misc;

import java.util.TreeMap;

/*

TreeMap: {1=One, 3=Three, 5=Five, 6=Six, 8=Eight, 10=Ten}
    Lower Key Entry of Element 6 is: 5
    (The lowerKey() method is used to return the greatest key strictly less than to given key)

    Floor Key Entry of Element 6 is: 6
    (floorKey() method is used to return the greatest key less than or equal to given key)
 */

public class TreeMapFloorLowerDemo {
    public static void main(String args[])
    {

        // create an empty TreeMap
        TreeMap<Integer, String>
                numMap = new TreeMap<Integer, String>();

        // Insert the values
        numMap.put(6, "Six");
        numMap.put(1, "One");
        numMap.put(5, "Five");
        numMap.put(3, "Three");
        numMap.put(8, "Eight");
        numMap.put(10, "Ten");

        // Print the Values of TreeMap
        System.out.println("TreeMap: " + numMap.toString());

        // Get the greatest key mapping of the Map

        // As here 9 is not available it returns 8
        // because 9 is strictly less than 11, present
        System.out.print("Lower Key Entry of Element 6 is: ");
        System.out.println(numMap.lowerKey(6));

        // Though, 3 is available in the Map
        // it returns 1 because this method returns
        // strictly less than key according to the specified key
        System.out.print("Floor Key Entry of Element 6 is: ");
        System.out.println(numMap.floorKey(6));
    }
}