package algorithm.dp;

/**
 * Matrix chain problem (problem)
 * We have n matrices and we want to compute their product.
 *
 * Matrix multiplication is associative, so ABC for example can be calculated as A(BC) or (AB)C.
 *
 * But A(BC) and (AB)C can require a different number of operations to be computed.
 */
public class MatrixChainMultiplication {

    public static void main(String[] args){
        int[][] matrices = {{40, 20}, {20, 30}, {30, 10}, {10, 30}, {30, 50}};
        System.out.println("cost of matrix chain is "+matrixChain(matrices,0,matrices.length-1));
    }

    private static int matrixChain(int[][] matrices, int i, int j ){
        int[][] lookUp = new int [matrices.length][matrices.length];
        return matrixChain(matrices,i,j,lookUp);
    }

    private static int matrixChain(int[][] matrices, int i, int j , int[][] lookUp){

        if(lookUp[i][j] != 0){
            return lookUp[i][j];
        }

        if(i==j){
            return 0;
        }else{
            int min = Integer.MAX_VALUE;
            for(int k=i;k<j;k++){
                int left_cost       =  matrixChain(matrices,i,k);
                int right_cost      =  matrixChain(matrices, k+1, j);
                int product_cost    =  matrices[i][0]*matrices[k][1]*matrices[j][1];
                min                 =  Math.min(min, left_cost+right_cost+product_cost);
                lookUp[i][j]        = min;
            }
            return  lookUp[i][j] ;
        }
    }

    private static int matrixChain(int[][] matrices){
        return 0;
    }
}
