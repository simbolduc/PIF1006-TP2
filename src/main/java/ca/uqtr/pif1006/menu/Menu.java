package ca.uqtr.pif1006.menu;
import ca.uqtr.pif1006.struct.Automaton;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

public abstract class Menu {

    private final TextIO textIO;
    private final TextTerminal<?> terminal;
    private final Automaton automaton;

    public Menu(TextIO textIO, TextTerminal<?> terminal, Automaton automaton) {
        this.textIO = textIO;
        this.terminal = terminal;
        this.automaton = automaton;
    }

    public abstract void show();

    public void showError(String msg) {
        this.terminal.println();
        this.terminal.executeWithPropertiesPrefix("error", t -> t.println("[ERREUR] " + msg));
        this.terminal.println();
    }

    public void showSuccess(String msg) {
        this.terminal.println();
        this.terminal.executeWithPropertiesPrefix("success", t -> t.println("[SUCCÈS] " + msg));
        this.terminal.println();
    }

    public void show(String msg) {
        this.terminal.executeWithPropertiesPrefix("info", t -> t.println(msg));
    }

    public void eol() {
        this.terminal.println();
    }

    public TextIO getTextIO() {
        return this.textIO;
    }

    public TextTerminal<?> getTerminal() {
        return this.terminal;
    }

    public Automaton getAutomaton() {
        return automaton;
    }
}
