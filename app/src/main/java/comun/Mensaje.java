package comun;

import java.io.Serializable;

/**
 * Created by Mateo on 16/10/17.
 */

public class Mensaje implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //private int indice;
    private String mensaje;

    public Mensaje(){

    }
    public Mensaje(String mensaje) {
        this.mensaje=mensaje;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}