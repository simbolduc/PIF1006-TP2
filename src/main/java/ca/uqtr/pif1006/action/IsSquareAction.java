package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.menu.Menu;

public class IsSquareAction extends Action {

    public IsSquareAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        try {
            boolean isSquare = this.getMenu().getSystem().getA().isSquare();
            if (isSquare) {
                this.getMenu().show("La matrice A est carrée :)");
            } else {
                this.getMenu().show("La matrice A n'est pas carrée :(");
            }
        } catch (MatrixNotInitializedException ignored) {
            this.getMenu().showError("La matrice n'a pas été initialisée.");
        }
    }
}
