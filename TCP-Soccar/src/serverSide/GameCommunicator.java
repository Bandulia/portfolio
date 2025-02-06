package serverSide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class GameCommunicator extends Thread{

    public BufferedReader in;
    public BufferedWriter out;
    public Server server;
    private boolean sensor1 = false;
    private boolean sensor2 = false;
    public GameCommunicator(Server server, BufferedReader in, BufferedWriter out){
        this.in = in;
        this.out = out;
        this.server = server;
    }

    public void receiveSignal(String signal){
        System.out.println(signal);

        switch (signal){
            case "M2" -> server.getGame().addScorePlayer2();
            case "M1" -> server.getGame().addScorePlayer1();
            case "S1" -> setSensor1(true);
            case "S2" -> setSensor2(true);
        }
    }

    public void setSensor1(boolean sensor1) {
        if (sensor1 && sensor2){
            server.readyUp();
        } else {
            server.turnOffCar(1);
            this.sensor1 = sensor1;
        }
    }

    public void setSensor2(boolean sensor2) {
        if (sensor2 && sensor1){
            server.readyUp();
        } else {
            server.turnOffCar(2);
            this.sensor2 = sensor2;
        }
    }


    @Override
    public void run(){
        while(!interrupted()){
            try{
                receiveSignal(in.readLine());
            } catch (IOException e) {
                server.giveNull("arduino");
                System.out.println("Arduino disconnected");
            }
        }
    }
}
