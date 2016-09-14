package ex.pipe;

/**
 * Created by han.j on 2016/9/13.
 */
public class CheckValu2 implements Value {

    @Override
    public void Invoke(String request, String response, ValueContext context) throws Exception {

        System.out.println("Check2");
    }
}
