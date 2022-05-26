package plankins.ant;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    Game game;
    Board board;


    public GamePanel(Game g) {
        this.game = g;
        this.board = game.getBoard();
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(game.WINDOW_SIZE, game.WINDOW_SIZE));

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw((Graphics2D) g);
    }


    //draws an ant which is a little smaller than the tile it is on, so the tile color can be seen.
    public void drawAnt(Graphics2D g) {
        Point2 p = game.ant.getPos();
        int x = (p.getX() * game.SIZE) + game.SIZE / 4;
        int y = (p.getY() * game.SIZE) + game.SIZE / 4;
        int size = game.SIZE / 2;

        drawRect(g, Color.black, x, y, size);
        Color prevCol = g.getColor();
        g.setColor(Color.black);
        g.fillRect(x, y, size, size);
        g.setColor(prevCol);
    }


    public void drawRect(Graphics2D g, Color c, int x, int y) {
        drawRect(g, c, x, y, game.SIZE);
    }


    //simple rectangle function i made
    public void drawRect(Graphics2D g, Color c, int x, int y, int size) {
        Color prevCol = g.getColor();
        g.setColor(c);
        g.fillRect(x * game.SIZE, y * game.SIZE, size, size);
        g.setColor(prevCol);
    }


    //goes over the wholle boardArray and, if not out of bounds, draws the tile, respective of its value.
    public void draw(Graphics2D g) {
        for (int x = 0; x < game.GRID_SIZE; x++) {
            for (int y = 0; y < game.GRID_SIZE; y++) {
                int v = board.getOnBoard(x, y);

                Color col = switch (v) {
                    case 1 -> Color.BLUE;
                    case 2 -> Color.red;
                    case 3 -> Color.GREEN;
                    case 4 -> Color.yellow;
                    default -> Color.GRAY;
                };

                //dont fill empty cells
                if (v != 0) {
                    drawRect(g, col, x, y);
                }


            }


        }

        //at last, draw the ant on top of the colours
        drawAnt(g);


        //draw a grid as long as the cells are not too small, so it doesnt get messy.
        if (game.SIZE > 5) {
            g.setColor(Color.darkGray);
            for (int i = 0; i <= game.GRID_SIZE; i++) {
                g.drawLine(game.SIZE * i, 0, game.SIZE * i, game.WINDOW_SIZE);
            }
            for (int i = 0; i <= game.GRID_SIZE; i++) {
                g.drawLine(0, game.SIZE * i, game.WINDOW_SIZE, game.SIZE * i);
            }
        }
    }


}
