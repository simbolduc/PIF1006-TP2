package ca.uqtr.pif1006.struct;


import ca.uqtr.pif1006.exception.DeterminantNullException;
import ca.uqtr.pif1006.exception.InvalidSystemException;
import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.exception.MatrixNotSquaredException;
import org.beryx.textio.TextTerminal;

import java.io.Serializable;
import java.util.stream.IntStream;

public class MatrixSystem implements Serializable {

    private Matrix2D A;
    private Matrix2D B;

    public MatrixSystem() {
    }

    public boolean isValid() {
        try {
            return this.A.isSquare() && this.B.getMatrix().length == this.A.getMatrix().length && this.B.getMatrix()[0].length == 1;
        } catch (MatrixNotInitializedException e) {
            return false;
        }
        // À compléter (0.25 pt)
        // Doit vérifier si la matrix A est carrée et si B est une matrice avec le même nb
        // de ligne que A et une seule colonne, sinon cela retourne faux.
        // Avant d'agir avec le système, il faut toujours faire cette validation
    }

    public Matrix2D solveUsingCramer() throws MatrixNotInitializedException, MatrixNotSquaredException, DeterminantNullException, InvalidSystemException {
        if (!this.isValid()) {
            throw new InvalidSystemException();
        }

        int n = this.A.getMatrix().length;
        double detA = this.A.determinant();

        if (detA == 0) {
            throw new DeterminantNullException();
        }

        double[][] solution = new double[n][1];

        for (int i = 0; i < n; i++) {
            double[][] Ai = remplacerColonne(A, i, B);

            Matrix2D matrixAi = new Matrix2D();
            matrixAi.setMatrix(Ai);
            double detAi = matrixAi.determinant();

            solution[i][0] = detAi / detA;
        }

        Matrix2D matrixSolution = new Matrix2D();
        matrixSolution.setMatrix(solution);
        return matrixSolution;
    }

    private double[][] remplacerColonne(Matrix2D a, int col, Matrix2D b) {
        int n = a.getMatrix().length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == col) {
                    result[i][j] = b.getMatrix()[i][0];
                } else {
                    result[i][j] = a.getMatrix()[i][j];
                }
            }
        }

        return result;
    }

    public Matrix2D solveUsingInverseMatrix() throws MatrixNotInitializedException, MatrixNotSquaredException, DeterminantNullException, InvalidSystemException {
        if (!this.isValid()) {
            throw new InvalidSystemException();
        }

        Matrix2D At = this.A.inverse();

        return this.produitMatricielle(At, this.B);
    }

    private Matrix2D produitMatricielle(Matrix2D A, Matrix2D B) {
        if (B.getMatrix().length != A.getMatrix().length) {
            return null;
        }

        Matrix2D result = new Matrix2D();
        double[][] matrix = new double[B.getMatrix().length][1];

        IntStream.range(0, 1).forEach(i ->
                IntStream.range(0, A.getMatrix().length).forEach(j -> {
                    double addition = IntStream.range(0, A.getMatrix()[0].length)
                            .mapToDouble(k -> A.getMatrix()[j][k] * B.getMatrix()[k][i])
                            .sum();

                    matrix[j][i] = addition;
                })
        );

        result.setMatrix(matrix);
        return result;
    }

    public Matrix2D solveUsingGauss() throws DeterminantNullException, InvalidSystemException {
        if (!this.isValid()) {
            throw new InvalidSystemException();
        }

        int n = this.A.getMatrix().length;

        double pivot;
        double ligne;
        double[][] matrixA = this.A.getMatrix();
        double[][] solution = this.B.getMatrix();

        for (int i = 0; i < n; i++) {
            pivot = matrixA[i][i];
            if (pivot == 0) {
                throw new DeterminantNullException();
            }

            for (int j = n - 1; j >= 0; j--) {
                if (i == j) continue;

                ligne = matrixA[j][i] / pivot;
                matrixA[j][i] = 0;
                for (int k = i + 1; k < n; k++) {
                    matrixA[j][k] -= matrixA[i][k] * ligne;
                }

                solution[j][0] -= solution[i][0] * ligne;
            }

            solution[i][0] /= pivot;
            for (int k = i; k < n; k++) {
                matrixA[i][k] /= pivot;
            }
        }

        Matrix2D solutionMatrix = new Matrix2D();
        solutionMatrix.setMatrix(solution);
        return solutionMatrix;
    }


    public void print(TextTerminal<?> terminal) {
        double[][] a = this.A.getMatrix();
        double[][] b = this.B.getMatrix();

        terminal.println();
        terminal.println("Solution : ");
        terminal.println();

        for (int i = 0; i < a.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < a[i].length; j++) {
                builder.append(a[i][j]).append("x").append(j).append(" ");
                if (j != a[i].length - 1) {
                    builder.append("+ ");
                }
            }

            builder.append("= ").append(b[i][0]);

            terminal.println(builder.toString());
        }

        terminal.println();
    }

    public Matrix2D getA() {
        return A;
    }

    public void setA(Matrix2D a) {
        A = a;
    }

    public Matrix2D getB() {
        return B;
    }

    public void setB(Matrix2D b) {
        B = b;
    }
}
