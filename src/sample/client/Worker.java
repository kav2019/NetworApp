package sample.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Worker {
    private static int port = 8089;
    private static String host = "localhost";
    private static String msgServer = "";
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    //необходимо написать что бы сообщения доходили от сервера и к серверу

    public Worker(){
        this(host, port);
    }

    private Worker(String host, int port) {
        connect(host, port);
    }

    private void connect(String host, int port){
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitMsg(Controller controller){
        Thread inMassage = new Thread( () -> {
            while (true){
                try {
                    msgServer = in.readUTF();
                    controller.getMsg(msgServer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        inMassage.start();
    }

    public String getMsgServer(){
        return msgServer;
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException e) {

        }
    }

    public void sendMsg(String msgClient){
        Thread thread = new Thread( () -> {
            try {
                out.writeUTF(msgClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
