package ca.uqtr.pif1006.struct;

import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.exception.MatrixNotSquaredException;
import org.beryx.textio.TextTerminal;

import java.io.Serializable;

public class Matrix2D implements Serializable {

    private double[][] matrix;

    public Matrix2D() {
    }

    public Matrix2D transpose() throws MatrixNotInitializedException {
        if (this.matrix == null || this.matrix.length == 0) {
            throw new MatrixNotInitializedException();
        }

        double[][] transposed = new double[this.matrix.length][this.matrix[0].length];

        for (int line = 0; line < this.matrix.length; line++) {
            for (int col = 0; col < this.matrix[line].length; col++) {
                transposed[col][line] = this.matrix[line][col];
            }
        }

        Matrix2D newMatrix = new Matrix2D();
        newMatrix.setMatrix(transposed);
        return newMatrix;
    }

    public boolean isSquare() throws MatrixNotInitializedException {
        if (this.matrix == null || this.matrix.length == 0) {
            throw new MatrixNotInitializedException();
        }

        return this.matrix.length == this.matrix[0].length;
    }

    public double determinant() throws MatrixNotInitializedException, MatrixNotSquaredException {
        if (this.matrix == null || this.matrix.length == 0) {
            throw new MatrixNotInitializedException();
        }

        if (!this.isSquare()) {
            throw new MatrixNotSquaredException();
        }

        return this.calculerDeterminant(this.matrix);
    }

    private double calculerDeterminant(double[][] matrix) {
        // Nombre de lignes
        int n = matrix.length;

        // Calcule pour 1x1
        if (n == 1) {
            return matrix[0][0];
        }

        // Calcule pour 2x2
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;

        // somme des produits des éléments de la 1ère ligne par leurs compléments algébriques correspondants
        for (int i = 0; i < n; i++) {
            determinant += matrix[0][i] * this.complementAlgebrique(matrix, 0, i);
        }

        return determinant;
    }

    private double complementAlgebrique(double[][] matrix, int line, int col) {
        int signe = (line + col) % 2 == 0 ? 1 : -1;
        return signe * this.calculerDeterminant(this.sousMatrice(matrix, line, col));
    }

    private double[][] sousMatrice(double[][] matrix, int line, int col) {
        // Nombre de ligne et columns - 1, car on retire la ligne et la colonne
        double[][] submatrix = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0, newLine = 0; i < matrix.length; i++) {
            if (i == line) {
                continue; // Sauter la ligne retirée
            }

            for (int j = 0, newCol = 0; j < matrix.length; j++) {
                if (j == col) {
                    continue; // Sauter la colonne retirée
                }

                // Déplacer la valeur dans la sous-matrice à sa nouvelle ligne-colonne
                submatrix[newLine][newCol] = matrix[i][j];
                newCol++;
            }

            newLine++;
        }

        return submatrix;
    }

    public Matrix2D comatrix() throws MatrixNotInitializedException {
        if (this.matrix == null || this.matrix.length == 0) {
            throw new MatrixNotInitializedException();
        }

        return null;
        // À compléter (1 pt)
        // Doit retourner une matrice qui est la comatrice de celle de l'objet
    }

    public Matrix2D inverse() throws MatrixNotInitializedException {
        if (this.matrix == null || this.matrix.length == 0) {
            throw new MatrixNotInitializedException();
        }

        return null;
        // À compléter (0.25 pt)
        // Doit retourner une matrice qui est l'inverse de celle de l'objet;
        // Si le déterminant est nul ou la matrice non carrée, on retourne null.
    }

    public void print(TextTerminal<?> terminal) {
        terminal.println();
        for (int line = 0; line < this.matrix.length; line++) {
            terminal.print("|");

            for (int col = 0; col < this.matrix[line].length; col++) {
                terminal.print(" " + this.matrix[line][col]);
            }

            terminal.println(" |");
        }
        terminal.println();
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

}
