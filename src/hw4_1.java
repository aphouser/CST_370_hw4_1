/*
 * HackerRank link:https://www.hackerrank.com/contests/cst370-su20-hw4/challenges/tsp-2-1/submissions/code/1323589218
 * Title: hw4_1.java
 * Abstract: TSP problem, user inputs vertices, edges, and paths with cost.  Program calculates all permutations
 *          of the number of cities, then outputs the lowest cost path if it exists.
 * Author: Adam Houser
 * ID: 1144
 * Date: 5/14/2020
 */

import java.util.ArrayList;
import java.util.Scanner;

public class hw4_1 {

    public static void main(String[] args) {

       ArrayList<String> cities = new ArrayList();
       ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
       String pString = "";

       Scanner in = new Scanner(System.in);

        // get vertices
        int v = in.nextInt();

        // add cities to list for future lookup
        for (int i = 0; i < v; i++) {
            cities.add(in.next());
        }

        // generate pString for permutation
       for (int i = 1; i < v; i++) {
          pString = pString + i;
       }

        // get edges
        int e = in.nextInt();

       // create a list for each vertex
       for (int i = 0; i < v; i++) {
          ArrayList<Integer> costs = new ArrayList<Integer>();
          list.add(costs);
       }

       // populate them all with 0 to start with
       for (int i = 0; i < v; i++){
          for (int j = 0; j < v; j++){
             list.get(i).add(0);
          }
       }

       // add edge mapping for each
       for (int i = 0; i < e; i++){
          String temp1 = in.next();
          String temp2 = in.next();
          int temp3 = in.nextInt();
          list.get(cities.indexOf(temp1)).set(cities.indexOf(temp2), temp3);
       }

       in.close();

       // create permutations
       ArrayList<String> permutes = permutation(pString);

       // calculate cost for each permutation
       int lowest = 0;
       int lowestIndex = -1;
       int total = 0;
       int counter = 0;

       for (int i = 0; i < permutes.size(); i++) {
          // temp string to add starting location to front and end
          String temp = "0" + permutes.get(i) + "0";

          // for each pair of numbers in the string get the cost from matrix
          for (int j = 0; j < temp.length()-1; j++) {
             int source = (temp.charAt(j)-'0');
             int dest = (temp.charAt(j+1)-'0');
             int subTotal = list.get(source).get(dest);

             // check if it is a valid cost ie > 0.  If 0 no route
             if (subTotal > 0) {
                counter++;
             }

             // sum up
             total = total + subTotal;
          }

          // use counter to see if we went to all cities
          if (counter == v && (total < lowest || lowest == 0)) {
             lowest = total;
             lowestIndex = i;
          }

          // reset for next permutation
          total = 0;
          counter = 0;
       }

       // if valid route
       if(lowest > 0) {
          System.out.print("Path:" + cities.get(0) + "->");
          for (int i = 0; i < permutes.get(lowestIndex).length(); i++) {
             System.out.print(cities.get(permutes.get(lowestIndex).charAt(i)-'0') + "->");
          }
          System.out.println(cities.get(0));
          System.out.println("Cost:" + lowest);
       }
       // invalid route
       else {
          System.out.println("Path:");
          System.out.println("Cost:-1");
       }
    }

    // generate permutations
    public static ArrayList<String> permutation(String str)
    {

       // If string is empty
       if (str.length() == 0) {

          // Return an empty arraylist
          ArrayList<String> empty = new ArrayList<>();
          empty.add("");
          return empty;
       }

       // Take first character of str
       char ch = str.charAt(0);

       // Take sub-string starting from the second character
       String subStr = str.substring(1);

       // Recurvise call
       ArrayList<String> prevResult = permutation(subStr);

       // Store the generated permutations into the resultant arraylist
       ArrayList<String> result = new ArrayList<>();

       for (String val : prevResult) {
          for (int i = 0; i <= val.length(); i++) {
             result.add(val.substring(0, i) + ch + val.substring(i));
          }
       }

       // Return the resultant arraylist
       return result;
    }


}
