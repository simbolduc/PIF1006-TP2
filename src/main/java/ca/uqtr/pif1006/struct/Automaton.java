package ca.uqtr.pif1006.struct;

import java.util.ArrayList;
import java.util.List;

public class Automaton {

    private State initialState;
    private State currentState;
    private final List<State> states;

    public Automaton() {
        this.states = new ArrayList<>();
    }

    public boolean validate(final String input) {
        this.reset();

        if (input.length() == 0 && !this.currentState.isFinalState()) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            // Trouver une transition avec la même valeur input
            int charIndex = i;
            Transition transition = this.currentState.getTransitions()
                    .stream()
                    .filter(trans -> trans.getInput() == input.charAt(charIndex))
                    .findFirst()
                    .orElse(null);

            // Si aucune transition est enregistré avec cette valeur de input on ne valide pas l'expression
            if (transition == null) {
                return false;
            }

            // Avancer au prochain état
            this.currentState = transition.getTo();
        }

        // Valider si après avoir parcouru toutes les transitions, l'état est un état final
        return this.currentState.isFinalState();
    }

    private void reset() {
        this.currentState = this.initialState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State getInitialState() {
        return this.initialState;
    }

    public boolean isAutomatonDefined() {
        return this.initialState != null;
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public List<State> getStates() {
        return this.states;
    }

}
