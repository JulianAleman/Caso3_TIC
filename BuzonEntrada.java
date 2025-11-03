import java.util.List;

public class BuzonEntrada {
    private int capacidad;
    private List<Mensaje> mensajes;
    private int totalMensajes;

    public BuzonEntrada(int capacidad, int totalMensajes){
        this.capacidad=capacidad;
        this.totalMensajes=totalMensajes;
    }


    public synchronized void IngresarMensaje(Mensaje mensaje) {
        while (mensajes.size()==capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        mensajes.add(mensaje);
        totalMensajes--;
        notify();
    }

    public synchronized Mensaje SacarMensaje(){
        Mensaje mensaje=null;
        while (mensajes.size()==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (totalMensajes>0){
            mensaje=mensajes.removeFirst();
            notify();
        }

        return mensaje;
    }
}
