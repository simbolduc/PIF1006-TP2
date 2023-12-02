package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.menu.Menu;

import java.io.FileNotFoundException;

public abstract class Action {

    private final Menu menu;

    public Action(Menu menu) {
        this.menu = menu;
    }

    public abstract void execute() throws FileNotFoundException;

    protected Menu getMenu() {
        return this.menu;
    }
}
