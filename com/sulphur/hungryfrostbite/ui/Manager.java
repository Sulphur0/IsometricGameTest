package com.sulphur.hungryfrostbite.ui;

public class Manager implements Runnable {

    private Thread        thread;
    private Window        window;
    private Renderer      renderer;
    private Input         input;
    private AbstractGameManager rules;

    private int    width;
    private int    height;
    private float  scale;
    private String title  = "Hungry Frostbite";

    private boolean running = false;
    private final double UPDATE_COUNT_CAP =  1.0d/60.0d;

    public Manager(AbstractGameManager rules, int width, int height, float scale){
        this.rules = rules;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public void start(){
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        thread = new Thread(this);
        thread.run();
    }

    public void stop(){

    }

    @Override
    public void run() {

        running = true;

        boolean _render = false;

        double _firstTime;
        double _lastTime        = System.nanoTime() / 1000000000.0d;
        double _passedTime;
        double _unprocessedTime = 0.0d;

        while(running){
            _firstTime = System.nanoTime() / 1000000000.0d;
            _passedTime = _firstTime - _lastTime;
            _lastTime = _firstTime;

            _unprocessedTime += _passedTime;

            while(_unprocessedTime >= UPDATE_COUNT_CAP){
                _unprocessedTime -= UPDATE_COUNT_CAP;
                _render = true;
                rules.update(this, (float) _firstTime);
                input.update();
            }

            if(_render){

                rules.render(this, renderer);

                window.updateWindow();
            }else{
                try {
                    thread.sleep(1L);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        dispose();
    }

    private void dispose(){

    }

    // Getters and setters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public float getScale() { return scale; }
    public String getTitle() {return title; }
    public Window getWindow() {return window; }
    public Input getInput() { return input; }
}

