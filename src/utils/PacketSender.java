package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class PacketSender extends Thread {

    private Scanner sc = new Scanner(System.in);
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf = new byte[256];

    public PacketSender(DatagramSocket socket, InetAddress address) {
        this.socket = socket;
        this.address = address;
    }
    @Override
    public void run() {
        while (true) {
            try {
                String msg = sc.nextLine();
                if (msg.equalsIgnoreCase("end")) close();
                buf = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        socket.close();
    }
}
