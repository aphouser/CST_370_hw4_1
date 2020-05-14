import java.util.ArrayList;
import java.util.Scanner;

public class hw4_1 {

    static ArrayList<String> permutations = new ArrayList<>();

    public static void main(String[] args) {

        ArrayList<String> cities = new ArrayList<String>();
        int sum = 0;
        int lowest = 0;
        int route = 0;

        Scanner in = new Scanner(System.in);

        // get vertices
        int v = in.nextInt();

        // create array with the verticies for heapPermutation use
        int sortArray[] = new int[v-1];
        int costArray[][] = new int[v][v];

        // populate array for heapPermutation use
        for (int i = 0; i < v-1; i++) {
            sortArray[i] = i+1;
        }

        // add cities to list for future lookup
        for (int i = 0; i < v; i++) {
            cities.add(in.next());
        }

        // get edges
        int e = in.nextInt();

        // populate costs
        // costs is my matrix
        // each row(sub arraylist) is the starting city index
        // then each item in the row is the ending city index
        // and value is the cost
        // so costs(0).item(4) is the cost from city 0 to city 4
        // for each line find start city and end city
        for (int j = 0; j < e; j++) {
            costArray[cities.indexOf(in.next())][cities.indexOf(in.next())]= in.nextInt();
        }

        // generate permutations and populate to arraylist
        permutation(sortArray, sortArray.length, sortArray.length);

        // for each entry in permutations, find the cost
        for (int x = 0; x < permutations.size(); x++) {
            String holder = permutations.get(x);
            for (int y = 0; y < v; y++) {
                // if the value in costArray for [x][y] > 0 then add to sum
                if(costArray[holder.charAt(y) - '0'][holder.charAt(y+1) - '0'] == 0) {
                    sum = 0;
                    break;
                }
                else {
                    sum = sum + costArray[holder.charAt(y) - '0'][holder.charAt(y+1) - '0'];
                }
            }

            if(sum < lowest || lowest == 0) {
                lowest = sum;
                route = x;
            }

            sum = 0;
        }

        String output = permutations.get(route);

        if (lowest == 0) {
            System.out.println("Path:");
            System.out.println("Cost:-1");
        }
        else {
            System.out.print("Path:");
            for (int z = 0; z < output.length()-1; z++) {
                System.out.print(cities.get((output.charAt(z)-'0')) + "->");
            }
            System.out.println(cities.get((output.charAt(output.length()-1))-'0'));
            System.out.println("Cost:" + lowest);
        }
    }

    // below Heap's algorithm to generate permutations
    // general Heap's algorithm from geeksforgeeks, updated for this project to add to arraylist
    // creates temp string and adds to arraylist
    static void printArr(int a[], int n) {
        String temp = "";
        temp = temp + "0";
        for (int i = 0; i < n; i++)
            temp = temp + a[i];
        temp = temp + "0";
        permutations.add(temp);
    }

    //Generating permutation using Heap Algorithm
    static void permutation(int array[], int size, int n) {
        // if size becomes 1 then prints the obtained permutation
        if (size == 1)
            printArr(array, n);

        for (int i = 0; i < size; i++) {
            permutation(array, size - 1, n);

            // if size is odd, swap first and last element
            if (size % 2 == 1) {
                int temp = array[0];
                array[0] = array[size - 1];
                array[size - 1] = temp;
            }

            // If size is even, swap ith and last element
            else {
                int temp = array[i];
                array[i] = array[size - 1];
                array[size - 1] = temp;
            }
        }

    }
}
