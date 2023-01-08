

import java.net.*;
import java.io.*;
public class Client {

    Socket socket;

    BufferedReader br;
    PrintWriter out;


    public Client()
    {
        try {

            System.out.println("sending request to server");
            socket=new Socket("127.0.0.1",7777);
            System.out.println("connection is done");
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public void startReading()
    {
     //thread-read garera  dinxa
     Runnable r1=()->{
         
     System.out.println("Reader started..");
      while(true)
     {
         try{

        String msg= br.readLine();
        if(msg.equals("exit"))
        {
           System.out.println("server terminated the chat");
            
           break; 
        }
        System.out.println("client:"+msg);
       }catch(Exception e)
       {
       e.printStackTrace();
       }
   }
};
     new Thread(r1).start(); 

    }

    public void startWriting()
    {
              //thread-data user linxa ra ssend garxa client lai
      
             
              Runnable r2=()->{

                System.out.println("writer started..");
                

        while(true)
         {
             try{
                 BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
            String content=br1.readLine();
            out.println(content);
            out.flush();
        }catch(Exception e)
        {
            e.printStackTrace();

        }
    }
};
    new Thread(r2) .start();
    }
    public static void main(String[] args) {
        System.out.println("this is client");
        new Client();
    }
}
