package com.sulphur.hungryfrostbite.ui;

public abstract class AbstractGameManager {

    public abstract void update(Manager manager, float deltaTime);
    public abstract void render(Manager manager, Renderer renderer);

}
