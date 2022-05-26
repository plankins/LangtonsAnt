package plankins.ant;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    Game game;
    private GamePanel panel;
    public InputHandler inputHandler;


    public GameFrame(Game g){
        this.game = g;
        panel = new GamePanel(game);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);


        this.getContentPane().add(panel);

        inputHandler = new InputHandler(game);
        this.getContentPane().addMouseListener(inputHandler);
        this.getContentPane().addMouseMotionListener(inputHandler);
        this.getContentPane().addMouseWheelListener(inputHandler);
        this.panel.addKeyListener(inputHandler);
        this.pack();

    }
}
