import java.util.*;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;

public class Queuee extends Thread{
    private Integer idQueue;
    private Client[] clients;
    private boolean verifyServer = false;

    public Queuee(Integer idQueue){
        super();
        this.idQueue = idQueue;
        this.clients = new Client[0];
    }

    public void addClient(Client newClient){
        Client[] oldClients = this.clients;
        this.clients = new Client[oldClients.length + 1];

        System.arraycopy(oldClients, 0, this.clients, 0, oldClients.length);
        this.clients[this.clients.length - 1] = newClient;
    }

    public void removeClient(){
        Client[] oldClients = this.clients;
        this.clients = new Client[oldClients.length - 1];

        System.arraycopy(oldClients, 1, this.clients, 0, oldClients.length - 1);
    }

    public int taskTime(){
        int timeTask = 0;
        for(Client c : this.clients){
            timeTask += c.getServiceTime();
        }
        return timeTask;
    }

    public String toString(){
        String string ="";
        if(!isVerifyServer()){
            string = "CLOSED!";
        }else{
            for(Client c : clients){
                string += c.toString();
            }
        }
        return string;
    }

    public void run(){
        while(Main.getCurrentTime() < Main.getTimeSimulation()){
            try{
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            };

            if(clients.length > 0){
                Client client = clients[0];
                int processingTime = client.getServiceTime();

                if(processingTime == 1){
                    removeClient();
                }else{
                    processingTime = client.getServiceTime() - 1;
                    client.setServiceTime(processingTime);
                    clients[0] = client;
                }
            }
        }
    }

    public Integer getIdQueue() {
        return idQueue;
    }

    public void setIdQueue(Integer idQueue) {
        this.idQueue = idQueue;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public boolean isVerifyServer() {
        return verifyServer;
    }

    public void setVerifyServer(boolean verifyServer) {
        this.verifyServer = verifyServer;
    }
}
