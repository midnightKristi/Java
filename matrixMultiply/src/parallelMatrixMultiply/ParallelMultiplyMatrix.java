package parallelMatrixMultiply;
/*
*  This is the modified matrix multiply using parallel threads.
*  Learned technique from: https://www.javaprogramto.com/2020/01/java-matrix-multiplication-threads.html
*/

public class ParallelMultiplyMatrix {

    public static void main(String[] args) {

        int[][] mY = { {1, 1, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2}, {3, 3, 3, 3, 3, 3}, {4, 4, 4, 4, 4, 4}, {5, 5, 5, 5, 5, 5}, {6, 6, 6, 6, 6, 6} };
        int[][] mZ = { {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6} };

        int[][] result = new int[mY.length][mZ[0].length];
        createParallelThreads.multiply(mY, mZ, result);

    }
}
