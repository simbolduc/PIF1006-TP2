package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.menu.Menu;
import ca.uqtr.pif1006.struct.Automaton;
import ca.uqtr.pif1006.struct.State;
import ca.uqtr.pif1006.struct.Transition;

import java.util.List;

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

        // Afficher tous les états
        List<State> states = this.getMenu().getAutomaton().getStates();
        StringBuilder stateBuilder = new StringBuilder();

        for (int i = 0; i < states.size(); i++) {
            stateBuilder.append(states.get(i).getName());
            if (states.get(i).isFinalState()) {
                stateBuilder.append("(F)"); // Si état final
            }

            if (i < states.size() - 1) {
                stateBuilder.append(", "); // Séparation entre états
            }
        }

        this.getMenu().showSuccess("Automate : ");
        this.getMenu().show("États possibles : ");
        this.getMenu().show(stateBuilder.toString());

        // Afficher les transitions
        this.getMenu().show("Transitions possibles : ");
        for (int i = 0; i < states.size(); i++) {
            StringBuilder transitionBuilder = new StringBuilder(states.get(i).getName() + " -> ");

            // Epsilon si état initiale
            if (i == 0) {
                transitionBuilder.append("ε, ");
            }

            for (int j = 0; j < states.get(i).getTransitions().size(); j++) {
                Transition transition = states.get(i).getTransitions().get(j);
                transitionBuilder.append(transition.getTo().getName()).append("(").append(transition.getInput()).append(")");

                if (j < states.get(i).getTransitions().size() - 1) {
                    transitionBuilder.append(", "); // Séparation entre transitions
                }
            }

            this.getMenu().show(transitionBuilder.toString());
        }

        this.getMenu().eol();
    }
}
