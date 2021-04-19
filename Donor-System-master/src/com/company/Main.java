package com.company;
import java.util.Scanner;

class ShortestPath {
    static final int V = 9;
    public int minDistance(int[] dist,Boolean[] sptSet){
        int min = Integer.MAX_VALUE;
        int minindex = -1;
        int x;
        for(x=0; x < V; x++)
        {
            if(!sptSet[x]  && dist[x] <= min)
            {
                min = dist[x];
                minindex = x;
            }
        
        }
        return minindex;
    }

    public void printSolution(int[] dist, int v , Integer r)
    {
        String[] btype = {"A+", "B+", "O+", "O-", "AB+", "AB-", "A-", "B-", "A+"};
        String[] local = {"Galle", "Matara", "Hambanthota", "Colombo", "Kaluthara", "Kandy", "Trino", "Jaffna", "Puttalam"};

        switch(r){
            case 1:    
                String type="O+";
                int min=1000;
                String loca="";
                System.out.format("%-15s %-25s","Vertex","Distance from Source");
                System.out.println();
                for (int j = 0; j < v; j++) 
                {
                    if (btype[j].equals(type))
                    {
                            System.out.format("%-15s %5s", local[j] , dist[j]);
                            System.out.println();
                            if (dist[j]<min)
                            {
                                min=dist[j];
                                loca=local[j];
                            }
                    }
                }
                System.out.println("");
                System.out.println("The Nearest Place with Requested blood type(" + type + ") is from :" + loca+" and the distance from source is " + min +"Km");
                break;
            case 2:

                for(int i=0 ; i<9; i++){
                    for(int k=0; k<8-i; k++){
                        if(btype[k].compareTo(btype[k+1])>0){
                            String t = btype[k];
                            btype[k]=btype[k+1];
                            btype[k+1]=t;

                            t = local[k];
                            local[k]= local[k+1];
                            local[k+1]=t;
                        }
                    }
                }
                System.out.format("%-15s %-25s","Blood Type","distrct");
                System.out.println();
                for(int i=0;i<8; i++){  
                    System.out.format("%-15s %5s", btype[i] , local[i]);
                    System.out.println();
                    
                }
                System.out.println("****************************************************************");
                System.out.println("");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }

    public void dijkstra(int[][] graph, int src,int r)
    {
        int[] dist = new int[V]; 
        Boolean[] sptSet = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

    
        dist[src] = 0;

        
        for (int count = 0; count < V; count++) {
            int u = minDistance(dist, sptSet);
    
            sptSet[u] = true;

            for (int x = 0; x < V; x++)
            {
                if (sptSet[x].equals(false) && graph[u][x] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][x] < dist[x])
                    dist[x] = dist[u] + graph[u][x];
            } 
            //int c = r;
            
            printSolution(dist, V, r);
            if(r>=2 || r==0)
            {
                break; 
            }
                 
        }
    }

    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("enter your choice");
        System.out.println("1=Search Shortest way to meet Donor");
        System.out.println("2=all the details");
        int r = sc.nextInt();
        
        int i = 0; 
        while(i==0)
        {
            int[][] graph = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };  
        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 5,r);

        System.out.println("enter your choice");
        System.out.println("1=Search Shortest way to meet Donor");
        System.out.println("2=all the details");
        r = sc.nextInt();

        }
        
    }
}