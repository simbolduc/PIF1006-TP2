package ca.uqtr.pif1006.menu;
import ca.uqtr.pif1006.struct.MatrixSystem;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

public abstract class Menu {

    private final TextIO textIO;
    private final TextTerminal<?> terminal;
    private MatrixSystem system;

    public Menu(TextIO textIO, TextTerminal<?> terminal) {
        this.textIO = textIO;
        this.terminal = terminal;
    }

    public abstract void show();

    public TextTerminal<?> getTerminal() {
        return this.terminal;
    }

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

    public MatrixSystem getSystem() {
        return system;
    }

    public void setSystem(MatrixSystem system) {
        this.system = system;
    }
}
