package serverSide;
import serverSide.Game;

import java.util.Timer;
import java.util.TimerTask;

public class GamingTimer {
    private Timer timer;
    private long remainingTime;
    private Game game;

    public GamingTimer(Game game) {
        this.timer = new Timer();
        this.remainingTime = 300000;
        this.game = game;
    }

    public void start() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingTime -= 1000; // subtract 1 second from remaining time
                //System.out.println("Remaining time: " + remainingTime / 1000 + " seconds");

                if (remainingTime <= 0) {
                    //game.endGame();
                    timer.cancel(); // cancel the timer when the remaining time is up
                }
            }
        }, 0, 1000); // schedule the task to run every second
    }

    public void pause() {
        timer.cancel(); // cancel the currently scheduled task
    }

    public void resume() {
        start();
    }
}
