package com.example.demo;


import javax.annotation.Resource;

import com.example.demo.server.Client;
import com.example.demo.server.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.FilesStorageService;

@SpringBootApplication
public class SpringBootUploadFilesApplication implements CommandLineRunner {
    @Resource
    FilesStorageService storageService;
    private Server server;
    private Client client;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUploadFilesApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        this.server = new Server();
        System.out.println("about to connect to the server");
        server.start();
        this.client = new Client();
        System.out.println("about to launch the client");
        client.start();
        storageService.init();
    }
}