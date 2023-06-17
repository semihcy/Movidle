package com.cuhacay.movidle.square;


public class DownArrowMagicSquare extends ArrowMagicSquare {
    public static DownArrowMagicSquare create(String title){
        DownArrowMagicSquare downArrowMagicSquare = new DownArrowMagicSquare();
        downArrowMagicSquare.setTitle(title);
        return downArrowMagicSquare;
    }

    @Override
    public int getRotationValue() {
        return 180;
    }
}
