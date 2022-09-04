import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static void TCP_client() {
        try {
            System.out.println("Ket noi thanh cong den server!");
            do {
                Socket s = new Socket("localhost", 8888);
                //client gui thong diep
                PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //doc du lieu tu ban phim
                System.out.print("<Client>: ");
                String send = inFromUser.readLine();

                out.println(send);
                out.flush();
                String message = in.readLine();
                System.out.println("<Server>: " + message);
                in.close();
                out.close();
                s.close();
            } while (true);
        } catch (Exception ex) {
            System.out.println("Loi: " + ex.getMessage());
        }
    }

    public static void UDP_client() {
        try {
            System.out.println("Hãy nhập dữ liệu gửi cho server:");
            do {
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                DatagramSocket clientSocket = new DatagramSocket(6789); // khoi tao socket cho client
                InetAddress IpServer = InetAddress.getByName("localhost"); // ip cua server
//            String message = "hello server";
//            sendData = message.getBytes();

                //doc du lieu gui di qua input user trong console
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //doc du lieu tu ban phim
                System.out.print("<Client>: ");
                String sentence = inFromUser.readLine();
                sendData = sentence.getBytes();
                //
                //data gui di
                int PortServer = 8888; //port cua server
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpServer, PortServer); //dong goi du lieu
                //gui goi tin len server
                clientSocket.send(sendPacket); //gui goi du lieu di
                //nhan goi tin phan hoi tu server
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                // xu ly goi tin nhan tu server
                String message = new String(receivePacket.getData());
                System.out.println("<Server>: " + message);
                //dong socket
                clientSocket.close();
            } while (true);
        } catch (Exception ex) {
            System.out.println("Loi :" + ex.getMessage());

        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Running client with tcp or udp? : ");
        String i= sc.nextLine();
        switch (i) {
            case "tcp" -> TCP_client();
            case "udp" -> UDP_client();
            default -> TCP_client();
        }
    }
}
