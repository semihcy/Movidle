package com.cuhacay.movidle.square;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

public abstract class ArrowMagicSquare extends RedMagicSquare {

    public ArrowMagicSquare() {
        ImageView imageView = new ImageView(new Image(new File("images/arrow.jpeg").toURI().toString()));
        imageView.setRotate(getRotationValue());
        imageView.setFitWidth(65);
        imageView.setFitHeight(65);
        imageView.setOpacity(0.3);
        getChildren().add(imageView);
    }

    protected abstract int getRotationValue();
}
