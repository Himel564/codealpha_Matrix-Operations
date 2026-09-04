import java.util.Scanner;

public class MatrixOperations {

    // STEP 1: Input a matrix
    public static int[][] inputMatrix(Scanner scanner, char name) {
        System.out.printf("\n--- Define Matrix %c ---\n", name);
        System.out.print("Enter number of rows for Matrix " + name + ": ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns for Matrix " + name + ": ");
        int cols = scanner.nextInt();

        // Dynamically allocating the array based on user input
        int[][] mat = new int[rows][cols];
        System.out.printf("\n--- Enter elements for Matrix %c (%dx%d) ---\n", name, rows, cols);
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf(" %c[%d][%d] = ", name, i, j);
                mat[i][j] = scanner.nextInt();
            }
        }
        return mat;
    }

    // STEP 2: Display a matrix
    public static void displayMatrix(int[][] mat, String label) {
        System.out.printf("\n%s:\n", label);
        for (int i = 0; i < mat.length; i++) {
            System.out.print("  ");
            for (int j = 0; j < mat[i].length; j++) {
                System.out.printf("%4d ", mat[i][j]);
            }
            System.out.println(); // New line after each row
        }
    }

    // STEP 3: Matrix Addition
    public static int[][] addMatrices(int[][] A, int[][] B) {
        System.out.println("\n--- Adding Matrices A and B ---");
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // STEP 4: Matrix Multiplication
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        System.out.println("\n--- Multiplying Matrices A and B ---");
        int r1 = A.length;
        int c1 = A[0].length;
        int c2 = B[0].length;
        int[][] result = new int[r1][c2];
        
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < c1; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // STEP 5: Transpose a matrix
    public static int[][] transposeMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] trans = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                trans[j][i] = mat[i][j];
            }
        }
        return trans;
    }

    // MAIN PROGRAM
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] A = null, B = null, result = null;
        int choice, subChoice;
        boolean operationDone = false;

        System.out.println("========================================================");
        System.out.println("            Welcome to Matrix Operations in Java!       ");
        System.out.println(" Matrix Operations: Addition, Multiplication, Transpose ");
        System.out.println("========================================================");

        A = inputMatrix(scanner, 'A');
        B = inputMatrix(scanner, 'B');

        while (true) {
            System.out.println("\n==============================");
            System.out.println(" Choose an Operation:");
            System.out.println("  1. Add Matrices");
            System.out.println("  2. Multiply Matrices");
            System.out.println("  3. Transpose Matrices A and B");
            if (operationDone) {
                System.out.println("  4. Exit");
            }
            System.out.println("==============================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            if (!operationDone && choice == 4) {
                System.out.println("Invalid choice. Please perform an operation first.");
                continue;
            }

            switch (choice) {
                case 1:
                    if (A.length == B.length && A[0].length == B[0].length) {
                        result = addMatrices(A, B);
                        displayMatrix(result, "Result of A + B");
                    } else {
                        System.out.println("Error: Matrices must have the same dimensions for addition.");
                    }
                    operationDone = true;
                    break;

                case 2:
                    if (A[0].length == B.length) {
                        result = multiplyMatrices(A, B);
                        displayMatrix(result, "Result of A x B");
                    } else {
                        System.out.println("Error: Columns of A must match rows of B for multiplication.");
                    }
                    operationDone = true;
                    break;

                case 3:
                    int[][] transA = transposeMatrix(A);
                    int[][] transB = transposeMatrix(B);
                    displayMatrix(transA, "Transpose of Matrix A");
                    displayMatrix(transB, "Transpose of Matrix B");
                    operationDone = true;
                    break;

                case 4:
                    if (operationDone) {
                        System.out.println("\nExiting program. Goodbye!");
                        scanner.close();
                        return;
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

            if (operationDone) {
                System.out.println("\nDo you want to:");
                System.out.println("  1. Use new matrices");
                System.out.println("  2. Continue with current matrices");
                System.out.println("  3. Exit");
                System.out.print("Enter your choice: ");
                subChoice = scanner.nextInt();

                if (subChoice == 1) {
                    A = inputMatrix(scanner, 'A');
                    B = inputMatrix(scanner, 'B');
                    operationDone = false;
                } else if (subChoice == 3) {
                    System.out.println("\nExiting program. Thank You!");
                    scanner.close();
                    return;
                }
            }
        }
    }
}
