package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.menu.Menu;
import ca.uqtr.pif1006.struct.Automaton;

public class DisplayAutomatonAction extends Action {

    public DisplayAutomatonAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        final Automaton automaton = this.getMenu().getAutomaton();

        if (!automaton.isAutomatonDefined()) {
            this.getMenu().showError("L'automate n'a pas été définie");
            return;
        }

    }
}
