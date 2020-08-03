package com.example.demo.server;

import java.io.File;
import java.io.IOException;

public class Client extends Thread {

    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder("bash run_client.sh".split(" "));
        Process server;
        int exitCode;
        try {
            server = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            exitCode = server.waitFor();
        } catch (InterruptedException e) {
            System.out.println("client exit now\n");
            return;
        }
        server.destroy();
    }
}