import java.io.*;
import java.net.*;
import java.util.Scanner;


public class MyServer {
    public static void TCP_server() {
        try {
            System.out.println("Cho ket noi den client!");
            ServerSocket server = new ServerSocket(8888);
            System.out.println("Da co ket noi den tu: " + server.getInetAddress().getHostAddress() + "!");
            do {
                Socket s = server.accept();
                //nhan thong diep
                PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                // lay tin nhan tu client in ra console
                String message = in.readLine();
                System.out.println("<Client>: " + message);
                if (message.equals("thoat")){
                    System.out.println("Close server");
                    String close = "***Server close***";
                    out.println(close);
                    out.flush();
                    in.close();
                    out.close();
                    s.close();
                    break;
                }

                //read input from screen
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //doc du lieu tu ban phim
                System.out.print("<Server>: ");
                String send = inFromUser.readLine();
//                String send = message.toUpperCase();
                //gui thong diep lai client
                out.println(send);
                out.flush();
                //dong ket noi
                in.close();
                out.close();
                s.close();
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void UDP_server() {
        try {
            // khoi tao doi tuong
            System.out.println("Chờ gói tín hiệu từ client!");
            do {
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                DatagramSocket serverSocket = new DatagramSocket(8888); //socket cua server
                //nhan du lieu tu client
                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);
                serverSocket.receive(receivePacket);
                // doc va xu ly du lieu nhan duoc
                String message = new String(receivePacket.getData());
                System.out.println("<Client>: " + message);
                if (message.equals("thoat")) {
                    String messageClose = "Đã thoát chương trình!";
                    sendData = messageClose.getBytes();
                    InetAddress IpClient = receivePacket.getAddress();
                    int PortClient = receivePacket.getPort();
                    DatagramPacket sendPacket = new DatagramPacket(sendData,
                            sendData.length, IpClient, PortClient);
                    //gui du lieu cho client
                    serverSocket.send(sendPacket);
                    serverSocket.close();
                    break;
                }
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //doc du lieu tu ban phim
                System.out.print("<Server>: ");
                String sentence = inFromUser.readLine();
                sendData = sentence.getBytes();
                //uppercase
//                String send = message.toUpperCase();
//                sendData = send.getBytes();
                //dong goi goi tin de gui di
                InetAddress IpClient = receivePacket.getAddress(); // lay ip client
                int PortClient = receivePacket.getPort(); //lay port client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpClient, PortClient);
                //gui du lieu cho client
                serverSocket.send(sendPacket);
                serverSocket.close();
//                break;
            } while (true);
        } catch (Exception ex) {
            System.out.println("Loi : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Running server with tcp or udp? : ");
        String i= sc.nextLine();
        switch (i) {
            case "tcp" -> TCP_server();
            case "udp" -> UDP_server();
            default -> TCP_server();
        }
    }
}

