package ex.four;

import java.net.Socket;

/**
 * Created by han.j on 2016/7/17.
 */
public class HttpProcesser implements Runnable {

    Socket socket=null;
    boolean stoped=false;
    boolean available=false;//是否可用socket

    @Override
    public void run() {
        while(!stoped){
            Socket socket=await();
            if(socket==null) continue;

        }
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

}
