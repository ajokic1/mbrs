package springplugin;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.MDActionsCategory;

public class MainMenuConfigurator implements AMConfigurator {
    private final String MENU = "Code Generation";
    private NMAction[] menuActions;

    public MainMenuConfigurator(NMAction[] menuActions) {
        this.menuActions = menuActions;
    }

    public void configure(ActionsManager manager) {

        ActionsCategory category = (ActionsCategory) manager.getActionFor(MENU);

        if (category == null) {
            category = new MDActionsCategory(MENU, MENU);
            category.setNested(true);
            manager.addCategory(category);
        }
        for (int i = 0; i < menuActions.length; i++) {
            category.addAction(menuActions[i]);
        }
    }

    public int getPriority() {
        return AMConfigurator.MEDIUM_PRIORITY;
    }

}