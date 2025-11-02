import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int numeroCE;
        int numMPC;
        int numSPAM;
        int numSE;
        int CapBE;
        int CapBEN;
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
    }
    public void leer(){
        int numeroCE;
        int numMPC;
        int numSPAM;
        int numSE;
        int CapBE;
        int CapBEN;
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
    }
}
