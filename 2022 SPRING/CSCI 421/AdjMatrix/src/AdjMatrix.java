/*
    references used:
    https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/
    https://www.geeksforgeeks.org/implementation-of-dfs-using-adjacency-matrix/
    https://www.softwaretestinghelp.com/java-graph-tutorial/
    https://github.com/ganesh-shivashankar/Algorithms---Java/blob/89f568eb9776047e24f05f1d528a5becb44dd07f/Graphs/Breadth_First_Search.java
 */

import java.util.*;
import java.io.*;

public class AdjMatrix {

        static int adjMat[][];
        static Boolean visited_dfs[]; // to prevent revisiting
        static Boolean visited_bfs[]; // to prevent revisiting

        public static void dfs_helper(int node){
            visited_dfs[node] = true; //note visited node
            System.out.print(node+" ");

            for(int i = 0; i< adjMat[node].length; i++){
                if(adjMat[node][i] == 1 && visited_dfs[i] == false)
                    dfs_helper(i);  // If the neighbour node is unvisited go to dfs helper function
            }
        }

        public static void dfs(){
            for(int i = 0; i< adjMat.length; i++){
                if(visited_dfs[i] == false)
                    dfs_helper(i);
            }
        }

        public static void bfs_helper(int node){
            Queue<Integer> q = new LinkedList<Integer>();

            q.add(node);
            visited_bfs[node] = true;       // Exploring the starting node of the current component

            while(q.isEmpty() == false){
                int size = q.size();        // Size of nodes currently in queue

                for(int i = 0; i<size; i++){     // Exploring all the nodes in current level
                    int curr = q.peek();
                    System.out.print(q.remove()+" ");       // printing the traversal

                    for(int j = 0; j< adjMat[curr].length; j++){
                        if(adjMat[curr][j] == 1 && visited_bfs[j] == false){
                            visited_bfs[j] = true;  // Exploring neighbours
                            q.add(j);       // Adding neighbor to queue
                        }
                    }
                }
            }
        }

        public static void bfs(){
            for(int i = 0; i< adjMat.length; i++){
                if(visited_bfs[i] == false)
                    bfs_helper(i);
            }
        }

        public static void main(String []args) throws FileNotFoundException{
            File file = new File("./input.txt");    // Creating a file object for input
            Scanner input = new Scanner(file);

            int n = Integer.parseInt(input.nextLine());

            adjMat = new int[n][n];        // Adjacency matrix for graph
            visited_dfs = new Boolean[n];   // Array to keep track which node is visited_dfs in dfs
            visited_bfs = new Boolean[n];   // Array to keep track which node is visited_bfs in bfs


            for(int i = 0; i<n; i++){
                // Taking row by row input
                visited_dfs[i] = false;     // Initialize visited_dfs array to all false
                visited_bfs[i] = false;     // Initialize visited_bfs array to all false
                String []row = input.nextLine().split(" ");

                for(int j = 0; j < n; j++){
                    adjMat[i][j] = Integer.parseInt(row[j]);       // Converting string inputs to integer
                }
            }

            // Display the results for each
            System.out.print("DFS Traversal Results:");
            dfs();
            System.out.print("\nBFS Traversal Results:");
            bfs();
        }
}


