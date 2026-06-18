package Day_5;
import java.net.*;
import java.io.*;
public class EchoServer {
    public static String packettostr(DatagramPacket p){
        return new String(p.getData(), 0, p.getLength());
    }
    public static void main(String[] args) throws IOException{
        final int PORT = 51091;
        final int BUFF_SIZE  = 65535;

        DatagramSocket ds = new DatagramSocket(PORT); // single socket
        byte[] buffer = new byte[BUFF_SIZE];
        
        System.out.println("Server ready on port " + PORT);

        while(true){
            DatagramPacket receive_packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(receive_packet); // wait for client

            String msg = packettostr(receive_packet);
            InetAddress client_ip = receive_packet.getAddress();
            int client_port = receive_packet.getPort();

            String reply = "Echo: " + msg;
            byte[] buffer_send = reply.getBytes();
            DatagramPacket send_packet = new DatagramPacket(buffer_send, buffer_send.length, client_ip, client_port);
            ds.send(send_packet);

            System.out.println("Received from client " + client_ip + ":" + client_port + " -> " + msg.toUpperCase());
        }
    }
}