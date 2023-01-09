public class MultiplyMatrices {


    public static void main(String[] args) {
        int r1 = 6, c1 = 6;
        int r2 = 6, c2 = 6;
        int[][] matrixY = { {1, 1, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2}, {3, 3, 3, 3, 3, 3}, {4, 4, 4, 4, 4, 4}, {5, 5, 5, 5, 5, 5}, {6, 6, 6, 6, 6, 6} };
        int[][] matrixZ = { {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6} };

        // Multiplying Two matrices
        int[][] productX;
        productX = multiplyMatrices(matrixY, matrixZ, r1, c1, c2);

        // Displaying the result
        displayProduct(productX);

        System.out.println("-------------------------------------------");
        System.out.println("Threading");


    } // end main

    public static int[][] multiplyMatrices(int[][] Y, int[][] Z, int r1, int c1, int c2) {
        int[][] X = new int[r1][c2];
        for(int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    X[i][j] += Y[i][k] * Z[k][j];
                } // inner for loop
            } // middle for loop
        } // outer for loop
        return X;
    } // end multiply matrices

    public static void displayProduct(int[][] product) {
        System.out.println("Product of two matrices is: ");
        for(int[] row : product) {
            for (int column : row) {
                System.out.print(column + "    ");
            }
            System.out.println();
        }
    } // end display product




}