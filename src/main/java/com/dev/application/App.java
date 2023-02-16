package com.dev.application;


import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.kafka.common.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication

@NpmPackage(value = "line-awesome", version = "1.3.0")
public class App implements AppShellConfigurator {

    static KafkaConsumer<String, String> consumer = null;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);



    }

}
