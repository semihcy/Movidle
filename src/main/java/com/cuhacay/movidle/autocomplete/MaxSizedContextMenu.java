package com.cuhacay.movidle.autocomplete;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.layout.Region;

public class MaxSizedContextMenu extends ContextMenu {

    public MaxSizedContextMenu() {
        addEventHandler(Menu.ON_SHOWING, e -> {
            Node content = getSkin().getNode();
            if (content instanceof Region) {
                ((Region) content).setMaxHeight(getMaxHeight());
            }
        });
    }
}