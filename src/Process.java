public class Process {
    String[] instructions;
    int ID;
    String state;
    int counter;
    int priority;
    int quantum;

    public int getID() {return ID;}
    public String getState() {return state;}
    public int getCounter() {return counter;}
    public String getInstructions() {
        StringBuilder sb = new StringBuilder();
        for (String s : instructions) {
            sb.append(s).append(",");
        }
        return sb.toString();
    }
    public int getPriority() {return priority;}
    public int getQuantum() {return quantum;}

    public void setCounter(int counter) {this.counter = counter;}
    public void setID(int ID) {this.ID = ID;}
    public void setState(String state) {this.state = state;}
    public void setInstructions(String[] instructions) {this.instructions = instructions;}
    public void setPriority(int priority) {this.priority = priority;}
    public void setQuantum(int quantum) {this.quantum = quantum;}

    public String getPrint() {
        return ("ID: " + ID + " | " + "State: " + state + " | " + "Quantum: " + quantum);
    }

}
