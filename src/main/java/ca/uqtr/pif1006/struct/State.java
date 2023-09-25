package ca.uqtr.pif1006.struct;

import java.util.ArrayList;
import java.util.List;

public class State {

    private final boolean finalState;
    private final String name;
    private final List<Transition> transitions;

    public State(String name, boolean finalState) {
        this.name = name;
        this.finalState = finalState;
        this.transitions = new ArrayList<>();
    }

    public boolean isFinalState() {
        return finalState;
    }

    public String getName() {
        return name;
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public List<Transition> getTransitions() {
        return this.transitions;
    }
}
