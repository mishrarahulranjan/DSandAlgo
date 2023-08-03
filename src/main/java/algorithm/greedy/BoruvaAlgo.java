package algorithm.greedy;

import java.util.*;

public class BoruvaAlgo {

    static class Graph_Edge {
        int v;
        int u;
        int cost;
        Graph_Edge(int v,int u,int cost)
        {
            this.v=v;
            this.u=u;
            this.cost=cost;
        }
    }

    static class Boruvka_MST {
        static int parent[] = new int[7];
        static int Min[] = new int[7];

        public static void main(String args[])
        {
            // No. of vertices in graph.
            int n=6;
            Graph_Edge g[]=new Graph_Edge[10];

            // Creating the graph with source, end and cost of each edge
            g[1]=new Graph_Edge(1,2,1);
            g[2]=new Graph_Edge(1,4,4);
            g[3]=new Graph_Edge(2,4,7);
            g[4]=new Graph_Edge(2,5,3);
            g[5]=new Graph_Edge(2,6,6);
            g[6]=new Graph_Edge(3,2,5);
            g[7]=new Graph_Edge(3,6,9);
            g[8]=new Graph_Edge(6,5,2);
            g[9]=new Graph_Edge(5,4,8);

            // Initializes parent of all nodes.
            init(n);

            int edges = g.length-1;

            int components = n;
            int ans_MST=0;

            while(components>1)
            {
                // Initialize Min for each component as -1.
                for(int i=1;i<=n;i++)
                {
                    Min[i]=-1;
                }

                for(int i=1;i<=edges;i++)
                {
                    // If both source and end are from same component we don't process them.
                    if(root(g[i].v)==root(g[i].u))
                        continue;

                    int r_v=root(g[i].v);
                    if(Min[r_v]==-1 || g[i].cost < g[Min[r_v]].cost)
                        Min[r_v]=i;

                    int r_u=root(g[i].u);
                    if(Min[r_u]==-1 || g[i].cost < g[Min[r_u]].cost)
                        Min[r_u]=i;

                }

                for(int i=1;i<=n;i++)
                {
                    if(Min[i]!=-1)
                    {
                        if(merge(g[Min[i]].v,g[Min[i]].u))
                        {
                            ans_MST+=g[Min[i]].cost;
                            components--;
                        }
                    }
                }
            }

            System.out.println("The Total Weight of Minimum Spanning Tree is : "+ans_MST);

        }

        static int root(int v)
        {
            if(parent[v]==v)
                return v;

            return parent[v]=root(parent[v]);
        }

        static boolean merge(int v,int u)
        {
            v=root(v);
            u=root(u);
            if(v==u)
                return false;
            parent[v]=u;
            return true;
        }

        static void init(int n)
        {
            for(int i=1;i<=n;i++)
            {
                parent[i]=i;
            }
        }

    }
}
