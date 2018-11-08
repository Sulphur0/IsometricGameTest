package com.sulphur.hungryfrostbite.ui;

import java.awt.image.DataBufferInt;
import com.sulphur.hungryfrostbite.ui.gfx.*;

public class Renderer {

    private int pW, pH;
    private int[] p;
    private int plenght;

    public Renderer(Manager manager){
        pW = manager.getWidth();
        pH = manager.getHeight();

        p = ((DataBufferInt)manager.getWindow().getImage().getRaster().getDataBuffer()).getData();
        plenght = p.length;
    }

    public void clear(){
        for(int i  = 0; i < p.length; i++){
            p[i] = 0x00000000;
        }
    }

    public void setPixel(int x, int y, int color){
        if(x < 0 || x >= pW || y < 0 || y >= pH || color == 0xFFFF00FF){
            return;
        }
        p[x + y * pW] = color;
    }

    public void setIsometricPixel(Vector<Double> pos, int color){
        pos = twoDToIsometric(pos);
        int nx = (int)Math.round(pos.getx());
        int ny = (int)Math.round(pos.gety());
        if(nx < 0 || nx >= pW || ny < 0 || ny * pW >= plenght || color == 0xFFFF00FF){
            return;
        }
        p[nx + ny * pW] = color;
    }

    public Vector<Double> isometricToTwoD(Vector<Double> point){
        return new Vector<>(
                (2 * point.gety() + point.getx()) / 2,
                (2 * point.gety() - point.getx()) / 2
        );
    }

    public Vector<Double> twoDToIsometric(Vector<Double> point){
        return new Vector<>(
                point.getx() - point.gety(),
                (point.getx() + point.gety())/2
        );
    }

    public void drawImage(Image image, int offx, int offy){             //draws images

        //if(offx < -image.getW()) return;                                //don't bother doing that if the whole image
        //if(offy < -image.getH()) return;                                //is OOB, just jump out and save our processor
        //if(offx >= pW) return;
        //if(offy >= pH) return;

        int newX = 0;                                                   //if the image needs to be drawn, we'll save
        int newY = 0;                                                   //some processing time and set up recurring
        int newWidth = image.getW();                                    //variables
        int newHeight = image.getH();


        //if(offx < 0) newX -= offx;                                      //to save the processing time, crop of any of
        //if(offy < 0) newY -= offy;                                      //image's parts that stick out of the canvas
        //if(offx + newWidth > pW) newWidth -= (newWidth + offx - pW);
        //if(offy + newHeight > pH) newHeight -= (newHeight + offy - pH);

        for(int y = newY; y < newHeight; y++){                          //then just assign each pixel its associated
            for(int x = newX; x < newWidth; x++){                       //color
                setPixel(x + offx, y + offy, image.getP()[x + y * image.getW()]);
            }
        }
    }

    public void drawImageTile(ImageTile image, int offx, int offy, int tilex, int tiley){

        if(offx < -image.getTileW()) return;                                //don't bother doing that if the whole image
        if(offy < -image.getTileH()) return;                                //is OOB, just jump out and save our processor
        if(offx >= pW) return;
        if(offy >= pH) return;

        int newX = 0;                                                   //if the image needs to be drawn, we'll save
        int newY = 0;                                                   //some processing time and set up recurring
        int newWidth = image.getTileW();                                    //variables
        int newHeight = image.getTileH();


        if(offx < 0) newX -= offx;                                      //to save the processing time, crop of any of
        if(offy < 0) newY -= offy;                                      //image's parts that stick out of the canvas
        if(offx + newWidth > pW) newWidth -= (newWidth + offx - pW);
        if(offy + newHeight > pH) newHeight -= (newHeight + offy - pH);

        for(int y = 0; y < image.getTileH(); y++){                          //then just assign each pixel its associated
            for(int x = 0; x < image.getTileW(); x++){                       //color
                setPixel(x + offx, y + offy, image.getP()[(x + tilex * image.getTileW()) + (y + tiley * image.getTileH()) * image.getW()]);
            }
        }
    }

    public void drawIsometricImageTile(ImageTile image, double offx, double offy, int tilex, int tiley){

        int newX = 0;                                                   //if the image needs to be drawn, we'll save
        int newY = 0;                                                   //some processing time and set up recurring
        int newWidth = image.getTileW();                                    //variables
        int newHeight = image.getTileH();


        for(int y = newY; y < newHeight; y++){                          //then just assign each pixel its associated
            for(int x = newX; x < newWidth; x++){                       //color
                setIsometricPixel(new Vector<>(x + offx, y + offy), image.getP()[(x + tilex * image.getTileW()) + (y + tiley * image.getTileH()) * image.getW()]);
            }
        }
    }

    public void drawIsometricWall(ImageTile image, double offx, double offy, int tilex, int tiley, int height, int stretch){

        int newX = 0;                                                   //if the image needs to be drawn, we'll save
        int newY = 0;                                                   //some processing time and set up recurring
        int newWidth = image.getTileW();                                    //variables
        int newHeight = image.getTileH();

        int offset = 0;

        for(int x = newX; x < newWidth; x++){                          //then just assign each pixel its associated
            for(int y = newY; y < newHeight * stretch; y += stretch){                     //color
                for(int s = 0; s < stretch; s++) {
                    setPixel(x + (int) offx, y + (int) offy + offset + s, image.getP()[(x + tilex * image.getTileW()) + ((y/stretch) + tiley * image.getTileH()) * image.getW()]);
                }
            }
            offset += height;
        }
    }

    // Getters
    public int getWidth() { return pW; }
    public int getHeight() { return pH; }
    public int getPixelCount() { return p.length; }
}

