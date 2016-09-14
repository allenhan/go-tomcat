package ex.pipe;

/**
 * Created by han.j on 2016/9/13.
 */
public class PipeTest {
    public static void main(String[] args){
        PipeChainStand pipeChainStand=new PipeChainStand();

        CheckValue1 checkValue1=new CheckValue1();
        pipeChainStand.AddValue(checkValue1);

        CheckValu2 checkValu2=new CheckValu2();
        pipeChainStand.SetBaseValue(checkValu2);

        try {
            pipeChainStand.Invoke("hello pipe","reee");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
