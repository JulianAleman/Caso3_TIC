import java.util.List;

public class BuzonEntrada {
    private int capacidad;
    private List<Mensaje> mensajes;
    private int totalMensajes;

    public BuzonEntrada(int capacidad, int totalMensajes){
        this.capacidad=capacidad;
        this.totalMensajes=totalMensajes;
    }


    public synchronized void IngresarMensaje(Mensaje mensaje) throws InterruptedException{
        while (mensajes.size()==capacidad) {
            wait();
        }
        mensajes.add(mensaje);
        totalMensajes--;
        notify();
    }

    public synchronized Mensaje SacarMensaje() throws InterruptedException{
        Mensaje mensaje=null;
        while (mensajes.size()==0) {
            wait();
        }

        if (totalMensajes>0){
            mensaje=mensajes.removeFirst();
            notify();
        }

        return mensaje;
    }
}
