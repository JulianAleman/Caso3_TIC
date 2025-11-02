import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int numeroCE;
    private static int numMPC;
    private static int numSPAM;
    private static int numSE;
    private static int CapBE;
    private static int CapBEN;
    public static void main(String[] args) {
        // LEER EL ARCHIVO
        try (BufferedReader br= new BufferedReader(new FileReader("./config.txt"))) {
            String linea;
            while((linea= br.readLine())!= null){
                String nombr= linea.split(":")[0].trim();
                int val= Integer.parseInt(linea.split(":")[1].trim());
                switch (nombr) {
                        case "Número de clientes emisores":
                            numeroCE = val;
                            break;
                        case "Número de mensajes por cliente":
                            numMPC = val;
                            break;
                        case "Número de filtros de spam":
                            numSPAM = val;
                            break;
                        case "Número de servidores de entrega":
                            numSE = val;
                            break;
                        case "Capacidad del buzón de entrada":
                            CapBE = val;
                            break;
                        case "Capacidad del buzón de entrega":
                            CapBE= val;
                            break;
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Crear Buzones
        BuzonCuarentena BC= new BuzonCuarentena();
        BuzonEntrada BE = new BuzonEntrada();
        BuzonEntrega BEN= new BuzonEntrega(CapBEN);


        // CREACION DE THREADS
            // Clientes Emisores
        for (int i= 0; i<numeroCE;i++){
            ClienteEmisor c= new ClienteEmisor();
            c.start();
        }
            //Filtros SPAM
        for (int i= 0; i<numSPAM;i++){
            FiltroSPAM c= new FiltroSPAM();
            c.start();
        }
            //Servidores de Entrega
        for (int i= 0; i<numSE;i++){
            ServidorEntrega c= new ServidorEntrega();
            c.start();
        }
            //Moderador
        Moderador m = new Moderador(BC, BEN);
        m.start();

    }
    
}
