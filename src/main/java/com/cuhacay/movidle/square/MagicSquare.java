package com.cuhacay.movidle.square;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public abstract class MagicSquare extends StackPane {
    public String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
        init();
    }

    public MagicSquare() {
        setStyle("-fx-border-color: #ffffff; -fx-border-width: 1px; -fx-background-color: " + getColor() + ";");
        setPrefHeight(75);
        setPrefWidth(75);
        startAnim();
        init();
    }

    protected abstract String getColor();

    void init() {
        getChildren().add(createText(title));
    }

    void startAnim() {
        startFadeAnim();
        startFlipAnim();
    }

    Label createText(String title) {
        Label text = new Label(title);
        text.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-text-alignment: center;");
        text.setWrapText(true);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        dropShadow.setRadius(5);
        text.setEffect(dropShadow);
        return text;
    }

    void startFadeAnim() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), this);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
    }

    void startFlipAnim() {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), this);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(-90);
        rotateTransition.setToAngle(0);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }
}
