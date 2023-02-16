package com.dev.application.views;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.catalina.webresources.FileResource;

//@Route(value = "")
//@PageTitle("errors")
public class MainLayout extends AppLayout {

    public MainLayout() {
        creatHeader();

    }

    private void creatHeader() {

        StreamResource imageResource = new StreamResource("download.png",
                () -> getClass().getResourceAsStream("/images/download.png"));

        Image image = new Image(imageResource, "GTN logo");
        image.setHeight(4.1F, Unit.PERCENTAGE);
        image.setWidth(4.1F,Unit.PERCENTAGE);

        H3 logo = new H3("Monitoring Application");
        logo.addClassNames("text-m","m-m");
        logo.getStyle().set("margin","auto");

        HorizontalLayout header = new HorizontalLayout(image, logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-m","px-m");

        addToNavbar(header);
    }
}
