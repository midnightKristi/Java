package parallelMatrixMultiply;
/*
*   https://www.javaprogramto.com/2020/01/java-matrix-multiplication-threads.html
*/

public class RowMultiplier implements Runnable {

    private final int[][] result;
    private int[][] matrix1;
    private int[][] matrix2;
    private final int row;

    public RowMultiplier(int[][] result, int[][] matrix1, int[][] matrix2, int row) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
    }

    @Override
    public void run() {

        for (int i = 0; i < matrix2[0].length; i++) {
            result[row][i] = 0;
            for (int j = 0; j < matrix1[row].length; j++) {
                result[row][i] += matrix1[row][j] * matrix2[j][i];

            }

        }

    }
} // end row multiplier
