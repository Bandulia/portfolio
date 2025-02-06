import serverSide.GamingTimer;

public class Main {
    public static void main(String[] args) {

            new serverSide.Server(6666);
            new ClientSide.Client("localhost", 6666);
            new ClientSide.Client("localhost", 6666);
    }
}