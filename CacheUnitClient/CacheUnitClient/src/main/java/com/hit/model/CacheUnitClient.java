
package com.hit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class CacheUnitClient {
    public String send(String request) throws IOException {

        Socket clientSocket = new Socket("localhost", 34567);
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        out.writeUTF(request);
        out.flush();


        String ans = in.readUTF();

        clientSocket.close();
        in.close();
        out.close();

        return ans;

    }

}