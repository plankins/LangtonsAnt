package plankins.ant;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Game {

    private Board board;
    private static Game game;
    public GameFrame frame;

    public Loop loop;
    public Ant ant;

    public int SIZE;
    public int WINDOW_SIZE;
    public int GRID_SIZE;
    public int mouseX = 0;
    public int mouseY = 0;
    public int slot = 1;
    public int stepCnt = 0;

    boolean running = false;

    public Game(int windowSize, int gridSize) {
        WINDOW_SIZE = windowSize;
        GRID_SIZE = gridSize;
        SIZE = WINDOW_SIZE / GRID_SIZE;

        game = this;

        board = new Board(this);
        ant = new Ant(gridSize / 2, gridSize / 2, 'u', this);
        loop = new Loop(this);
        frame = new GameFrame(this);

        loop.run();
    }


    public void drawOnBoard(Action a, int x, int y) {

        switch (a) {
            case L_MOUSE:
                switch (slot) {
                    //draw on the grid with a total of 6 tile states (including 0), but right now only 4 used.
                    case 1 -> board.setOnBoard(x, y, 1);
                    case 2 -> board.setOnBoard(x, y, 2);
                    case 3 -> board.setOnBoard(x, y, 3);
                    case 4 -> board.setOnBoard(x, y, 4);
                    case 5 -> board.setOnBoard(x, y, 5);
                }
                break;
            case R_MOUSE:
                //"erase", if you will.
                board.setOnBoard(x, y, 0);
                break;
            case MID_MOUSE:
                //set the ant at mouse pos.
                ant.getPos().set(x, y);
        }
    }

    public void step() {
        Point2 p = ant.getPos();
        int val = board.getOnBoard(p);

        switch (val) {
            //by changing this, one can change the pattern the ant produces.
            case 0 -> ant.turnRight();
            case 1 -> ant.turnRight();
            case 2 -> ant.turnLeft();
            case 3 -> ant.turnRight();
        }

        ant.moveForward();
        stepCnt++;
    }


    //every user input is handled by this function, with Action enums.

    public void onAction(Action a, AWTEvent e) {
        System.out.println(a);
        if (e instanceof MouseEvent) {
            mouseX = Math.floorDiv(((MouseEvent) e).getX(), game.SIZE);
            mouseY = Math.floorDiv(((MouseEvent) e).getY(), game.SIZE);
            // idek why i used math.floordiv

            drawOnBoard(a, mouseX, mouseY);

            if (e instanceof MouseWheelEvent) {
                double increment = 10;

                System.out.println(loop.stepDelay);
                switch (a) {
                    case WHEEL_DOWN -> {
                        game.GRID_SIZE -= increment;
                    }
                    case WHEEL_UP -> {
                        game.GRID_SIZE += increment;
                    }
                }

            }


        } else if (e instanceof KeyEvent) {
            double increment = loop.stepDelay / 10;

            switch (a) {
                case SPACE -> game.toggleSimulation();
                case S -> game.step();
                case C -> {
                    board.clearArray();
                    stepCnt = 0;
                }
                case UP -> {
                    if (loop.stepDelay + increment <= 2000) {
                        loop.stepDelay += increment;
                    }
                }
                case DOWN -> {
                    if (loop.stepDelay - increment > 0) {
                        loop.stepDelay -= increment;

                    }
                }
                case RIGHT -> {
                    if (loop.stepsPerLoop <= 400000)
                        loop.stepsPerLoop += 1 + loop.stepsPerLoop / 10;
                }
                case LEFT -> {
                    if (loop.stepsPerLoop >= 0)
                        loop.stepsPerLoop -= 1 + loop.stepsPerLoop / 10;
                }
                case N1 -> this.slot = 1;
                case N2 -> this.slot = 2;
                case N3 -> this.slot = 3;
                case N4 -> this.slot = 4;
                case N5 -> this.slot = 5;
                case P -> board.printArray();
                case R -> ant.resetPos();
            }

        }


    }

    public Board getBoard() {
        return this.board;
    }

    public void toggleSimulation() {
        running = !running;
        if (running) {
            startSimulation();
        } else {
            stopSimulation();
        }
    }

    public void startSimulation() {
        running = true;
        System.out.println("start");
    }

    public void stopSimulation() {
        running = false;
        System.out.println("stop");
    }


}
