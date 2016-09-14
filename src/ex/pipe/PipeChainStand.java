package ex.pipe;

import com.sun.corba.se.impl.io.ValueUtility;

import java.util.ArrayList;

/**
 * Created by han.j on 2016/9/13.
 */
public class PipeChainStand implements PipeChain {

    private Value[] values=new Value[0];

    private Value BaseValue;

    public PipeChainStand()
    {

    }

    @Override
    public void AddValue(Value value) {
        synchronized (values) {
            Value[] tvalues = new Value[values.length + 1];
            System.arraycopy(values,0,tvalues,0,values.length);
            tvalues[values.length ]=value;
            values=tvalues;
        }
    }

    @Override
    public void RemoveValue(Value value) {
       synchronized (values){
           int j=-1;
           for(int i=0;i<values.length;i++){
               if(values[i]==value)
               {
                   j=i;
                   break;
               }
           }
           if(j<0) return;
           Value[] values1=new Value[values.length-1];
           int n=0;
           for(int i=0;i<values.length;i++){
               if(i==j)continue;
               values1[n++]=values[i];
           }
       }
    }

    public void SetBaseValue(Value value){
        this.BaseValue=value;
    }

    @Override
    public void Invoke(String request, String response) throws Exception {
        new StatndValueContext().InvokeNext(request,response);
    }

    protected  class  StatndValueContext implements ValueContext{

        protected int idx=0;

        @Override
        public void InvokeNext(String request, String response) throws Exception {
            int cur=idx;
            idx=idx+1;
            if(cur<values.length){
                values[cur].Invoke(request,response,this);
            }
            else if(cur==values.length&&BaseValue!=null) {
               BaseValue.Invoke(request,response,this);
            }
            else
            {
               throw new Exception("No Value");
            }
        }
    }
}
