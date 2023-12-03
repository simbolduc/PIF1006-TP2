package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.exception.DeterminantNullException;
import ca.uqtr.pif1006.exception.InvalidSystemException;
import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.exception.MatrixNotSquaredException;
import ca.uqtr.pif1006.menu.Menu;
import ca.uqtr.pif1006.struct.Matrix2D;

public class SolveWithGaussAction extends Action {
    public SolveWithGaussAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        try {
            Matrix2D solution = this.getMenu().getSystem().solveUsingGauss();
            solution.print(this.getMenu().getTerminal());
        } catch (DeterminantNullException e) {
            this.getMenu().showError("Une division par zéro, impossible de trouver la solution.");
        } catch (InvalidSystemException e) {
            this.getMenu().showError("Le système est invalide.");
        }
    }
}
