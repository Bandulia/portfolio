package serverSide;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private boolean active;
    private int scorePlayer1;
    private int scorePlayer2;
    private Server server;
    private boolean isPaused;
    private int gameType;
    private GamingTimer timer;

    public Game(Server server){
        this.server = server;
        this.isPaused = false;
        this.scorePlayer1 = 0;
        this.scorePlayer2 = 0;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public void startGame(){
        active = true;
        this.timer = new GamingTimer(this);
    }

    public boolean getPaused(){
        return this.isPaused;
    }

    public void setPaused(boolean pause){
        this.isPaused = pause;
    }
    public void endGame(){
        server.endGame();
    }

    public void pauseTimer(){
        timer.pause();
    }

    public void resumeTimer(){
        timer.resume();
    }

    public boolean isActive() {
        return active;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void addScorePlayer1() {
        this.scorePlayer1 ++;
        server.goal(scorePlayer1 + ":" + scorePlayer2);
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void addScorePlayer2() {
        this.scorePlayer2++;
        server.goal(scorePlayer1 + ":" + scorePlayer2);
    }

    public void powerUp(String power, int time, int id){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            int timeLeft = time;

            public void run() {
                if (timeLeft > 0) {
                    System.out.println(timeLeft);
                    timeLeft--;
                } else {
                    server.endPowerUp(power, id);
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
}
