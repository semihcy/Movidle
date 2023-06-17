package com.cuhacay.movidle.square;


public class UpArrowMagicSquare extends ArrowMagicSquare {
    public static UpArrowMagicSquare create(String title){
        UpArrowMagicSquare upArrowMagicSquare = new UpArrowMagicSquare();
        upArrowMagicSquare.setTitle(title);
        return upArrowMagicSquare;
    }

    @Override
    public int getRotationValue() {
        return 0;
    }
}
