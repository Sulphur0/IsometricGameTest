package com.sulphur.hungryfrostbite.ui;

import java.awt.event.*;

public class Input implements MouseListener, MouseMotionListener, KeyListener {

    private final int KEY_COUNT = 256;
    private boolean[] keyState = new boolean[KEY_COUNT];
    private boolean[] lastKeyState = new boolean[KEY_COUNT];

    private final int MOUSE_BUTTONS_COUNT = 5;
    private boolean[] mouseButtons = new boolean[MOUSE_BUTTONS_COUNT];
    private boolean[] mouseButtonsLast = new boolean[MOUSE_BUTTONS_COUNT];

    private int mouseX;
    private int mouseY;

    private Manager manager;

    public Input(Manager manager){
        this.manager = manager;
        mouseX = mouseY = 0;

        manager.getWindow().getCanvas().addMouseListener(this);
        manager.getWindow().getCanvas().addMouseMotionListener(this);
        manager.getWindow().getCanvas().addKeyListener(this);
    }

    public void update(){
        for(int i = 0 ; i < MOUSE_BUTTONS_COUNT; i++) {
            mouseButtonsLast[i] = mouseButtons[i];
        }
        for(int i = 0 ; i < KEY_COUNT; i++){
            lastKeyState[i] = keyState[i];
        }
    }

    public boolean isMouseButton(int mouseButtonCode){
        return mouseButtons[mouseButtonCode];
    }

    public boolean isButtonUp(int mouseButtonCode){
        return !mouseButtons[mouseButtonCode] && mouseButtonsLast[mouseButtonCode];
    }

    public boolean isButtonDown(int mouseButtonCode){
        return mouseButtons[mouseButtonCode] && !mouseButtonsLast[mouseButtonCode];
    }

    public boolean isKey(int keyCode){
        return keyState[keyCode];
    }
    public boolean isKeyUp(int keyCode){
        return !keyState[keyCode] && lastKeyState[keyCode];
    }
    public boolean isKeyDown(int keyCode){
        return keyState[keyCode] && !lastKeyState[keyCode];
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) { }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mouseButtons[mouseEvent.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mouseButtons[mouseEvent.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        mouseX = (int) (mouseEvent.getX() / manager.getScale());
        mouseY = (int) (mouseEvent.getY() / manager.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = (int) (mouseEvent.getX() / manager.getScale());
        mouseY = (int) (mouseEvent.getY() / manager.getScale());
    }

    public int getMouseX(){ return mouseX; }

    public int getMouseY() { return mouseY; }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyState[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyState[keyEvent.getKeyCode()] = false;
    }
}
