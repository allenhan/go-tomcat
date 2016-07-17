import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by han.j on 2016/7/17.
 */
public class ServletProcessor1 {
    public  void Proeces(Request request,Response response){
        String uri=request.getUri();
        String servletName=uri.substring(uri.lastIndexOf('/') + 1);
        URLClassLoader loader=null;
        URL[] url=new URL[1];
        URLStreamHandler streamHandler=null;
        File classPath=new File(Constants.WEB_ROOT);
        try {
            String repository= new URL("file",null,classPath.getCanonicalPath()+File.separator).toString();
           url[0]=new URL(null,repository,streamHandler);
            loader=new URLClassLoader(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myclass=null;

        try {
            myclass=loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Servlet servlet=null;

        try {
            servlet=(Servlet)myclass.newInstance();
            servlet.service((ServletRequest)request,(ServletResponse)response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
