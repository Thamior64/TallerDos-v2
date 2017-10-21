package com.example.joseossa.tallerdos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import comun.Mensaje;

/**
 * Created by estudiante on 20/10/17.
 */

public class Cliente implements Runnable {

    public static Cliente ref;

    private Socket s;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;

    public Cliente() {
        s = null;
    }

    public static Cliente getInstance() {
        if (ref == null) {
            ref = new Cliente();
            new Thread(ref).start();
        }
        return ref;
    }

    @Override
    public void run() {
        while (true) {
            if (s == null) {// si no se ha creado ha cnectado, conecte
                try {
                    s = new Socket(InetAddress.getByName("192.168.1.61"), 8000);

                    entrada = new ObjectInputStream(s.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            recibirMensaje();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void recibirMensaje() {
        try {
            Mensaje m = (Mensaje) entrada.readObject();
            System.out.println("Cliente lee: " + m.getMensaje());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(Mensaje m) {
        try {
            salida = new ObjectOutputStream(s.getOutputStream());
            salida.writeObject(m);
            salida.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}