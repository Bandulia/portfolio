package serverSide;

import ClientSide.MenuFrame;
import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server handles the connection with the two players and the arduino chip through TCP/IP connections.
 * @author Julia Tadic
 */
public class Server {

    private final ServerSocket serverSocket;
    private ControlPanel gui;
    private PlayerCommunicator player1 = null;
    private PlayerCommunicator player2 = null;
    private CarCommunicator car = null;
    private Game game;


    public void goal(String score){
        player1.send("Score/" + score);
        player2.send("Score/" + score);
    }

    public void readyUp(){
        player1.send("Start");
        player2.send("Start");
    }
    public Server(int port) {

        this.game = new Game(this);

        setUpControlPanel();

        try {

            this.serverSocket = new ServerSocket(port);
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.getHostAddress());
            new ConnectionListener().start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void opponentsReady(){
        if (player1.isReady() && player2.isReady()){
            player1.send("Ready/");
            player2.send("Ready/");
        }
    }

    private void setUpControlPanel(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui = new ControlPanel(getServer());
                gui.setVisible(true);
            }
        });
    }

    public void disconnect() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    public void endPowerUp(String power, int id){
        System.out.println(power + " " + id + " är avslutat");
        if (id == 1){
            switch(power){
                case "Off" -> player1.setCarIsOff(false);
                case "Switch" -> player1.setSteeringSwitched(false);
                //case "Close" -> player1/*TODO*/
            }
        }
         else if (id == 2){
            switch(power){
                case "Off" -> player2.setCarIsOff(false);
                case "Switch" -> player2.setSteeringSwitched(false);
                //case "Close" -> player2/*TODO*/
            }
        }
    }

    public void endGame() {
        if (game.getScorePlayer1() < game.getScorePlayer2()){
            player1.send("endGame/0");
            player2.send("endGame/1");
        } else if (game.getScorePlayer1() > game.getScorePlayer2()){
            player1.send("endGame/1");
            player2.send("endGame/0");
        } else {
            player1.send("endGame/2");
            player2.send("endGame/2");
        }
    }

    public Game getGame() {
        return this.game;
    }

    public Server getServer(){
        return this;
    }

    public void closeGoal(int id){
        /*TODO close the goal */
    }

    public void turnOffOpponentsCar(int id){
        if (id == 1){
            player2.setCarIsOff(true);
            game.powerUp("Off", 3, 2);
            System.out.println("Player 2 car is off");
        } else if (id == 2){
            player1.setCarIsOff(true);
            game.powerUp("Off", 3, 1);
            System.out.println("Player 1 car is off");
        }
    }

    public void switchOpponentsSteering(int id){
        if (id == 1){
            player2.setSteeringSwitched(true);
            game.powerUp("Switch", 3, 2);
            System.out.println("Player 2 steering is switched");
        } else if (id == 2){
            player1.setSteeringSwitched(true);
            game.powerUp("Switch", 3, 1);
            System.out.println("Player 1 steering is switched");
        }
    }

    public void pauseOrResumeGame(){

        if(game.getPaused()){
            player1.send("R");
            player2.send("R");
            game.setPaused(false);
        } else {
            player1.send("P");
            player2.send("P");
            game.setPaused(true);
        }
    }

    public void setGameType(int type){
        game.setGameType(type);
        player2.send("G/" + type);
    }

    public void getPowerUp(int id){
        if (car.isSensor1() || car.isSensor2()){
            if (id == 1){
                player1.send("Power");
                car.setSensor1(false);
                car.setSensor2(false);
            } else {
                player2.send("Power");
                car.setSensor1(false);
                car.setSensor2(false);
            }
        }
    }

    private class ConnectionListener extends Thread {

        private void assignConnection(Socket socket) {
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String assignment = in.readLine();

                if (assignment.equals("player")) {
                    if (player1 == null) {
                        player1 = new PlayerCommunicator(in, out, 1);
                        player1.start();
                        System.out.println("PlayerCommunicator 1 connected");
                    } else if (player2 == null) {
                        player2 = new PlayerCommunicator(in, out, 2);
                        player2.start();
                        System.out.println("PlayerCommunicator 2 connected");
                    }
                } else if (assignment.equals("game")) {
                    if (car == null) {
                        car = new CarCommunicator(out, in, getServer());
                        car.start();
                        System.out.println("Arduino is connected");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            Socket socket = null;
            try {
                while (!Thread.interrupted()) {

                    socket = serverSocket.accept();
                    assignConnection(socket);

                }
            } catch (IOException e) {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                    disconnect();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private class PlayerCommunicator extends Thread {

        private final BufferedReader in;
        private final BufferedWriter out;
        private final int id;
        private boolean isReady;
        private boolean steeringSwitched;
        private boolean carIsOff;

        public PlayerCommunicator(BufferedReader in, BufferedWriter out, int id) {

            this.steeringSwitched = false;
            this.carIsOff = false;
            this.in = in;
            this.out = out;
            this.id = id;
            send(String.valueOf(id));
        }

        public void send(String info){
            try {
                out.write(info);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean isReady(){
            return this.isReady;
        }

        private void informationReceived(String action) {

            if (!carIsOff && !steeringSwitched){

                String[] expression = action.split("/");

                switch (expression[0]) {
                    case "A" -> {
                        gui.updateButton(action, id);
                        car.sendSignal(id, expression[1] + "/" + expression[2]);
                    }
                    case "E" -> pauseOrResumeGame();
                    case "G" -> setGameType(Integer.parseInt(expression[1]));
                    case "Z" -> closeGoal(id);
                    case "X" -> switchOpponentsSteering(id);
                    case "C" -> turnOffOpponentsCar(id);
                    case "S" -> getPowerUp(id);
                    case "Ready" -> {
                        this.isReady = true;
                        opponentsReady();
                    }
                    case "EndGame" -> endGame();
                }
            }
            else {
                powerUpIsActive(action);
            }
        }

        private void sendSwitchedSignal(String letter, String action){
            switch (letter){
                case "F" -> car.sendSignal(id, "L/" + action);
                case "B" -> car.sendSignal(id, "R/" + action);
                case "L" -> car.sendSignal(id, "B/" + action);
                case "R" -> car.sendSignal(id, "F/" + action);
            }
        }

        private void powerUpIsActive(String action){

                String[] expression = action.split("/");

                switch (expression[0]) {
                    case "A" -> {
                        gui.updateButton(action, id);
                        if (!carIsOff) {
                            sendSwitchedSignal(expression[1], expression[2]);
                        }
                    }
                    case "E" -> pauseOrResumeGame();
                    case "G" -> setGameType(Integer.parseInt(expression[1]));
                    case "Z" -> closeGoal(id);
                    case "X" -> switchOpponentsSteering(id);
                    case "C" -> turnOffOpponentsCar(id);
                    case "Ready" -> {
                        this.isReady = true;
                        opponentsReady();

                }
            }
        }


        public void setSteeringSwitched(boolean steeringSwitched) {
            this.steeringSwitched = steeringSwitched;
        }


        public void setCarIsOff(boolean carIsOff) {
            this.carIsOff = carIsOff;
        }

        private void disconnect(){
            if (id==1){
                player1 = null;
            }
            if(id == 2){
                player2 = null;
            }
        }

        @Override
        public void run() {
            while (!interrupted()) {
                try {

                    informationReceived(in.readLine());

                } catch (IOException e) {
                    disconnect();
                }
            }
        }
    }
}