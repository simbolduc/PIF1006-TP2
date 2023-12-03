package ca.uqtr.pif1006.action;

import ca.uqtr.pif1006.menu.Menu;

public class PrintSystemAction extends Action {

    public PrintSystemAction(Menu menu) {
        super(menu);
    }

    @Override
    public void execute() {
        this.getMenu().getSystem().print(this.getMenu().getTerminal());
    }
}
