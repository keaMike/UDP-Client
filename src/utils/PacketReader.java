package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PacketReader extends Thread {

    private DatagramSocket socket;
    private byte[] buf = new byte[256];

    public PacketReader(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            String msg = readMsg();
            System.out.println(msg);
        }
    }

    private String readMsg() {
        try {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            return new String(packet.getData(), 0, packet.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
