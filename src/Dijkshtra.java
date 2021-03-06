// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
import java.util.*;
import java.lang.*;
import java.io.*;

 class ShortestPath
{
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V=9;
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(int dist[], int n)
    {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i+" tt "+dist[i]);
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src)
    {
        int minDist[] = new int[V]; // The output array. minDist[i] will hold
        // the shortest distance from src to i

        // visitedSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean visitedSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            minDist[i] = Integer.MAX_VALUE;
            visitedSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        minDist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(minDist, visitedSet);

            // Mark the picked vertex as processed
            visitedSet[u] = true;

            // Update minDist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update minDist[v] only if is not in visitedSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of minDist[v]
                if (!visitedSet[v] && graph[u][v]!=0 &&
                        minDist[u] != Integer.MAX_VALUE &&
                        minDist[u]+graph[u][v] < minDist[v])
                    minDist[v] = minDist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(minDist, V);
    }

    // Driver method
    public static void main (String[] args)
    {
		/* Let us create the example graph discussed above */
        int graph[][] = new int[][]
                {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0);
    }
}

