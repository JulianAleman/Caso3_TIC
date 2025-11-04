import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class FiltroSPAM extends Thread{
    
    private static int numInicio;
    private static int numFinales;
    private int numClientes;

    private BuzonCuarentena buzonCuarentena;
    private BuzonEntrada buzonEntrada;
    private BuzonEntrega buzonEntrega;
    private CyclicBarrier barrera; 

    public FiltroSPAM(int numClientes ,BuzonCuarentena buzonCuarentena, BuzonEntrada buzonEntrada, BuzonEntrega buzonEntrega, CyclicBarrier barre){
        this.buzonCuarentena=buzonCuarentena;
        this.buzonEntrada=buzonEntrada;
        this.buzonEntrega=buzonEntrega;
        this.numClientes=numClientes;
        barrera=barre;
    }

    @Override
    public void run(){
        System.out.println("=!== Se inicio El filtro =!==");        
        while (numFinales<numClientes || numInicio<numClientes) {
            Mensaje mensaje= buzonEntrada.SacarMensaje();
            if(mensaje!=null){
                if (mensaje.getSPAM()) {
                    buzonCuarentena.Ingresar(mensaje);
                }else if (numFinales<=numClientes) {
                    if (mensaje.getFinal()) {
                        synchronized(this){
                            System.out.println("Se recibio un mensaje de finalizacion del cliente emisor");
                            numFinales++;
                        }
                    }else if(mensaje.getInicio()){
                        synchronized(this){
                            System.out.println("Se recibio un mensaje de inicio del cliente emisor");
                            numInicio++;
                        }
                        buzonEntrega.agregar(mensaje);
                    }else{buzonEntrega.agregar(mensaje);}
                 }
            }
        }
        
        try {
            barrera.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("FiltroSPAM interrupted while waiting at the barrier: " + e.getMessage());
        } catch (BrokenBarrierException e) {
            System.err.println("Barrier broken while FiltroSPAM was waiting: " + e.getMessage());
        }
        System.out.println("====Termino el filtro de SPAM====");
    }
}
