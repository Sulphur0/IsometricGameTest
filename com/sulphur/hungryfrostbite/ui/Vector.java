package com.sulphur.hungryfrostbite.ui;

public class Vector <T> {

    private T x, y;

    public Vector(T x, T y){
        this.x = x;
        this.y = y;
    }

    public T getx() { return x; }
    public T gety() { return y; }
    public void setx(T x) { this.x = x; }
    public void sety(T y) { this.y = y; }
}
