package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.menu.MainMenu;
import ca.uqtr.pif1006.struct.Automaton;
import org.beryx.textio.TextIO;

public class ValidateAutomatonInputAction extends Action {

    public ValidateAutomatonInputAction(MainMenu mainMenu) {
        super(mainMenu);
    }

    @Override
    public void execute() {
        TextIO textIO = this.getMenu().getTextIO();

        String input = textIO
                .newStringInputReader()
                .withDefaultValue(null)
                .withMinLength(0)
                .withPattern("^$|(0|1)+$")
                .read("Veuillez entrer un input à valider par l'automate", "La chaine doit être composée de 0 et de 1 seulement");

        Automaton automaton = this.getMenu().getAutomaton();

        if (!automaton.isAutomatonDefined()) {
            this.getMenu().showError("L'automate n'est pas complète. Veuillez configurer l'automate en premier lieu");
            return;
        }

        boolean valid = automaton.validate(input);

        if (valid) {
            this.getMenu().showSuccess("La chaine {" + input + "} est bien valide.");
        } else {
            this.getMenu().showError("La chaine {" + input + "} est invalide.");
        }
    }
}
