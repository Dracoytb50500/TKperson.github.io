public class Matrix {
    private final int[][] matrix;

    // store matrix
    public Matrix(int[][] matrix) {
        this.matrix = matrix;
        // swap inner
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length / 2; j++) {
                int temp = matrix[i][matrix[i].length - j - 1];
                matrix[i][matrix[i].length - j - 1] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        // sawp outer
        for(int i = 0; i < matrix.length / 2; i++) {
            int[] temp = matrix[matrix.length - i - 1].clone();
            matrix[matrix.length - i - 1] = matrix[i].clone();
            matrix[i] = temp;
        }
    }

    // Hack: create toString method using nested for loops to format output of a matrix

    public String toString() {
        String output = "";
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) 
                output += matrix[i][j] < 0 ? " ": matrix[i][j] + " ";
            output += "\n";
        }
        return output;
    }
    

    // declare and initialize a matrix for a keypad
    static int[][] keypad() {
        return new int[][]{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, {-1, 0, -1} };
    }

    // declare and initialize a random length arrays
    static int[][] numbers() {
        return new int[][]{ { 0, 1 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };
    }

    // tester method for matrix formatting
    public static void main(String[] args) {
        Matrix m0 = new Matrix(keypad());
        System.out.println("Keypad:");
        System.out.println(m0);

        Matrix m1 = new Matrix(numbers());
        System.out.println("Numbers Systems:");
        System.out.println(m1);
    }

}