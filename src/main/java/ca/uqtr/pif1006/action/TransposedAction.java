package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.menu.Menu;

public class TransposedAction extends Action {

    public TransposedAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        try {
            this.getMenu().getSystem().getA().transpose().print(this.getMenu().getTerminal());
        } catch (MatrixNotInitializedException ignored) {
            this.getMenu().showError("La matrice n'a pas été initialisée.");
        }
    }
}
