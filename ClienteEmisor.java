public class ClienteEmisor extends Thread{
    private int id;
    private static int correosAProducir;
    private int correosProducidos;
    private BuzonEntrada buzonEntrada;

    public ClienteEmisor(int correosAProducir, int id, BuzonEntrada buzonEntrada){
        ClienteEmisor.correosAProducir=correosAProducir;
        this.correosProducidos=0;
        this.id=id;
    }


    @Override
    public void run(){
        int secuencial=id;

        while (correosProducidos<correosAProducir) {
            secuencial++;
            correosProducidos++;
            Mensaje mensaje;
            //Definimos si es mensaje de inicio, fin o otro
            if (correosProducidos==1) {
                mensaje= new Mensaje(secuencial);
                mensaje.setInicio(true);
                mensaje.setSPAM(false);
            }else if(correosProducidos==correosAProducir){
                mensaje= new Mensaje(secuencial);
                mensaje.setFin(true);
                mensaje.setSPAM(false);
            }else{
                mensaje= new Mensaje(secuencial);
            }
            //mandamos el mensaje al buzon de entrada
            buzonEntrada.IngresarMensaje(mensaje);
            
        }
    }
}
