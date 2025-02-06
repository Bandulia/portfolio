package ClientSide;

import java.util.Timer;
import java.util.TimerTask;

public class ClientTimer {

    private Timer timer;
    private GameFrame gameFrame;
    private boolean isPaused = false;

    public ClientTimer(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    public void start(int minute, int second) {

        this.timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int min = minute;
            int sec = second;
            @Override
            public void run() {
                if (!isPaused){
                    if(min == 0 && sec == 0){
                        gameFrame.endGame();
                    }
                    if(sec == 0){
                        min--;
                        sec=60;
                    }
                    sec--;
                    gameFrame.updateTimeLabel("Time 0" + min + ":" + sec);
                }
            }
        }, 0, 1000);
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }
}

