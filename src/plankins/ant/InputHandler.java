package plankins.ant;

import java.awt.event.*;

public class InputHandler implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    public int mouseX = 0;
    public int mouseY = 0;

    public boolean[] pressedButtons;
    public boolean[] pressedKeys;

    Game game;
    Board board;

    public InputHandler(Game g) {
        game = g;
        board = game.getBoard();

        int buttons = java.awt.MouseInfo.getNumberOfButtons() + 1;
        pressedButtons = new boolean[buttons];
        pressedKeys = new boolean[3]; //shift = 0, strg = 1, alt = 2

    }

    @Override
    public void mousePressed(MouseEvent e) {

        pressedButtons[e.getButton()] = true;
        switch (e.getButton()) {
            case MouseEvent.BUTTON1 -> game.onAction(Action.L_MOUSE, e);
            case MouseEvent.BUTTON2 -> game.onAction(Action.MID_MOUSE, e);
            case MouseEvent.BUTTON3 -> game.onAction(Action.R_MOUSE, e);
        }

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (pressedButtons[1]) {
            game.onAction(Action.L_MOUSE, e);
        }
        if (pressedButtons[2]) {
            game.onAction(Action.MID_MOUSE, e);
        }
        if (pressedButtons[3]) {
            game.onAction(Action.R_MOUSE, e);
        }

    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            game.onAction(Action.WHEEL_UP, e);
        } else if (e.getWheelRotation() > 0) {
            game.onAction(Action.WHEEL_DOWN, e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedButtons[e.getButton()] = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_SPACE -> game.onAction(Action.SPACE, e);
            case KeyEvent.VK_S -> game.onAction(Action.S, e);
            case KeyEvent.VK_C -> game.onAction(Action.C, e);
            case KeyEvent.VK_UP -> game.onAction(Action.UP, e);
            case KeyEvent.VK_DOWN -> game.onAction(Action.DOWN, e);
            case KeyEvent.VK_RIGHT -> game.onAction(Action.RIGHT, e);
            case KeyEvent.VK_LEFT -> game.onAction(Action.LEFT, e);
            case KeyEvent.VK_1 -> game.onAction(Action.N1, e);
            case KeyEvent.VK_2 -> game.onAction(Action.N2, e);
            case KeyEvent.VK_3 -> game.onAction(Action.N3, e);
            case KeyEvent.VK_4 -> game.onAction(Action.N4, e);
            case KeyEvent.VK_P -> game.onAction(Action.P, e);
            case KeyEvent.VK_R -> game.onAction(Action.R, e);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

