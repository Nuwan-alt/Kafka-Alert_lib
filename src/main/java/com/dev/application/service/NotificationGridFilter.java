package com.dev.application.service;

import com.dev.application.data.entity.AltertMessage;
import com.dev.application.data.entity.Notification;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

public class NotificationGridFilter {

    private final GridListDataView<Notification> dataView;

    private String level;
    private String ip;
    private String component;
    private String version;
    private String type;

    public NotificationGridFilter(GridListDataView<Notification> dataView) {
        this.dataView = dataView;
        this.dataView.addFilter(this::test);
    }



    public void setLevel(String level) {
        this.level = level;
        this.dataView.refreshAll();
    }

    public void setIp(String ip) {
        this.ip = ip;
        this.dataView.refreshAll();
    }
    public void setVersion(String version) {
        this.version = version;
        this.dataView.refreshAll();
    }

    public void setComponent(String component) {
        this.component = component;
        this.dataView.refreshAll();
    }
    public void setType(String type) {
        this.type = type;
        this.dataView.refreshAll();
    }

    public boolean test(Notification notification) {
        boolean matchesLevel = matches(notification.getLevel(), level);
        boolean matchesIp = matches(notification.getIp(), ip);
        boolean matchesComponent = matches(notification.getComponent(), component);
        boolean matchesVersion = matches(notification.getVersion(),version);
        boolean matchesType = matches(notification.getType(),type);

        return matchesLevel && matchesIp && matchesComponent && matchesVersion && matchesType;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || value.toLowerCase().contains(searchTerm.toLowerCase());
    }

}
