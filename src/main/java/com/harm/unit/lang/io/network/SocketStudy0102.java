package com.harm.unit.lang.io.network;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketStudy0102 implements Unit {
    private Logger logger = LoggerFactory.getLogger(SocketStudy0102.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        Socket socket = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        String ipNumber = "127.0.0.1";
        int portNumber = 3000;
        String msg = "";

        socket = new Socket(ipNumber, portNumber);
        this.logger.debug("CLIENT SOCKET CREATION");

        while(true) {

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.logger.debug("TYPE MESSAGE : ");
            br = new BufferedReader(new InputStreamReader(System.in));
            msg = br.readLine();

            if("EXIT".equalsIgnoreCase(msg)) {
                break;
            }

            bw.write(msg + "\n");
            bw.flush();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.logger.debug("RECV {}", br.readLine());
        }

        socket.close();
        return null;
    }
}
