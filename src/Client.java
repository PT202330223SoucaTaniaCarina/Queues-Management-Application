public class Client {

    private Integer ID;
    private Integer arrivalTime;
    private Integer serviceTime;
    private Integer finishTime;

    public Client(Integer ID, Integer arrivalTime, Integer serviceTime){
        super();
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.finishTime = 0;
    }

    public String toString(){
        return ( "(" + getID() + ", " + getArrivalTime() + ", " + getServiceTime()  + ")" );
    }

    public Integer getID() {
        return ID;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }
}
