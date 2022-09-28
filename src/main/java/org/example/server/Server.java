package org.example.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread{

    private boolean running = false;
    private static final int port = 10001;
    private ServerSocket serverSocket;



    public void startServer(){
        try{
            serverSocket = new ServerSocket(port,5);
            this.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stopServer(){
        running = false;
        this.interrupt();
    }
    //ServerSocket server = new ServerSocket(PORT,5);
    @Override
    public void run(){
        running = true;
        while (running){
            try{
                Socket socket = serverSocket.accept();

                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private final Application application;

    public Server(Application application) {
        this.application = application;
    }

    public String handle(String requestString){
        Request request = RequestBuilder.build(requestString);
        Response response = this.application.handle(request);

        return response.toString();
    }

}

class RequestHandler extends Thread{

    private Socket socket;

    RequestHandler( Socket socket){
        this.socket = socket;
    }

    @Override
    public void run (){
        try{
            System.out.println("Received a connection");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            out.println("Echo Server 1.0");
            out.flush();

            String line = in.readLine();
            while(line != null && line.length() > 0){
                 out.println("Echo: " +line);
                 out.flush() ;
                 line = in.readLine();
            }

            in.close();
            out.close();
            socket.close();
            System.out.println("Connection closed");

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
