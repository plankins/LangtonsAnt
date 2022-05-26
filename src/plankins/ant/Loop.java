package plankins.ant;


public class Loop {
    int stepsPerLoop = 1;
    boolean running = true;

    long startTime;
    int currentFrames;
    int fps;
    double stepDelay = 20;
    Game game;

    public Loop(Game g) {
        this.game = g;
    }



    public void run() {
        long lastloop = System.nanoTime();
        startTime = (lastloop / 1000000);
        long laststep = System.nanoTime();

        //my "gameloop". what do you think?
        while (running) {
            long now = System.nanoTime();

            if (game.running) {
                if ((now - laststep) / 1000000f >= stepDelay) {
                    for (int i = 0; i < stepsPerLoop; i++) {
                        game.step();

                    }
                    laststep = now;
                }
            }

            /*
            as you can see, i split the actual simulation and the rendering.
            this way, i can achieve higher simulation speeds while not having to render everytime.
             */

            if ((now - lastloop) / 1000000f >= 5) {
                game.frame.repaint();
                game.frame.setTitle("Sand  (fps: " + fps + ")  STEP: " + game.stepCnt + "  STEPDELAY: " + stepDelay + "  STEPSPERLOOP: " + stepsPerLoop);
                countFrame();
                lastloop = now;
            }
        }
    }

    public void countFrame() {
        long now = System.currentTimeMillis();
        if (now - startTime >= 1000) {
            startTime = now;
            fps = currentFrames;
            currentFrames = 0;

        }
        currentFrames += 1;
    }


}