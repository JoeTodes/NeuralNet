package app;

import processing.core.PApplet;

public class App extends PApplet{
    NeuralNet net;
    Matrix mat;
    public static void main(String[] args)  {
        PApplet.main("app.App");    //must match this file name and package
    }

    public void settings(){         //only used for setting the size of the canvas
        size (640, 480);
    }

    public void setup(){            //runs once on launch
        net=new NeuralNet(3, 3, 1);
        
    }

    public void draw(){             //main loop

    }
}