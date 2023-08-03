package algorithm.greedy;

import java.util.LinkedList;
import java.util.Queue;

public class HuffmanCodingForSortedData {

    static class Node{
        char data;
        int  frequency;
        Node left;
        Node right;

        Node(char data,int frequency, Node left, Node right){
            this.data       = data;
            this.frequency  = frequency;
            this.left       = left;
            this.right      = right;
        }
    }


    public static void main(String[] args){

        char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int freq[] = { 5, 9, 12, 13, 16, 45 };

        Queue<Node> firstQueue  = new LinkedList();
        Queue<Node> secondQueue = new LinkedList();

        for(int i=0;i<arr.length;i++){
            firstQueue.add(new Node(arr[i],freq[i],
                    null,null));
        }

        huffManCoding(firstQueue,secondQueue);
    }

    private static void huffManCoding(Queue<Node>  firstQueue, Queue<Node>  secondQueue) {
        Node parentNode = null;

        while(!firstQueue.isEmpty()  ||  !secondQueue.isEmpty()){
            Node fNode = findMin(firstQueue,secondQueue);
            Node sNode = findMin(firstQueue,secondQueue);

            if(fNode !=null && sNode!= null){
                Node pNode = new Node('$', fNode.frequency+ sNode.frequency,fNode,sNode);
                secondQueue.add(pNode);
            }else if(fNode !=null){
                parentNode = fNode;
            }else{
                parentNode = sNode;
            }

        }

        // print huffman coding
        printCode(parentNode,"");
    }

    public static void printCode(Node root, String s){
        if (root.left == null && root.right== null) {
            System.out.println(root.data + ":" + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    private static Node findMin(Queue<Node> fQueue, Queue<Node> sQueue) {
        if (fQueue.isEmpty() && sQueue.isEmpty()) {
            return null;
        }else if (fQueue.isEmpty()) {
            return sQueue.poll();
        }else if (sQueue.isEmpty()) {
            return fQueue.poll();
        }else{
            if(fQueue.peek().frequency < sQueue.peek().frequency){
                return fQueue.poll();
            }else{
                return sQueue.poll();
            }
        }
    }
}
