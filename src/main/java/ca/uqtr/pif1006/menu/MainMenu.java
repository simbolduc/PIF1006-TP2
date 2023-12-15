package ca.uqtr.pif1006.menu;

import ca.uqtr.pif1006.action.*;
import ca.uqtr.pif1006.exception.InvalidOptionError;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

public class MainMenu extends Menu {

    private final static String[] MENU = {
            "1. Charger un système par fichier",
            "2. Afficher la matrice transposée",
            "3. Est carrée?",
            "4. Calculer le déterminant",
            "5. Afficher la comatrice",
            "6. Afficher la matrice inverse",
            "7. Résoudre par Cramer",
            "8. Résoudre par Gauss",
            "9. Résoudre par Inverse",
            "10. Afficher le système",
            "11. Quitter",
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
                    new TransposedAction(this).execute();
                    break;
                case 3:
                    new IsSquareAction(this).execute();
                    break;
                case 4:
                    new DeterminantAction(this).execute();
                    break;
                case 5:
                    new ComatrixAction(this).execute();
                    break;
                case 6:
                    new InverseAction(this).execute();
                    break;
                case 7:
                    new SolveWithCramerAction(this).execute();
                    break;
                case 8:
                    new SolveWithGaussAction(this).execute();
                    break;
                case 9:
                    new SolveWithInverseAction(this).execute();;
                    break;
                case 10:
                    new PrintSystemAction(this).execute();
                    break;
            }

        } while (action != MENU.length - 1);

        System.exit(0);
    }
}
