public class FiltroSPAM extends Thread{
    
    private static int numInicio=0;
    private static int numFinales=0;
    private int numClientes;
    private static boolean enviadoFin=false;

    private BuzonCuarentena buzonCuarentena;
    private BuzonEntrada buzonEntrada;
    private BuzonEntrega buzonEntrega;

    public FiltroSPAM(int numClientes ,BuzonCuarentena buzonCuarentena, BuzonEntrada buzonEntrada, BuzonEntrega buzonEntrega){
        this.buzonCuarentena=buzonCuarentena;
        this.buzonEntrada=buzonEntrada;
        this.buzonEntrega=buzonEntrega;
        this.numClientes=numClientes;
    }

    @Override
    public void run(){
        System.out.println("=!== Se inicio El filtro =!==");        
        while (numFinales<numClientes || numInicio<numClientes) {
            Mensaje mensaje= buzonEntrada.SacarMensaje();
            if(mensaje!=null){
                if (mensaje.getSPAM()) {
                    System.out.println("El mensaje: "+mensaje.getId()+" es SPAM y se envia a cuarentena");
                    buzonCuarentena.Ingresar(mensaje);
                }else if (numFinales<=numClientes) {
                    System.out.println("El mensaje: "+mensaje.getId()+" no es SPAM y se envia al buzon de entrega");
                    if (mensaje.getFinal()) {
                        synchronized(this){
                            System.out.println("Se recibio un mensaje de finalizacion del cliente emisor");
                            numFinales++;
                        }
                        buzonEntrega.agregar(mensaje);
                    }else if(mensaje.getInicio()){
                        synchronized(this){
                            System.out.println("Se recibio un mensaje de inicio del cliente emisor");
                            numInicio++;
                        }
                        buzonEntrega.agregar(mensaje);
                    }else{
                        System.out.println("El mensaje: "+mensaje.getId()+" es un mensaje normal");
                        buzonEntrega.agregar(mensaje);
                    }
                 }
                 
            }
        }
        
        if (!enviadoFin) {
                Mensaje mensaje= new Mensaje(-1);
                mensaje.setTiempo(20002);
                buzonCuarentena.Ingresar(mensaje);
                buzonEntrega.agregar(mensaje);
                enviadoFin=true;
        }
        
                   
        System.out.println("====Termino el filtro de SPAM====");
    }
}
