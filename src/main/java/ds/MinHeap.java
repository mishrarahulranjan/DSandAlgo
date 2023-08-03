package ds;

public class MinHeap {

    int[] data;
    int capacity;
    int size;

    public MinHeap(int[] data,int capacity){
        this.data =data;
        this.capacity=capacity;
        this.size=data.length;

        int i = (size - 1) / 2;
        while (i >= 0) {
            MinHeapify(i);
            i--;
        }

    }

    private void swap(int x, int y){
        int temp = data[x];
        data[x] = data[y];
        data[y] = temp;
    }

    void MinHeapify(int i){
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < size && data[l] < data[i])
            smallest = l;
        if (r < size && data[r] < data[smallest])
            smallest = r;
        if (smallest != i) {
            swap(i, smallest);
            MinHeapify(smallest);
        }
    }

    int parent(int i) { return (i - 1) / 2; }

    int left(int i) { return (2 * i + 1); }

    int right(int i) { return (2 * i + 2); }

    int extractMin(){
        if (size <= 0)
            return Integer.MAX_VALUE;
        if (size == 1) {
            size--;
            return data[0];
        }

        int root = data[0];
        data[0] = data[size - 1];
        size--;
        MinHeapify(0);
        return root;
    }

    void insertKey(int k){
        if (size == capacity) {
            System.out.println("Overflow: Could not insertKey");
            return;
        }

        size++;
        int i = size - 1;
        data[i] = k;

        while (i != 0 && data[parent(i)] > data[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public static void main(String[] args){
        int len[] = { 4, 3, 2, 6 };
        int size = len.length;

        System.out.println("MinHeap==> "+ new MinHeap(len, size));
    }
}
