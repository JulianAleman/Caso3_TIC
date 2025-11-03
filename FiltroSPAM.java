public class FiltroSPAM extends Thread{
    
    private int numInicio;
    private int numFinales;
    private int numClientes;

    private BuzonCuarentena buzonCuarentena;
    private BuzonEntrada buzonEntrada;
    private BuzonEntrega buzonEntrega;

    public FiltroSPAM(int numClientes ,BuzonCuarentena buzonCuarentena, BuzonEntrada buzonEntrada, BuzonEntrega buzonEntrega){
        this.buzonCuarentena=buzonCuarentena;
        this.buzonEntrada=buzonEntrada;
        this.buzonEntrega=buzonEntrega;
        this.numInicio=0;
        this.numFinales=0;
        this.numClientes=numClientes;
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
                        numFinales++;
                    }else if(mensaje.getInicio()){
                        numInicio++;
                    }else{buzonEntrega.agregar(mensaje);}
                 }
                 
            }
        }
        Mensaje mensaje= new Mensaje(numFinales);
                mensaje.setFin(true);
                mensaje.setSPAM(false);
        if (numFinales==numClientes) {
                    if(mensaje.getFinal()){
                         buzonCuarentena.Ingresar(mensaje);
                         buzonEntrega.agregar(mensaje);
                     }
                 }
        System.out.println("====Termino el filtro de SPAM====");
    }
}
