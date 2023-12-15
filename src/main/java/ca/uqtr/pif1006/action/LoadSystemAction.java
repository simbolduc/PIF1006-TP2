package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.menu.Menu;
import ca.uqtr.pif1006.struct.MatrixSystem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.lang3.StringUtils;
import org.beryx.textio.TextIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class LoadSystemAction extends Action {

    public LoadSystemAction(Menu menu) {
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
            path = "C:\\Users\\simbo\\OneDrive\\Desktop\\system.json";
        }

        File file = new File(path);

        // Valider que le chemin existe et qu'il s'agit bien d'un fichier
        if (!file.exists() || !file.isFile()) {
            this.getMenu().showError("Le chemin précisé n'est pas un fichier valide");
            return;
        }

        // Valider qu'il s'agit d'un fichier .txt
        final String filename = file.getName();
        if (!filename.substring(filename.lastIndexOf(".") + 1).equals("json")) {
            this.getMenu().showError("Le fichier doit être un .json");
            return;
        }

        Type type = new TypeToken<MatrixSystem>(){}.getType();
        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.getMenu().setSystem(gson.fromJson(reader, type));

        if (this.getMenu().getSystem().getA().getMatrix() == null) {
            this.getMenu().showError("La matrice A n'est pas valide.");
            return;
        }

        if (this.getMenu().getSystem().getB().getMatrix() == null) {
            this.getMenu().showError("La matrice B n'est pas valide.");
            return;
        }

        this.getMenu().getSystem().getA().print(this.getMenu().getTerminal());
        this.getMenu().getSystem().getB().print(this.getMenu().getTerminal());

        this.getMenu().showSuccess("Le système a bien été chargé!");
    }

}
