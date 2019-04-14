package app;

import processing.core.PApplet;

public class App extends PApplet{
    NeuralNet net;
    public static void main(String[] args)  {
        PApplet.main("app.App");    //must match this file name and package
    }

    public void settings(){         //only used for setting the size of the canvas
        size (1600, 900);
    }

    public void setup(){            //runs once on launch
        net=new NeuralNet(3, 3, 1);
    }

    public void draw(){             //main loop

    }
}