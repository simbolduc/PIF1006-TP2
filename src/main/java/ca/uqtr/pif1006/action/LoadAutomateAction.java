package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.menu.Menu;
import ca.uqtr.pif1006.struct.State;
import ca.uqtr.pif1006.struct.Transition;
import org.apache.commons.lang3.StringUtils;
import org.beryx.textio.TextIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadAutomateAction extends Action {

    private List<State> states = new ArrayList<>();

    public LoadAutomateAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        TextIO textIO = this.getMenu().getTextIO();

        String path = textIO
                .newStringInputReader()
                .withDefaultValue(null)
                .withMinLength(0)
                .read("Veuillez entrer le chemin absolu du fichier");

        // Valeur par défaut pour les tests en local
        if (StringUtils.isBlank(path)) {
            path = "C:\\Users\\Sim\\Desktop\\automate.txt";
        }

        File file = new File(path);

        // Valider que le chemin existe et qu'il s'agit bien d'un fichier
        if (!file.exists() || !file.isFile()) {
            this.getMenu().showError("Le chemin précisé n'est pas un fichier valide");
            return;
        }

        // Valider qu'il s'agit d'un fichier .txt
        final String filename = file.getName();
        if (!filename.substring(filename.lastIndexOf(".") + 1).equals("txt")) {
            this.getMenu().showError("Le fichier doit être un .txt");
            return;
        }

        // Réinitialisation de la mémoire de State
        this.states = new ArrayList<>();

        // Charger les instructions ligne par ligne
        try {
            List<String> instructions = Files.readAllLines(file.toPath());
            for (String instruction : instructions) {
                // TODO: INSTRUCTION TYPE ERROR HANDLING -------------------------------------------
                String[] args = instruction.split(" ");
                String[] insArgs = new String[0];
                if (args.length > 1) {
                    insArgs = Arrays.copyOfRange(args, 1, args.length - 1);
                } else {
                    // TODO: DISPLAY ERROR IF NO INSTRUCTION ARG ? ----------------------
                }
                this.handleInstruction(args[0].trim().toUpperCase(), insArgs);
            }
        } catch (IOException e) {
            this.getMenu().showError("Impossible de lire le contenu d'instructions du fichier");
        } catch (IllegalArgumentException e) {
            this.getMenu().showError(e.getMessage());
        }
    }

    private void handleInstruction(String type, String... args) {

        if (type.equals("STATE")) {
            try {
                if (args.length < 2) {
                    throw new IllegalArgumentException("L'instruction 'STATE' doit être suivi de deux arguments : <nom du state> <est final {0, 1}>");
                }

                final String name = args[0];
                final boolean isFinal = parseBool(args[1]);

                State state = new State(name, isFinal);
                this.states.add(state);

                // Définir le State par défaut de l'automate
                if (this.getMenu().getAutomaton().getCurrentState() == null) {
                    this.getMenu().getAutomaton().setInitialState(state);
                }
            } catch (IllegalArgumentException e) {
                this.getMenu().showError(e.getMessage());
            }
            return;
        }

        if (type.equals("TRANSITION")) {
            try {
                if (args.length < 3) {
                    throw new IllegalArgumentException("L'instruction 'TRANSITION' doit être suivi de trois arguments : <nom du state initial> <valeur {0, 1}> <nom du state final>");
                }

                final String initialName = args[0];
                final String input = args[1];
                final String finalName = args[2];

                State initialState = this.states
                        .stream()
                        .filter(s -> s.getName().equalsIgnoreCase(initialName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("L'état initiale {" + initialName + "} n'a pas été trouvée"));

                State finalState = this.states
                        .stream()
                        .filter(s -> s.getName().equalsIgnoreCase(finalName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("L'état finale {" + finalName + "} n'a pas été trouvée"));

                if (input.length() > 1) {
                    throw new IllegalArgumentException("La valeur de transition doit être un caractère");
                }

                final char inputValue = input.charAt(0);

                if (inputValue != '0' && inputValue != '1') {
                    throw new IllegalArgumentException("La valeur de transition doit être 0 ou 1");
                }

                Transition transition = new Transition(inputValue, finalState);
                initialState.addTransition(transition);
            } catch (IllegalArgumentException e) {
                this.getMenu().showError(e.getMessage());
            }
            return;
        }

        this.getMenu().showError("Impossible de trouver le type d'instruction : {" + type + "}");
    }

    private boolean parseBool(String arg) throws IllegalArgumentException {
        if (arg.equals("1")) {
            return true;
        }

        if (arg.equals("0")) {
            return false;
        }

        throw new IllegalArgumentException("La valeur isFinal doit être un 0 ou un 1");
    }
}
