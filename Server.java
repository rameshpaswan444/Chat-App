  import java.io.PrintWriter;
  import java.net.*;
  import java.io.*;




   class Server {

    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;


    //constructor
    public Server()
    {
        try {
            server=new ServerSocket(7777);
             System.out.println("server is ready to accept connection");
             socket=server.accept();
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

     System.out.println("READER STARTED..");
     while(true)
     {
         try{

         String msg= br.readLine();
         if(msg.equals("exit"))
         {
            System.out.println("client terminated the chat");
             
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

             while(true )
            {
                try{
             BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
            String content=br1.readLine();
            out.println(content);
            out.flush();
                break;
            }catch(Exception e){
            e.printStackTrace();
        }
     }
};

    new Thread(r2) .start();
}
    
    public static void main(String[] args) 
    {
        
    
        
        System.out.println("server is running");
        
    new Server();
        
    }
}
