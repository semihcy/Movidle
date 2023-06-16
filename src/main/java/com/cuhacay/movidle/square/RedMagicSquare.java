package com.cuhacay.movidle.square;

public class RedMagicSquare extends MagicSquare {

    public static RedMagicSquare create(String title){
        RedMagicSquare redMagicSquare = new RedMagicSquare();
        redMagicSquare.setTitle(title);
        return redMagicSquare;
    }

    @Override
    public String getColor() {
        return "#d81512";
    }
}
