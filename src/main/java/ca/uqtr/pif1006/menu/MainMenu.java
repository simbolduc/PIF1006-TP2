package ca.uqtr.pif1006.menu;

import ca.uqtr.pif1006.action.DisplayAutomatonAction;
import ca.uqtr.pif1006.action.LoadAutomateAction;
import ca.uqtr.pif1006.action.ValidateAutomatonInputAction;
import ca.uqtr.pif1006.exception.InvalidOptionError;
import ca.uqtr.pif1006.struct.Automaton;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

public class MainMenu extends Menu {

    private final static String[] MENU = {
            "1. Charger un automate par fichier",
            "2. Afficher l'automate",
            "3. Valider un input par l'automate",
            "4. Quitter"
    };

    public MainMenu(TextIO textIO, TextTerminal<?> terminal, Automaton automaton) {
        super(textIO, terminal, automaton);
    }

    @Override
    public void show() {
        int action;
        do {
            action = this.getTextIO().newIntInputReader().withParseErrorMessagesProvider(new InvalidOptionError()).withMinVal(1).withMaxVal(MENU.length).read(MENU);

            switch (action) {
                case 1:
                    new LoadAutomateAction(this).execute();
                    break;
                case 2:
                    new DisplayAutomatonAction(this).execute();
                    break;
                case 3:
                    new ValidateAutomatonInputAction(this).execute();
                    break;
            }

        } while (action != MENU.length);

        System.exit(0);
    }
}
