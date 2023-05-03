import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    private static int nbClients;
    private static int nbQueues;
    private static int timeSimulation;
    private static int ArrivalTimeMin;
    private static int ArrivalTimeMax;
    private static int ServiceTimeMin;
    private static int ServiceTimeMax;

    public static void readInput(String inputName) throws IOException {
        String read1;
        FileInputStream f1 = new FileInputStream("C:/Users/tania/Documents/TP/PT2023_30223_Souca_Tania_Carina_Assigment_2/src/In_Test.txt");
        InputStreamReader fchar = new InputStreamReader(f1);
        BufferedReader buff = new BufferedReader(fchar);
        read1 = buff.readLine();
        nbClients = Integer.parseInt(read1);

        read1 = buff.readLine();
        nbQueues = Integer.parseInt(read1);

        read1 = buff.readLine();
        timeSimulation = Integer.parseInt(read1);

        read1 = buff.readLine();
        String[] arr = read1.split(",");   //arrivalTime

        ArrivalTimeMin = Integer.parseInt(arr[0]);
        ArrivalTimeMax = Integer.parseInt(arr[1]);

        read1 = buff.readLine();
        String[] serv = read1.split(",");   //ServiceTime

        ServiceTimeMin = Integer.parseInt(serv[0]);
        ServiceTimeMax = Integer.parseInt(serv[1]);

    }

    public static int getNbClients() {
        return nbClients;
    }

    public static int getNbQueues() {
        return nbQueues;
    }

    public static int getTimeSimulation() {
        return timeSimulation;
    }

    public static int getArrivalTimeMin() {
        return ArrivalTimeMin;
    }

    public static int getArrivalTimeMax() {
        return ArrivalTimeMax;
    }

    public static int getServiceTimeMin() {
        return ServiceTimeMin;
    }

    public static int getServiceTimeMax() {
        return ServiceTimeMax;
    }
}
