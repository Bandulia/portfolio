package ClientSide;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * The controller class on the client side. Connects through TCP/IP connection to the game server.
 */
public class Client {

        private final String address;
        private final int port;
        private Socket socket;
        private Communicator communicator;
        private MenuFrame frame;
        private GameFrame gameFrame;


    /**
     * Constructor that needs the server address and port number to later on open a TCP/IP connection.
     * @param address of the server
     * @param port number for the connection
     */
    public Client(String address, int port) {
            this.address = address;
            this.port = port;

            connectAndSetup();
        }


    /**
     * This method send a string telling the server what type of client this is. The server responds with a
     * player number before the client opens up the GUI.
     */
    private void connectAndSetup(){

            try{

                socket = new Socket(address,port);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.write("player");
                out.newLine();
                out.flush();
                setUpMenuFrame(Integer.parseInt(in.readLine()));
                this.communicator = new Communicator(in, out);
                communicator.start();

            } catch (IOException e) {
                System.out.println("Couldn't find the game server");
            }
        }

    private void setUpMenuFrame(int id){
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
                    frame = new MenuFrame(getClient(), id);
                    frame.setVisible(true);
                }
            });
        }

        public Client getClient(){
            return this;
        }

        public void setGameFrame(GameFrame frame){
            this.gameFrame = frame;
        }

        public void sendInformationToServer(String action){
            communicator.send(action);
        }

        public void countDown(){

        }

        private void disconnect() {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }


    /**
     * Communicator is a thread that listens for strings from the game server and sends string to the server.
     */
    private class Communicator extends Thread{

            private BufferedReader in;
            private BufferedWriter out;


            public Communicator(BufferedReader in, BufferedWriter out){
                this.in = in;
                this.out = out;
            }


            public void send(String action){
                try {
                    out.write(action);
                    out.newLine();
                    out.flush();

                } catch (IOException e) {
                    System.out.println("Could not send driving signals");
                    throw new RuntimeException(e);
                }
            }


            private void receivedInfo(String info){

                String[] expression = info.split("/");

                    switch (expression[0]){
                        case "P" -> gameFrame.pause();
                        case "R" -> gameFrame.resume();
                        case "G" -> frame.setGameType(expression[1]);
                        case "W" -> gameFrame.setWinnerPanel(expression[1].equals("1"));
                        case "C" -> countDown();
                        case "Score" -> gameFrame.updateScores(expression[1]);
                        case "endGame" -> gameFrame.endGame(Integer.parseInt(expression[1]));
                        case "Ready" -> frame.setUpGameFrame();
                        case "Power" -> frame.incrementPowerUp();
                        case "Start" -> gameFrame.countDown();
                    }
            }


            @Override
            public void run() {

                while (!interrupted()) {
                    try {
                        receivedInfo(in.readLine());
                    } catch (IOException e) {
                        disconnect();
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
