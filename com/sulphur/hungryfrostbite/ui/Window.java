package com.sulphur.hungryfrostbite.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame         frame;
    private BufferedImage  image;
    private Canvas         canvas;
    private BufferStrategy bufferStrategy;
    private Graphics       graphics;

    public Window(Manager manager){

        image = new BufferedImage(
                manager.getWidth(),
                manager.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        canvas = new Canvas();
        Dimension _d = new Dimension(
                (int)(manager.getWidth() * manager.getScale()),
                (int)(manager.getHeight() * manager.getScale())
        );

        canvas.setPreferredSize(_d);
        canvas.setMinimumSize(_d);
        canvas.setMaximumSize(_d);

        frame = new JFrame(manager.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void updateWindow(){
        graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }

    // Getters
    public BufferedImage getImage() { return image; }
    public Canvas getCanvas() { return canvas; }
    public JFrame getFrame() { return frame; }
}

