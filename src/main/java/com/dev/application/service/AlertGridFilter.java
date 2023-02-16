package com.dev.application.service;

import com.dev.application.data.entity.AltertMessage;
//import com.dev.application.data.entity.Notification;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

public class AlertGridFilter {
    private final GridListDataView<AltertMessage> dataView;

    private String level;
    private String ip;
    private String component;
    private String version;
    private String type;

    public AlertGridFilter(GridListDataView<AltertMessage> dataView) {
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

    public boolean test(AltertMessage alertMessage) {
        boolean matchesLevel = matches(alertMessage.getLevel(), level);
        boolean matchesIp = matches(alertMessage.getIp(), ip);
        boolean matchesComponent = matches(alertMessage.getComponent(), component);
        boolean matchesVersion = matches(alertMessage.getVersion(),version);
        boolean matchesType = matches(alertMessage.getType(),type);

        return matchesLevel && matchesIp && matchesComponent && matchesVersion && matchesType;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
