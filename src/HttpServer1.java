import java.io.*;
import java.lang.ref.ReferenceQueue;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by han.j on 2016/7/13.
 */
public class HttpServer1 {

    private boolean shutdown=false;

    public static void main(String[] args){
          HttpServer1 server1=new HttpServer1();
        server1.await();
    }

    private void await() {
        ServerSocket serverSocket=null;
        int port=8080;
        try {
            serverSocket=new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket socket=null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        while(!shutdown){
            try {
                socket=serverSocket.accept();
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();

                Request request=new Request(inputStream);
                request.Parse();

                Response response=new Response(outputStream);
                response.setRequest(request);

                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 servletProcessor1=new ServletProcessor1();
                    servletProcessor1.Proeces(request,response);
                }else{
                    StaticResourceProcessor processor=new StaticResourceProcessor();
                    processor.process(request,response);
                }
                socket.close();
                shutdown=request.getUri().equals("/ShutDown");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
