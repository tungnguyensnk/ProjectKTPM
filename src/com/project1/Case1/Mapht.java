package com.project1.Case1;

import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Mapht implements Initializable {
    public WebView webView;
    public AnchorPane root;
    int x,y;
    private double xOffset = 0;
    private double yOffset = 0;

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            root.getScene().getWindow().setX(mouseEvent.getScreenX() - xOffset);
            root.getScene().getWindow().setY(mouseEvent.getScreenY() - yOffset);
        });
        File file = new File(System.getProperty("user.dir")+"//test.html");
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(file.toURI().toString());
        PauseTransition delay = new PauseTransition(Duration.millis(1000));
        delay.setOnFinished(actionEvent -> webEngine.executeScript("initMap("+x+","+y+");"));
        delay.play();
        root.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER)
                root.getScene().getWindow().hide();
        });
    }
}
