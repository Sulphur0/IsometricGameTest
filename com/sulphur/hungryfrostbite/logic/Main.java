package com.sulphur.hungryfrostbite.logic;

import com.sulphur.hungryfrostbite.ui.Manager;
import com.sulphur.hungryfrostbite.ui.AbstractGameManager;
import com.sulphur.hungryfrostbite.ui.Renderer;
import com.sulphur.hungryfrostbite.ui.gfx.ImageTile;
import com.sulphur.hungryfrostbite.ui.gfx.Image;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Main extends AbstractGameManager{

    private final static int WINDOW_WIDTH = 96;
    private final static int WINDOW_HEIGHT = 64;
    private final static float WINDOW_SCALE = 9f;

    private double offx = 0, offy = 0;
    private double speed = -0.5;

    private ImageTile ground = new ImageTile("floorTilePurple.png",8,8);
    private Image player = new Image("playerSprite.png");
    private static int groundx = 10, groundy = 10;
    private static int[] groundTile = new int[groundx * groundy];

    private int playerPosx = WINDOW_WIDTH / 2 - 4;
    private int playerPosy = WINDOW_HEIGHT / 2 - 4;

    @Override
    public void update(Manager manager, float deltaTime) {
        int ax = 0, ay = 0;
        if(manager.getInput().isKey(KeyEvent.VK_A)){ ay++; ax--; }
        if(manager.getInput().isKey(KeyEvent.VK_D)){ ay--; ax++; }
        if(manager.getInput().isKey(KeyEvent.VK_W)){ ay--; ax--; }
        if(manager.getInput().isKey(KeyEvent.VK_S)){ ay++; ax++; }
        offx += ax * speed;
        offy += ay * speed;
    }

    @Override
    public void render(Manager manager, Renderer renderer) {
        renderer.clear();
        for(int y = 0; y < groundy; y++) {
            for(int x = 0; x < groundx; x++){
                renderer.drawIsometricImageTile(ground, offx + x * 8, offy + y * 8, groundTile[x + y * groundx], 0);
            }
        }
        renderer.drawImage(player,playerPosx,playerPosy);
    }

    public static void main(String[] args){
        for(int i = 0; i < groundTile.length; i++){
            groundTile[i] = (int)Math.round(Math.random()*4);
        }
        Manager _manager = new Manager(new Main(),WINDOW_WIDTH,WINDOW_HEIGHT,WINDOW_SCALE);
        _manager.start();
    }
}
