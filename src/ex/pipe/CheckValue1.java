package ex.pipe;

/**
 * Created by han.j on 2016/9/13.
 */
public  class CheckValue1 implements Value {

    @Override
    public void Invoke(String request, String response, ValueContext context) throws Exception {
        context.InvokeNext(request,response);
        System.out.println("Check1");
    }
}
