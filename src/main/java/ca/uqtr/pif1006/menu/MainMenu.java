package ca.uqtr.pif1006.menu;

import ca.uqtr.pif1006.action.LoadSystemAction;
import ca.uqtr.pif1006.exception.InvalidOptionError;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

public class MainMenu extends Menu {

    private final static String[] MENU = {
            "1. Charger un automate par fichier",
            "2. Résoudre par Cramer",
            "3. Résoudr par Gauss",
            "4. Résoudr par Inverse",
            "5. Quitter",
            "Option : "
    };

    public MainMenu(TextIO textIO, TextTerminal<?> terminal) {
        super(textIO, terminal);
    }

    @Override
    public void show() {
        int action;
        do {
            action = this.getTextIO().newIntInputReader().withParseErrorMessagesProvider(new InvalidOptionError()).withMinVal(1).withMaxVal(MENU.length).read(MENU);

            switch (action) {
                case 1:
                    new LoadSystemAction(this).execute();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }

        } while (action != MENU.length - 1);

        System.exit(0);
    }
}
