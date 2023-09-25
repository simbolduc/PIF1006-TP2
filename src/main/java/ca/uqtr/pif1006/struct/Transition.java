package ca.uqtr.pif1006.struct;

public class Transition {

    private final char input;
    private final State to;

    public Transition(char input, State to) {
        this.input = input;
        this.to = to;
    }

    public char getInput() {
        return input;
    }

    public State getTo() {
        return to;
    }
}
