package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.menu.Menu;

public class ComatrixAction extends Action {

    public ComatrixAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        try {
            this.getMenu().getSystem().getA().comatrice().print(this.getMenu().getTerminal());
        } catch (MatrixNotInitializedException ignored) {
            this.getMenu().showError("La matrice n'a pas été initialisée.");
        }
    }
}
