import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by han.j on 2016/7/13.
 */
public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter printWriter=servletResponse.getWriter();
        printWriter.println("hello ");
        printWriter.print("world ");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
