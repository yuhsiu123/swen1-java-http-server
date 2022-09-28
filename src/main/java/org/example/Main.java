package org.example;

import org.example.application.demo.DemoApp;
import org.example.server.Response;
import org.example.server.Server;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Main {
    //java -jar main.jar --version --help
    public static void main(String[] args) {
        Server server = new Server(new DemoApp());
        String request =
                """
                POST /hello HTTP/1.1
                Authorization: Basic hello-world-token
                Content-Type: application/json
                User-Agent: PostmanRuntime/7.28.3
                Accept: */*
                Host: localhost:10001
                Accept-Encoding: gzip, deflate, br
                Connection: keep-alive
                Content-Length: 29
                
                {
                    "message": "World!"
                }
                """;

        String response = server.handle(request);
        System.out.println(response);
        server.startServer();
        server.run();



        //List<String>stringList = new ArrayList<>();
        //stringList = new LinkedList<>();
        //stringList = new Vector<>();
    }
}