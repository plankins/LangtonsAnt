package plankins.ant;


public class Board {

    Game game;
    int[][] boardArray;

    /*
    this is one of the classes which i used as a base, and modified.
    yeah dont have anything to add, pretty useful class with functions that are needed often.
     */


    public Board(Game g) {
        game = g;
        boardArray = new int[game.GRID_SIZE][game.GRID_SIZE];
        clearArray(boardArray);
    }

    public int getOnBoard(Point2 p) {
        return getOnBoard(p.getX(), p.getY());
    }

    public int getOnBoard(int x, int y) {
        if (isInBounds(x, y))
            return boardArray[x][y];

        return 0;
    }

    public void updateTile(Point2 p) {
        int var = getOnBoard(p);
        int newVar = var+1;
        if(newVar > 3){
            newVar = 0;
        }
        setOnBoard(p, newVar);
    }

    private void setOnBoard(Point2 p, int newVar) {
        setOnBoard(p.getX(), p.getY(), newVar);
    }

    public void setOnBoard(int x, int y, int v) {
        if (isInBounds(x, y))
            boardArray[x][y] = v;
    }

    public int countNonEmpty(int[][] arr) {
        int i = 0;
        for (int x = 0; x < game.GRID_SIZE; x++) {
            for (int y = 0; y < game.GRID_SIZE; y++) {
                if (arr[x][y] != 0) {
                    i++;
                }

            }

        }
        return i;
    }

    public void clearArray() {
        clearArray(boardArray);
    }

    public void clearArray(int[][] bArray) {
        if (bArray != null) {
            for (int x = 0; x < game.GRID_SIZE; x++) {
                for (int y = 0; y < game.GRID_SIZE; y++) {
                    bArray[x][y] = 0;
                }
            }
        }
    }

    public boolean isInBounds(Point2 p) {
        return isInBounds(p.getX(), p.getY());
    }

    public boolean isInBounds(int x, int y) {
        if (x >= 0 && x < game.GRID_SIZE && y >= 0 && y < game.GRID_SIZE) {
            return true;
        }
        //System.out.println("Out of Bounds!: " + x + " - " + y);
        return false;
    }

    public void printArray(int[][] array) {
        System.out.println("------------------------");
        for (int y = 0; y < game.GRID_SIZE; y++) {
            for (int x = 0; x < game.GRID_SIZE; x++) {
                System.out.print(array[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------\n\n\n\n");

    }

    public void printArray() {
        System.out.println("------------------------");
        printArray(boardArray);
    }

    public boolean contains(int v) {
        for (int x = 0; x < game.GRID_SIZE; x++) {
            for (int y = 0; y < game.GRID_SIZE; y++) {
                return boardArray[x][y] == v;
            }
        }
        return false;
    }

}


