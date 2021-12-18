package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class EchoServer {
    private static final int SERVER_PORT = 8089;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            System.out.println("Ожидание подключения...");
            Socket socket = serverSocket.accept();
            System.out.println("Подключение установлено!");
            new Worker(socket);
        } catch (IOException e) {
            System.out.println("");
        }
    }

}

class Worker{
    public Worker(Socket socket){
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            Thread threadIN = new Thread( () -> {
                while (true){
                    try {
                        String massage = in.readUTF();
                        System.out.println("Сообщение от пользователя: " + massage);
                        if (massage.equals("/exit")){
                            System.out.println("Лдиент отключился");
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadOUT = new Thread( () -> {
                while (true){
                    String massage = scanner.nextLine();
                    if (massage.equals("/exit")){
                        break;
                    }
                    try {
                        out.writeUTF(massage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            threadIN.start();
            threadOUT.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
