package com.cuhacay.movidle.square;

import com.cuhacay.movidle.square.RedMagicSquare;
import javafx.scene.image.ImageView;

public abstract class ArrowMagicSquare extends RedMagicSquare {

    public ArrowMagicSquare() {
        ImageView imageView = new ImageView("https://i.hizliresim.com/4vfv229.png");
        imageView.setRotate(getRotationValue());
        imageView.setFitWidth(65);
        imageView.setFitHeight(65);
        imageView.setOpacity(0.3);
        getChildren().add(imageView);
    }

    protected abstract int getRotationValue();
}
