package ex.four;

import java.net.Socket;

/**
 * Created by han.j on 2016/7/17.
 */
public class HttpProcesser implements Runnable {

    Socket socket=null;
    boolean stoped=false;
    boolean available=false;//�Ƿ����socket

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

    //�ȴ��������ṩһ���µ�socket
    private  synchronized Socket await()
    {
        //�޿���socket �ȴ�
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
