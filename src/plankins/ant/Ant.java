package plankins.ant;

public class Ant {

    private Point2 pos;
    private char direction;
    Game game;
    Board board;

    public Ant(int x, int y, char direction, Game g) {
        this.game = g;
        this.board = game.getBoard();
        this.pos = new Point2(x, y);
        this.direction = direction;
    }

    public void resetPos() {
        this.pos.set(game.GRID_SIZE / 2, game.GRID_SIZE / 2); //somewhere in the middle of the grid, meh.
        this.direction = 'u';

    }

    //DIRECTIONS: pretty logical, chars for Up, Down, Left, Right.

    public Point2 getPos() {
        return this.pos;
    }

    public void moveForward() {
        board.updateTile(this.pos);
        int dx = switch (direction) {
            case 'u', 'd' -> 0;
            case 'l' -> -1;
            case 'r' -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
        int dy = switch (direction) {
            case 'r', 'l' -> 0;
            case 'u' -> -1;
            case 'd' -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };

        this.pos.transform(dx, dy);

    }

    public void turnRight() {
        direction = switch (direction) {
            case 'u' -> 'r';
            case 'r' -> 'd';
            case 'd' -> 'l';
            case 'l' -> 'u';
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    public void turnLeft() {
        direction = switch (direction) {
            case 'u' -> 'l';
            case 'l' -> 'd';
            case 'd' -> 'r';
            case 'r' -> 'u';
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    public void turnBack() {
        direction = switch (direction) {
            case 'u' -> 'd';
            case 'l' -> 'r';
            case 'd' -> 'u';
            case 'r' -> 'l';
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }
}
