package com.harm.unit.io.network;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketStudy0101 implements Unit {
    private Logger logger = LoggerFactory.getLogger(SocketStudy0101.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int portNumber = 3000; //
        boolean infinit = true;

        serverSocket = new ServerSocket(portNumber);
        this.logger.debug("SERVER SOCKER CREATION.");

        class Worker implements Runnable {
            private Logger logger = LoggerFactory.getLogger(Worker.class);
            private final String workerName;
            private final Socket socket;
            public Worker(String workerName, Socket socket) {
                this.workerName = workerName;
                this.socket = socket;
            }
            @Override
            public void run() {
                this.logger.debug(this.workerName + " THREAD START LOCAL {} REMOTE {}", this.socket.getLocalSocketAddress(), this.socket.getRemoteSocketAddress());
                BufferedReader br = null;
                BufferedWriter bw = null;
                while(true) {
                    try {
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String msg = br.readLine();
                        this.logger.debug("RECV {}", msg);

                        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        bw.write(this.workerName + "[" + msg + "]\n");
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.logger.debug(this.workerName + " THREAD END");
            }
        }

        int i = 0;

        while(infinit) {
            socket = serverSocket.accept();
            this.logger.debug("ACCEPT LOCAL {}:{} REMOTE {}:{}", socket.getLocalAddress(), socket.getLocalPort(), socket.getInetAddress(), socket.getPort());

            new Thread(new Worker("WORKER"+(i++), socket)).start();

        }


        return null;
    }
}
