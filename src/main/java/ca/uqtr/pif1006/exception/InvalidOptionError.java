package ca.uqtr.pif1006.exception;

import org.beryx.textio.InputReader;

import java.util.ArrayList;
import java.util.List;

public class InvalidOptionError implements InputReader.ErrorMessagesProvider {

    @Override
    public List<String> getErrorMessages(String s, String s1) {
        List<String> ret = new ArrayList<>();
        ret.add("");
        ret.add("Argument invalide, veuillez entrer un nombre.");
        return ret;
    }
}
