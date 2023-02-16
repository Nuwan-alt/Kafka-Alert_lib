package com.dev.application.service;

import com.dev.application.data.entity.AltertMessage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;

public class AlertGridManager extends ContextMenu {
    public AlertGridManager(Component target) {
        super(target);
        setOpenOnClick(true);
    }

    public void addColumnToggleItem(String label, Grid.Column<AltertMessage> column) {
        MenuItem menuItem = this.addItem(label, e -> {
            column.setVisible(e.getSource().isChecked());
        });
        menuItem.setCheckable(true);
        menuItem.setChecked(column.isVisible());
    }
}
