#include <stdio.h>
#define MAX 10

// STEP 1: Input a matrix
void inputMatrix(int mat[MAX][MAX], int *rows, int *cols, char name) {
    printf("\n--- Define Matrix %c ---\n", name);
    printf("Enter number of rows for Matrix %c: ", name);
    scanf("%d", rows);
    printf("Enter number of columns for Matrix %c: ", name);
    scanf("%d", cols);

    printf("\n--- Enter elements for Matrix %c (%dx%d) ---\n", name, *rows, *cols);
    for (int i = 0; i < *rows; i++)
        for (int j = 0; j < *cols; j++) {
            printf(" %c[%d][%d] = ", name, i, j);
            scanf("%d", &mat[i][j]);
        }
}

// STEP 2: Display a matrix
void displayMatrix(int mat[MAX][MAX], int rows, int cols, const char *label) {
    printf("\n%s:\n", label);
    for (int i = 0; i < rows; i++) {
        printf("  ");
        for (int j = 0; j < cols; j++)
            printf("%4d ", mat[i][j]);
        printf("\n");
    }
}

// STEP 3: Matrix Addition
void addMatrices(int A[MAX][MAX], int B[MAX][MAX], int result[MAX][MAX], int rows, int cols) {
    printf("\n--- Adding Matrices A and B ---\n");
    for (int i = 0; i < rows; i++)
        for (int j = 0; j < cols; j++)
            result[i][j] = A[i][j] + B[i][j];
}

// STEP 4: Matrix Multiplication
void multiplyMatrices(int A[MAX][MAX], int B[MAX][MAX], int result[MAX][MAX], int r1, int c1, int c2) {
    printf("\n--- Multiplying Matrices A and B ---\n");
    for (int i = 0; i < r1; i++)
        for (int j = 0; j < c2; j++) {
            result[i][j] = 0;
            for (int k = 0; k < c1; k++)
                result[i][j] += A[i][k] * B[k][j];
        }
}

// STEP 5: Transpose a matrix
void transposeMatrix(int mat[MAX][MAX], int trans[MAX][MAX], int rows, int cols) {
    for (int i = 0; i < rows; i++)
        for (int j = 0; j < cols; j++)
            trans[j][i] = mat[i][j];
}

// MAIN PROGRAM
int main() {
    int A[MAX][MAX], B[MAX][MAX], result[MAX][MAX], transA[MAX][MAX], transB[MAX][MAX];
    int r1, c1, r2, c2;
    int choice, subChoice;
    int operationDone = 0;

    printf("========================================================\n");
    printf("           Welcome to Matrix Operations in C!           \n");
    printf(" Matrix Operations: Addition, Multiplication, Transpose \n");
    printf("========================================================\n");

    inputMatrix(A, &r1, &c1, 'A');
    inputMatrix(B, &r2, &c2, 'B');

    while (1) {
        printf("\n==============================\n");
        printf(" Choose an Operation:\n");
        printf("  1. Add Matrices\n");
        printf("  2. Multiply Matrices\n");
        printf("  3. Transpose Matrices A and B\n");
        if (operationDone) {
            printf("  4. Exit\n");
        }
        printf("==============================\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        if (!operationDone && choice == 4) {
            printf("Invalid choice. Please perform an operation first.\n");
            continue;
        }

        switch (choice) {
            case 1:
                if (r1 == r2 && c1 == c2) {
                    addMatrices(A, B, result, r1, c1);
                    displayMatrix(result, r1, c1, "Result of A + B");
                } else {
                    printf("Error: Matrices must have the same dimensions for addition.\n");
                }
                operationDone = 1;
                break;

            case 2:
                if (c1 == r2) {
                    multiplyMatrices(A, B, result, r1, c1, c2);
                    displayMatrix(result, r1, c2, "Result of A x B");
                } else {
                    printf("Error: Columns of A must match rows of B for multiplication.\n");
                }
                operationDone = 1;
                break;

            case 3:
                transposeMatrix(A, transA, r1, c1);
                transposeMatrix(B, transB, r2, c2);
                displayMatrix(transA, c1, r1, "Transpose of Matrix A");
                displayMatrix(transB, c2, r2, "Transpose of Matrix B");
                operationDone = 1;
                break;

            case 4:
                if (operationDone) {
                    printf("\nExiting program. Goodbye!\n");
                    return 0;
                }
                break;

            default:
                printf("Invalid choice. Try again.\n");
        }

        if (operationDone) {
            printf("\nDo you want to:\n");
            printf("  1. Use new matrices\n");
            printf("  2. Continue with current matrices\n");
            printf("  3. Exit\n");
            printf("Enter your choice: ");
            scanf("%d", &subChoice);

            if (subChoice == 1) {
                inputMatrix(A, &r1, &c1, 'A');
                inputMatrix(B, &r2, &c2, 'B');
                operationDone = 0;
            } else if (subChoice == 3) {
                printf("\nExiting program. Thank You!\n");
                break;
            }
        }
    }

    return 0;
}