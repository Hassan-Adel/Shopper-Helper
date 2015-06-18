
package shopper_helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Shopper_helper {
    
    public static int minDist = 9999999;
    public static int [] minRoute;
    public static int [][] graph;
    
    public static void getMinDist(int [] departments, int n){
        
        int currentDist = 0;
        for(int i=0; i<n; i++)
            currentDist += graph[departments[i]][departments[(i + 1)]];
        
        if (currentDist < minDist){
            System.out.println(Arrays.toString(departments));
            //System.out.println("Distance: " + currentDist);
            minDist = currentDist;
            minRoute = departments.clone();
        }
    }
    
    public static void checkRoutes(int [] departments, int i, int n){
   
        if (i == n){
            //System.out.println("getMinDist(" + Arrays.toString(perm) + ", " + n + ")");
             getMinDist(departments, n);
        }
        else {
            
            for (int j = i; j <= n; j++){

               int temp = departments[j];
               departments[j] = departments[i];
               departments[i] = temp;

               checkRoutes(departments, i + 1, n);

               int temp1 = departments[j];
               departments[j] = departments[i];
               departments[i] = temp1;
            }
         }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        File in = new File("in.txt");
        Scanner reader = new Scanner(in);
        int n; //lines
        n = reader.nextInt();
        graph = new int [n][n];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                graph[i][j] = reader.nextInt();
            }
        }
        
        //int departments [] = {0, 1, 2, 3, 4, 5, 6};
        int [] departments = new int[n];
        for(int i=0; i<n; i++){
            departments[i] = i;
        }
        checkRoutes(departments, 0, (n-1));
        System.out.println("minRoute: " + Arrays.toString(minRoute));
        System.out.println("minDist: " + minDist);
        
        try (PrintWriter p = new PrintWriter(new File("out.txt"))) {
            
            for(int i=0; i<minRoute.length; i++)
                p.print(minRoute[i] + " ");
            
            p.println();
            p.print(minDist);
        }
    }
    
}
