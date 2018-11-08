package com.sulphur.hungryfrostbite.ui.gfx;

public class ImageTile extends Image{

    private int tileW, tileH;   //the width and height of an individual tile image

    public ImageTile(String path, int tileW, int tileH){    //super constructor
        super(path);
        this.tileW = tileW;
        this.tileH = tileH;
    }

    //getters and setters
    public int getTileW() {
        return tileW;
    }
    public int getTileH() {
        return tileH;
    }
}

