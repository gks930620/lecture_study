package com.study;

import com.study.calculate.Calculator;
import com.study.calculate.operator.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;

    private  final ExecutorService executorService= Executors.newFixedThreadPool(10);//쓰레드풀은 10개

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }


    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("CustomApplicaionServer started {} port", port);
            Socket clientSocket;
            logger.info("CustomWebApplicationServer watint for client");
            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("CustomWebApplicationServer  client connected");
                executorService.execute(new ClientRequestHandler(clientSocket)); //쓰레드를 매번만드는것이아니라
                //쓰레드풀에 있느ㅡㄴ거에 clientsocket 전달
            }
        }
    }

}












