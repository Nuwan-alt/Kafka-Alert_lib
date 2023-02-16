package com.dev.application.views.Notification;

import com.dev.application.data.entity.AltertMessage;
import com.dev.application.data.entity.Notification;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import org.aspectj.weaver.ast.Not;

public class NotificationPopupView {


    Dialog dialog;
    Notification selectedNotification;

    Binder<Notification> binder;

    Component grid;
    public NotificationPopupView() {
        this.binder = new BeanValidationBinder<>(Notification.class);
        this.dialog = new Dialog();
        dialog.setModal(false);
        dialog.setHeaderTitle("GTN KAFKA NOTIFICATION");
//        binder.bindInstanceFields(this);

        FormLayout dialogLayout = createDialogLayout();
        dialog.add(dialogLayout);
        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        dialog.setWidth("50%");

        dialog.setDraggable(true);






//        add(dialog);



        // Center the button within the example
//        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
//                .set("bottom", "0").set("left", "0").set("display", "flex")
//                .set("align-items", "center").set("justify-content", "center");
    }
    public void openPopupMenu(Notification selectednotification){
        this.selectedNotification = selectednotification;
        binder.readBean(selectednotification);
        dialog.open();
    }

    public Dialog pop(){
        return dialog;
    }


    private FormLayout createDialogLayout() {


        TextField level = new TextField("Level");
        level.setReadOnly(true);
        binder.bind(level,Notification::getLevel,Notification::setLevel);
        level.getStyle().set("margin","auto").set("width","100%");

        TextField version = new TextField("Component");
        version.setReadOnly(true);
        binder.bind(version,Notification::getVersion,Notification::setVersion);
        version.getStyle().set("margin","auto").set("width","100%");

        TextField component = new TextField("Version");
        component.setReadOnly(true);
        binder.bind(component, Notification::getComponent,Notification::setComponent);

        TextField ip = new TextField("Ip");
        ip.setReadOnly(true);
        binder.bind(ip,Notification::getIp,Notification::setIp);

        TextField type = new TextField("Type");
        type.setReadOnly(true);
        binder.bind(type,Notification::getType,Notification::setType);

        TextArea messageText = new TextArea("MessageText");
        messageText.setReadOnly(true);
        binder.bind(messageText,Notification::getMessageText,Notification::setMessageText);
        messageText.setMinHeight("5rem");
        messageText.setMaxHeight("10rem");



        FormLayout formLayout = new FormLayout();
        HorizontalLayout hl = new HorizontalLayout(level,version);
        hl.getStyle().set("width","100%").set("display","flex");


        formLayout.setWidth("90%");
//        formLayout.getStyle().set("background","red");
//        formLayout.setHeight("100%");


        formLayout.add(hl,component,ip,type,messageText);
        formLayout.getStyle().set("display","flex").set("align-items", "center").set("justify-content", "center").set("margin","auto");
        return formLayout;
    }

    public void setDialogForm(Notification selectedNotification){
        this.selectedNotification =  selectedNotification;
        binder.readBean(selectedNotification);

    }
}
