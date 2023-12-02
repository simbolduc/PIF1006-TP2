package ca.uqtr.pif1006.struct;


import org.beryx.textio.TextTerminal;

import java.io.Serializable;

public class MatrixSystem implements Serializable {

    private Matrix2D A;
    private Matrix2D B;

    public MatrixSystem() {
    }

    public boolean isValid() {
        return false;
        // À compléter (0.25 pt)
        // Doit vérifier si la matrix A est carrée et si B est une matrice avec le même nb
        // de ligne que A et une seule colonne, sinon cela retourne faux.
        // Avant d'agir avec le système, il faut toujours faire cette validation
    }

    public Matrix2D solveUsingCramer() {
        return null;
        // À compléter (1 pt)
        // Doit retourner une matrice X de même dimension que B avec les valeurs des inconnus
    }

    public Matrix2D solveUsingInverseMatrix() {
        return null;
        // À compléter (0.25 pt)
        // Doit retourner une matrice X de même dimension que B avec les valeurs des inconnus
    }

    public Matrix2D solveUsingGauss() {
        return null;
        // À compléter (1 pts)
        // Doit retourner une matrice X de même dimension que B avec les valeurs des inconnus
    }


    public void print(TextTerminal<?> terminal) {
        // À compléter (0.5 pt)
        // Devrait retourner en format:
        //
        // 3x1 + 5x2 + 7x3 = 9
        // 6x1 + 2x2 + 5x3 = -1
        // 5x1 + 4x2 + 5x3 = 5
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
