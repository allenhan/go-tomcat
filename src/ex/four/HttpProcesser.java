package ex.four;

import ex.four.*;
import ex.four.HttpConnecter;

import java.net.Socket;

/**
 * Created by han.j on 2016/7/17.
 */
public class HttpProcesser implements Runnable {

    Socket socket=null;
    boolean stoped=false;
    boolean available=false;//是否可用socket
    HttpConnecter connecter;

    public HttpProcesser(HttpConnecter connecter){
        this.connecter=connecter;
    }

    @Override
    public void run() {
        while(!stoped){
            Socket socket=await();
            if(socket==null) continue;
            process(socket);
            connecter.recyleProcessor(this);
        }
    }

    private  void process(Socket socket){

    }

    synchronized void assign(Socket socket) {
        while (available){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.socket=socket;
        available=true;
        notifyAll();
    }

    //等待连接器提供一个新的socket
    private  synchronized Socket await()
    {
        //无可用socket 等待
       while (!available){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        Socket socket=this.socket;
        available=false;
        notifyAll();
        return socket;
    }

    public  void Start(){
        Thread thread=new Thread(this);
        thread.run();
    }
}
