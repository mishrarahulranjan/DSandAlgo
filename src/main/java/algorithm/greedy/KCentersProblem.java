package algorithm.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KCentersProblem {

    static int maxindex(int[] dist, int n){
        int mi = 0;
        for(int i = 0; i < n; i++){
            if (dist[i] > dist[mi]){
                mi = i;
            }
        }
        return mi;
    }

    static int minindex(int[] dist){
        int mi = 0;
        for(int i = 0; i < dist.length; i++){
            if (dist[i] < dist[mi]){
                mi = i;
            }
        }
        return mi;
    }

    static void selectKcities(int n, int[][] weights, int k){

        List<List<Integer>> centers = new ArrayList<>();
        int[] distArray = new int[n];
        for(int node=0;node<n;node++){
            int max = node;
            List<Integer> center = new ArrayList<>(k);
            int[] dist = new int[n];
            for(int i = 0; i < n; i++){
                dist[i] = Integer.MAX_VALUE;
            }
            for(int i = 0; i < k; i++) {
                center.add(max);
                for(int j = 0; j < n; j++) {
                     dist[j] = Math.min(dist[j],weights[max][j]);
                }
                max = maxindex(dist, n);
            }
            distArray[node]=dist[max];
            centers.add(center.stream().collect(Collectors.toList()));
        }


        int min = minindex(distArray);
        System.out.println(distArray[min]);
        System.out.println( centers.get(min));
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] weights = new int[][]{
                { 0, 10, 7, 6 },
                { 10, 0, 8, 5 },
                { 7, 8, 0, 12 },
                { 6, 5, 12, 0 } };
        int k = 2;
        selectKcities(n, weights, k);
    }
}
