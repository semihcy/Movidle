package com.cuhacay.movidle.square;

public class GreenMagicSquare extends MagicSquare {

    public static GreenMagicSquare create(String title){
        GreenMagicSquare greenMagicSquare = new GreenMagicSquare();
        greenMagicSquare.setTitle(title);
        return greenMagicSquare;
    }
    @Override
    public String getColor() {
        return "#08be2d";
    }
}
