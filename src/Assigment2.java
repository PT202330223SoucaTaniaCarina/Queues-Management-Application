import javax.swing.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;





    public class Assigment2 {

        private static int ArrivalTimeMin;
        private static int ServiceTimeMin;
        private static int ArrivalTimeMax;
        private static int ServiceTimeMax;
        private static int timeSimulation;
        private static int currentTime = 0;
        public static int time1;
        public static int timeS;
        public static int nbClients;
        public static int nbQueues;
        static int totalWaitingTime = 0;
        static int totalServiceTime = 0;
        static int maxQueueLength = 0;
        static int peakHour = 0;

        private static ArrayList<Queuee> queues = new ArrayList<Queuee>();
        private static ArrayList<Integer> ArrivalTimeClient = new ArrayList<Integer>();
        private static CopyOnWriteArrayList<Client> waitingClientsList = new CopyOnWriteArrayList<>();
        static String simulationString = "";


        private JPanel MainPannel;
    private JTextArea TextFieldNClients;
    private JTextArea TextFieldMinArrivalTime;
    private JTextArea TextFieldMaxArrivalTime;
    private JTextArea TextFieldNbQueues;
    private JTextArea TextFieldMinServiceTime;
    private JTextArea TextFieldMaxServiceTime;
    private JTextArea TextFieldTimeSimulation;
    private JButton OKButton;
        private JFormattedTextField formattedTextField1;
        private JTextArea TextFieldOutput;
        private JScrollBar scrollBar1;
        private JTextPane textPane1;
        private JTextField textField1;
        private JTextArea textArea1;


        public Assigment2() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               nbClients = Integer.valueOf(TextFieldNClients.getText());
               nbQueues = Integer.valueOf(TextFieldNbQueues.getText());
               timeSimulation = Integer.valueOf(TextFieldTimeSimulation.getText());
               ArrivalTimeMin = Integer.valueOf(TextFieldMinArrivalTime.getText());
               ArrivalTimeMax = Integer.valueOf(TextFieldMaxArrivalTime.getText());
               ServiceTimeMin = Integer.valueOf(TextFieldMinServiceTime.getText());
               ServiceTimeMax = Integer.valueOf(TextFieldMaxServiceTime.getText());

               System.out.println("nbClients = " + nbClients);
               System.out.println("nbQueues = " + nbQueues);
               System.out.println("timeSimulation = " + timeSimulation);
               System.out.println("arrivalTimeMin = " + ArrivalTimeMin);
               System.out.println("arrivalTimeMax = " + ArrivalTimeMax);
               System.out.println("serviceTimeMin = " + ServiceTimeMin);
               System.out.println("serviceTimeMax = " + ServiceTimeMax);

                generateClients();
                calculateArrivalTime();//
                generateQueue();
                Simulation();


                //TextFieldOutput.setText(simulationString);
                textPane1.setText(simulationString);
                //OutputWriter.writeOutput("Out_Test.txt", simulationString);

            }
        });

        }

    static void generateClients(){
        int i;
        for (i = 1; i <= nbClients; i++) {
            int arrivalTime = (int) (Math.random() * (ArrivalTimeMax - ArrivalTimeMin) + 1) + ArrivalTimeMin;
            if (ArrivalTimeClient.isEmpty()) {
                ArrivalTimeClient.add(arrivalTime);
            } else {
                while (ArrivalTimeClient.contains(arrivalTime)) {
                    arrivalTime = (int) (Math.random() * (ArrivalTimeMax - ArrivalTimeMin) + 1) + ArrivalTimeMin;
                }
                ArrivalTimeClient.add(arrivalTime);
            }
            int serviceTime = (int) (Math.random() * (ServiceTimeMax - ServiceTimeMin) + 1) + ServiceTimeMin;

            Client client = new Client(i, arrivalTime, serviceTime);
            waitingClientsList.add(client);
        }

        Collections.sort(waitingClientsList, Comparator.comparing(Client::getArrivalTime));
    }

    public static Client calculateArrivalTime(){
        if(waitingClientsList.isEmpty()) {
            return null;
        }

        Client arrivalMinCl = waitingClientsList.get(0);
        for(Client c : waitingClientsList){
            if(c.getArrivalTime() < arrivalMinCl.getArrivalTime())
                arrivalMinCl = c;
        }
        return arrivalMinCl;
    }

    static void generateQueue(){
        int i;
        for(i = 0; i < nbQueues; i++){
            Queuee queue = new Queuee(i+1);
            queues.add(queue);
            System.out.println(queues.toString());//
        }
    }

    public static Queuee queueClosed(){
        for(Queuee q : queues){
            if(!q.isVerifyServer())
                return q;
        }
        return null;
    }

    private static Queuee findMinServer(){
        Queuee minSer = queues.get(0);
        for(Queuee q : queues){
            if(minSer.taskTime() < q.taskTime()){
                minSer = q;
            }
        }
        return minSer;
    }

    public static void Simulation(){
        while(currentTime < timeSimulation){
            currentTime = currentTime + 1;
            if(!waitingClientsList.isEmpty()){
                Client client = calculateArrivalTime();

                if(currentTime == client.getArrivalTime()){
                    Queuee qClosed = queueClosed();

                    if(qClosed != null){
                        qClosed.setVerifyServer(true);
                        qClosed.addClient(client);
                        waitingClientsList.remove(client);
                        qClosed.start();
                    }else{
                        Queuee qTimeMin = findMinServer();
                        qTimeMin.addClient(client);
                        waitingClientsList.remove(client);
                    }
                }
            }
            simulationString += "\nTime: " + currentTime + "\n" + "Waiting clients: " + waitingClientsList.toString();
            for(Queuee q : queues){
                simulationString += "\nQueue" + q.getIdQueue() + ": " + q.toString();
            }
            System.out.println(simulationString);//

            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assigment2");
        frame.setContentPane(new Assigment2().MainPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    }
