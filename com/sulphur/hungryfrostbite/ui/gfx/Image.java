package com.sulphur.hungryfrostbite.ui.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Image {
    protected int w, h;
    private int[] p;

    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }
    public int[] getP() {
        return p;
    }

    public Image(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Image.class.getResourceAsStream(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        w = image.getWidth();
        h = image.getHeight();
        p = image.getRGB(0, 0, w, h, null, 0, w);
        image.flush();
    }
}

