import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by han.j on 2016/7/13.
 */
public class Response implements ServletResponse {

    private  static  final int BUFFER_SIZE=1024;
    private   Request request;
    private OutputStream outputStream=null;
    PrintWriter printWriter=null;

    public void setRequest(Request request){
         this.request=request;
    }

    public Response(OutputStream outputStream){
        this.outputStream=outputStream;
    }

    public void sendStaticResource() throws IOException {
     byte[] bytes=new byte[BUFFER_SIZE];
        FileInputStream fileInputStream=null;
     try {
         File file=new File(Constants.WEB_ROOT,request.getUri());
         fileInputStream=new FileInputStream(file);
         int ch=fileInputStream.read(bytes,0,BUFFER_SIZE);
         while (ch!=-1){
              outputStream.write(bytes,0,ch);
              ch=fileInputStream.read(bytes,0,BUFFER_SIZE);
         }
     }
      catch (FileNotFoundException ex){

      }
        finally {
         if(fileInputStream!=null)
             fileInputStream.close();
     }
    }


    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        printWriter=new PrintWriter(outputStream,true);
        return printWriter;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
