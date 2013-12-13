import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Needs to have code convereted to run with correct classes
//This should be the 'main' for the program
public class Animation extends JPanel implements Observer {
    Simulation simulation;
    //This initializes the simulation grid and all Predator and Prey objects
    Animation(Simulation simulation) {
        this.simulation = simulation;
        setPreferredSize(new Dimension(400, 400));

        for(Blob blob : simulation.blobs) {
            blob.addObserver(this);
        }
    }

    // Necesary to update the predator and prey after each game tick
    @Override
    public void update(Observable o, Object arg) {
        Blob blob = (Blob)o;
        repaint(blob.x, blob.y, 24, 24);       
        //repaint(blob.x - 12, blob.y - 12, 24, 24);
    }
    
    // Necesary to update the predator and prey after each game tick
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Blob blob : simulation.blobs) {
            g.setColor(blob.color);
            g.fillOval(blob.x, blob.y, 20, 20);
            //g.fillOval(blob.x - 10, blob.y - 10, 20, 20);
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        JFrame frame = new JFrame();
        frame.getContentPane().add(new Animation(simulation));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        simulation.simulate();
    }
}
