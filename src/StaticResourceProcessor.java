import java.io.IOException;

/**
 * Created by han.j on 2016/7/17.
 */
public class StaticResourceProcessor {

    public  void process(Request request,Response response){
        try {
             response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
