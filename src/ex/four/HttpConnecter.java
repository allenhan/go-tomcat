package ex.four;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by han.j on 2016/7/17.
 */
public class HttpConnecter implements Runnable {

    private boolean shutdown=false;

    @Override
    public void run() {
        ServerSocket serverSocket=null;
        int port=8080;
        try {
            serverSocket=new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        while(!shutdown){
            try {
                Socket socket=serverSocket.accept();
                //process.assign(socket); asnyc

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Start(){
        Thread thread=new Thread(this);
        thread.start();
    }
}
