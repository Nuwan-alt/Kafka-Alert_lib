package com.dev.application.service;

import com.dev.application.data.entity.AltertMessage;
import com.dev.application.data.entity.Notification;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;

public class NotificationGridManager extends ContextMenu {

    public NotificationGridManager(Component target) {
        super(target);
        setOpenOnClick(true);
    }

    public void addColumnToggleItem(String label, Grid.Column<Notification> column) {
        MenuItem menuItem = this.addItem(label, e -> {
            column.setVisible(e.getSource().isChecked());
        });
        menuItem.setCheckable(true);
        menuItem.setChecked(column.isVisible());
    }
}
