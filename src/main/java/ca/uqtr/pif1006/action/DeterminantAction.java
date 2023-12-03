package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.exception.MatrixNotSquaredException;
import ca.uqtr.pif1006.menu.Menu;

public class DeterminantAction extends Action {

    public DeterminantAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        try {
            double determinant = this.getMenu().getSystem().getA().determinant();
            this.getMenu().show("Le déterminant de la matrice A est : " + String.format("%.2f", determinant));
        } catch (MatrixNotInitializedException ignored) {
            this.getMenu().showError("La matrice n'a pas été initialisée.");
        } catch (MatrixNotSquaredException e) {
            this.getMenu().showError("La matrice A n'est pas carrée.");
        }
    }
}
