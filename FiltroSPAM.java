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
        while (numFinales<=numClientes || numInicio<=numClientes) {
            System.out.println("FILTRO");
            Mensaje mensaje= buzonEntrada.SacarMensaje();
            if (mensaje.getSPAM()) {
                buzonCuarentena.Ingresar(mensaje);
            }else{
                if (mensaje.getFinal()) {
                    numFinales++;
                }else if(mensaje.getInicio()){
                    numInicio++;
                }
                buzonEntrega.agregar(mensaje);
            }
        }
    }
}
