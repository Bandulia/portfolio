package serverSide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * CarCommunicator sends the arduino card connected to the car-controllers,
 * signals used to drive/steer the cars.
 */

public class CarCommunicator extends Thread{
    private final BufferedReader in;
    private final BufferedWriter out;
    private Server server;
    private boolean sensor1 = false;
    private boolean sensor2 = false;
    public CarCommunicator(BufferedWriter out, BufferedReader in, Server server) {
        this.out = out;
        this.in = in;
        this.server = server;
    }

    public void sendSignal(int player, String action) {
        try {

            out.write(player + "/" + action);
            out.newLine();
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSensor1() {
        return sensor1;
    }

    public void setSensor1(boolean sensor1) {
        this.sensor1 = sensor1;
    }

    public boolean isSensor2() {
        return sensor2;
    }

    public void setSensor2(boolean sensor2) {
        this.sensor2 = sensor2;
    }

    public void receiveSignal(String signal){
        System.out.println(signal);

        switch (signal){
            case "M1" -> {
                server.getGame().addScorePlayer2();
            }
            case "M2" -> {
                server.getGame().addScorePlayer1();
            }
           /* case "S1" -> {
                this.sensor1 = expression[1].equals("1");
                checkReadyOpponents();
            }
            case "S2" -> {
                this.sensor2 = expression[1].equals("1");
                checkReadyOpponents();
            }*/
            case "S" -> server.readyUp();
        }
    }


    public void run(){
        while(!interrupted()){
            try{
                receiveSignal(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}