package ex.pipe;

/**
 * Created by han.j on 2016/9/14.
 */
public interface ValueContext {

    public void  InvokeNext(String request,String response ) throws Exception;

}
