package com.dev.application.views.Notification;

import com.dev.application.data.entity.AltertMessage;
import com.dev.application.data.entity.Notification;
import com.dev.application.service.*;
import com.dev.application.views.Alert.AlertPopupView;
import com.dev.application.views.MainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.function.Consumer;


import static com.dev.application.service.FormService.notifications;

@PageTitle("Notifications")
@Route(value = "notification", layout = MainLayout.class)
public class NotificationListView extends VerticalLayout {

    private FormService formService = new FormService();
    private NotificationPopupView popupview;
    Grid<Notification> grid = new Grid<>(Notification.class, false);
    Button gridArrangeButton = new Button("Show/Hide Columns");

    private Thread thread;


    public NotificationListView() {


        this.popupview = new NotificationPopupView();
        addClassName("List-view");
        setSizeFull();

//        configurationGrid();
        configurationForm();

        updateList();
        add(getSearchPanel(), grid, popupview.pop());


    }


    private void updateList() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();

        Grid.Column<Notification> level = grid.addColumn(Notification::getLevel);

        Grid.Column<Notification> component = grid.addColumn(Notification::getComponent);
        Grid.Column<Notification> version = grid.addColumn(Notification::getVersion);
        Grid.Column<Notification> ip = grid.addColumn(Notification::getIp);
        Grid.Column<Notification> type = grid.addColumn(Notification::getType);

        grid.getColumns().forEach(column -> column.setAutoWidth(true));


        grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES);


        gridArrangeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);


        NotificationGridManager columnToggleContextMenu = new NotificationGridManager(gridArrangeButton);
        columnToggleContextMenu.addColumnToggleItem("Level", level);
        columnToggleContextMenu.addColumnToggleItem("Component", component);
        columnToggleContextMenu.addColumnToggleItem("Version", version);
        columnToggleContextMenu.addColumnToggleItem("Ip", ip);
        columnToggleContextMenu.addColumnToggleItem("Type", type);

        grid.asSingleSelect().addValueChangeListener(e -> popupview.openPopupMenu(e.getValue()));

//        GridListDataView<AltertMessage> dataView = grid.setItems(alertMessages);

        System.out.println(notifications.size() + " Number of Notifications in the list");



        GridListDataView<Notification> dataView = grid.setItems(notifications);

        NotificationGridFilter gridFilter = new NotificationGridFilter(dataView);

        //grid  filters

        grid.getHeaderRows().clear();
        HeaderRow headerRow = grid.appendHeaderRow();


        headerRow.getCell(level).setComponent(
                createFilterHeader("Level", gridFilter::setLevel));
        headerRow.getCell(ip).setComponent(
                createFilterHeader("Ip", gridFilter::setIp));
        headerRow.getCell(component).setComponent(
                createFilterHeader("Component", gridFilter::setComponent));
        headerRow.getCell(version).setComponent(
                createFilterHeader("Version", gridFilter::setVersion));
        headerRow.getCell(type).setComponent(
                createFilterHeader("Type", gridFilter::setType));

//    }


        //grid end filters
//        grid.setItems(formService.findAllAlertMessages());
    }


    private void configurationForm() {


    }

    private Component getSearchPanel() {

        HorizontalLayout toolBar = new HorizontalLayout();

        Button test = new Button("LOAD",e->{grid.getDataProvider().refreshAll();});

        toolBar.setWidth("100%");
        toolBar.setDefaultVerticalComponentAlignment(Alignment.END);
        toolBar.add(gridArrangeButton,test);
        return toolBar;

    }


    private static Component createFilterHeader(String labelText, Consumer<String> filterChangeConsumer) {
        Label label = new Label(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        thread = new Thread(() -> {
            while (true) {
                try {


                    getUI().ifPresent(u -> u.access(() -> {

                        grid.getDataProvider().refreshAll();

                    }));

                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
//                    System.out.println(ex);
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        thread.interrupt();
        thread = null;
    }

}
