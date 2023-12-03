package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.exception.DeterminantNullException;
import ca.uqtr.pif1006.exception.MatrixNotInitializedException;
import ca.uqtr.pif1006.exception.MatrixNotSquaredException;
import ca.uqtr.pif1006.menu.Menu;

public class InverseAction extends Action {

    public InverseAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        try {
            this.getMenu().getSystem().getA().inverse().print(this.getMenu().getTerminal());
        } catch (MatrixNotInitializedException ignored) {
            this.getMenu().showError("La matrice n'a pas été initialisée.");
        } catch (MatrixNotSquaredException e) {
            throw new RuntimeException(e);
        } catch (DeterminantNullException e) {
            this.getMenu().showError("Le déterminant de la matrice A est nul");
        }
    }
}
