package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.exception.DeterminantNullException;
import ca.uqtr.pif1006.exception.InvalidSystemException;
import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.exception.MatrixNotSquaredException;
import ca.uqtr.pif1006.menu.Menu;
import ca.uqtr.pif1006.struct.Matrix2D;

public class SolveWithCramerAction extends Action {
    public SolveWithCramerAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        try {
            Matrix2D solution = this.getMenu().getSystem().solveUsingCramer();
            solution.print(this.getMenu().getTerminal());
        } catch (MatrixNotInitializedException e) {
            this.getMenu().showError("La matrice n'a pas été initialisée.");
        } catch (MatrixNotSquaredException e) {
            this.getMenu().showError("La matrice n'est pas carrée.");
        } catch (DeterminantNullException e) {
            this.getMenu().showError("Le déterminant est nul.");
        } catch (InvalidSystemException e) {
            this.getMenu().showError("Le système est invalide.");
        }
    }
}
