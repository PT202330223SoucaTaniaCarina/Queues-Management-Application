import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
    public static void writeOutput(String outputName, String simulationString) {
        try {
            FileWriter write1 = new FileWriter("C:/Users/tania/Documents/TP/PT2023_30223_Souca_Tania_Carina_Assigment_2/src/Out_Test.txt");
            write1.write(simulationString);
            write1.close();
        } catch(IOException e) {
            System.out.println("Error writing output file");
            e.printStackTrace();
        }
    }
}
