package ex.four;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

/**
 * Created by han.j on 2016/7/17.
 * 连接器
 * 创建服务器套接字 ，维护httpprocessor 池，提供 http 请求 服务
 */
public class HttpConnecter implements Runnable {

    private boolean shutdown = false;
    private Stack processor = new Stack();
    protected int minProcessor = 5;
    private int maxProcessor = 10;

    public int getMinProcessor() {
        return minProcessor;
    }

    public void setMinProcessor(int minProcessor) {
        this.minProcessor = minProcessor;
    }

    public int getMaxProcessor() {
        return maxProcessor;
    }

    public void setMaxProcessor(int maxProcessor) {
        this.maxProcessor = maxProcessor;
    }

    private int curProcessor = 0;//

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!shutdown) {
            try {
                Socket socket = serverSocket.accept();
                //
                HttpProcesser processer = createProcessor();
                processer.assign(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void recyleProcessor(HttpProcesser httpProcesser) {
        processor.push(httpProcesser);
    }

    public void Start() {
        for (int i = 0; i < minProcessor; i++) {
            HttpProcesser processer = newProcessor();
            recyleProcessor(processer);
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    private HttpProcesser newProcessor() {
        HttpProcesser processer = new HttpProcesser(this);
        curProcessor = curProcessor + 1;
        return processer;
    }

    private HttpProcesser createProcessor() {
        HttpProcesser processer = (HttpProcesser) processor.pop();
        if (processer == null) {
            if (curProcessor < maxProcessor) {
                processer = newProcessor();
                recyleProcessor(processer);
            }
        }
        if (processer != null) processer.Start();
        return processer;
    }
}
