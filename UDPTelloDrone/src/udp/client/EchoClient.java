package udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * EchoClient for sending UDP data packets to Tello drone
 * @author Lachlan Blennerhassett
 * - Credit and thanks to Baeldung tutorials for the client/server code:
 * - https://www.baeldung.com/udp-in-java
 */

public class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;
 
    private byte[] buf;
    
    /*
     * Tello SDK details:
     * https://terra-1-g.djicdn.com/2d4dce68897a46b19fc717f3576b7c6a/Tello%20%E7%BC%96%E7%A8%8B%E7%9B%B8%E5%85%B3/For%20Tello/Tello%20SDK%20Documentation%20EN_1.3_1122.pdf
     * Tello drone IP: 192.168.10.1 UDP Port: 8889
     */
 
    public EchoClient() throws UnknownHostException, SocketException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("192.168.10.1");
    }
 
    public String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet 
          = new DatagramPacket(buf, buf.length, address, 8889);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
          packet.getData(), 0, packet.getLength());
        return received;
    }
 
    public void close() {
        socket.close();
    }
}
