import utils.PacketReader;
import utils.PacketSender;

import java.net.*;


public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;

    public UDPClient() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            System.out.println("Connection to server established...");
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        PacketReader packetReader = new PacketReader(socket);
        PacketSender packetSender = new PacketSender(socket, address);

        Thread pR = new Thread(packetReader);
        Thread pS = new Thread(packetSender);

        pR.start();
        pS.start();
    }
}
