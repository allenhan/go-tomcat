package ex.pipe;

/**
 * Created by han.j on 2016/9/13
 * Í¨µÀ
 */
public interface PipeChain {
     void AddValue(Value value);
     void RemoveValue(Value value);
     void Invoke(String request,String response) throws Exception;
}
