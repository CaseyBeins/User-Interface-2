package com.example.userinterfaceapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class MainApp extends Application {
    private TextArea textArea;
    private BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        textArea = new TextArea();
        root.setCenter(textArea);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Options");

        MenuItem dateTimeItem = new MenuItem("Show Date and Time");
        dateTimeItem.setOnAction(e -> showDateTime());

        MenuItem saveToFileItem = new MenuItem("Save to File");
        saveToFileItem.setOnAction(e -> saveToFile());

        MenuItem changeColorItem = new MenuItem("Change Background Color");
        changeColorItem.setOnAction(e -> changeBackgroundColor());

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());

        menu.getItems().addAll(dateTimeItem, saveToFileItem, changeColorItem, exitItem);
        menuBar.getMenus().add(menu);

        root.setTop(menuBar);

        primaryStage.setScene(scene);
        primaryStage.setTitle("User Interface App");
        primaryStage.show();
    }

    private void showDateTime() {
        textArea.appendText("Current Date and Time: " + LocalDateTime.now() + "\n");
    }

    private void saveToFile() {
        String content = textArea.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(content);
            writer.newLine();
            textArea.appendText("Saved to file log.txt\n");
        } catch (IOException e) {
            textArea.appendText("Error saving to file log.txt\n");
            e.printStackTrace();
        }
    }

    private void changeBackgroundColor() {
        Random random = new Random();
        int hue = random.nextInt(61) + 120; // Random hue between 120 and 180 for green shades
        String colorStyle = "-fx-control-inner-background: hsb(" + hue + ", 100%, 50%);";
        textArea.setStyle(colorStyle);
    }
}
