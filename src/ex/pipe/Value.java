package ex.pipe;

/**
 * Created by han.j on 2016/9/13.
 * ·§
 */
public interface Value {
     void Invoke(String request,String response,ValueContext context) throws Exception;
}
