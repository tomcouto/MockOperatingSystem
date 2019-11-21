import org.w3c.dom.Node;
import java.io.IOException;
import java.util.*;

public class PriorityRun {

    static Node node;

    //Random instructions to test
    static String[] instructionSet = new String[] {"Test", "test", "TeSt"};

    //counter to track how many tasks have been created
    static int idCounter = 0;

    //counter for quantums gone through total
    static int qCounter = 0;

    //create a new random for creating random numbers
    static Random r = new Random();

    //priority queues (1-4, 4 is the highest)
    static Queue<Process> q1 = new LinkedList<>();
    static Queue<Process> q2 = new LinkedList<>();
    static Queue<Process> q3 = new LinkedList<>();
    static Queue<Process> q4 = new LinkedList<>();

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("How many minutes should the program run for?");
        //take input as minutes and convert to quantums (2 seconds per quantum)
        int time = (input.nextInt()*30);

        //create random process and add it to the proper queue
        //loop 10 times to start just to populate the queues
        for(int j =0; j < 10; j++) {
            generateProcess();
        }

        //add 1 process for each priority to start
        int pStart = 1;
        for(int a =0; a < 4; a++) {
            idCounter++;
            Process process = new Process();
            process.setID(r.nextInt(999999) + 1);
            process.setCounter(idCounter);
            process.setState("READY");
            process.setInstructions(instructionSet);
            process.setQuantum(r.nextInt(7));
            process.setPriority(r.nextInt(pStart));
            pStart++;

            switch(process.getPriority()) {
                case 1:
                    q1.add(process);
                    break;
                case 2:
                    q2.add(process);
                    break;
                case 3:
                    q3.add(process);
                    break;
                case 4:
                    q4.add(process);
                    break;
            }
        }

        //set the head of top priority to running
        if(!q1.isEmpty()) {
            q1.peek().setState("RUNNING");
        } else if(!q2.isEmpty()) {
            q2.peek().setState("RUNNING");
        } else if(!q3.isEmpty()) {
            q3.peek().setState("RUNNING");
        } else q4.peek().setState("RUNNING");

        //start running for n minutes
        for(int i = 0; i < time; i++){
            //check that the running process is not finished, if it is delete it
            if(!q4.isEmpty() && q4.peek().getQuantum() == 0) {
                q4.poll();
            } else if(!q4.isEmpty() && q4.peek().getQuantum() != 0){
                //reduce quantum by 1
                q4.peek().setQuantum(q4.peek().getQuantum() - 1);
                q4.peek().setState("RUNNING");
                if(!q3.isEmpty()) q3.peek().setState("READY");
                if(!q2.isEmpty()) q2.peek().setState("READY");
                if(!q1.isEmpty()) q1.peek().setState("READY");
            }

            //if the top priority has been running for 10 seconds on the same priority queue move all up by 1 priority
            if(q4.size() < 10 && qCounter != 0 && (qCounter%20 == 0 || q4.isEmpty())) {

                //print that the priorities are being moved
                System.out.println("Priorities being reassigned!");

                //loop to move all priorities up by 1
                int q3Size = q3.size();
                int q2Size = q2.size();
                int q1Size = q1.size();

                for(int y = 0; y < q3Size; y++) {
                    q4.add(q3.peek());
                    q3.poll();
                }
                for(int x = 0; x < q2Size; x++) {
                    q3.add(q2.peek());
                    q2.poll();
                }
                for(int w = 0; w < q1Size; w++) {
                    q2.add(q1.peek());
                    q1.poll();
                }
                //print that the priorities have been moved up
                System.out.println("All priorities have been moved up to ensure running!");
            }

            //after 2 quantums move the head of q4 to the end
            if(qCounter != 0 && qCounter%2 == 0) {
                move(q4);
            }
            if(qCounter !=0 && qCounter%10 == 0) {
                //add 5 new processes to the queues after 10 quantums
                for(int z = 0; z < 5; z++) {
                    generateProcess();
                }
            }

            //increment quantum counter
            qCounter++;

            //print out queues
            System.out.println("===============================");
            System.out.println("Priority 4: " + q4.toString());
            printQueue(q4);
            System.out.println("Priority 3: " + q3.toString());
            printQueue(q3);
            System.out.println("Priority 2: " + q2.toString());
            printQueue(q2);
            System.out.println("Priority 1: " + q1.toString());
            printQueue(q1);
            System.out.println("===============================");
            Thread.sleep(2000);
        }
        System.out.println("System ran for " + time/30 + " minutes. Exiting program!");
    }

    //method to handle moving the head to tail
    static public void move(Queue<Process> list) {
        list.peek().setState("READY");
        list.offer(list.poll());
        list.peek().setState("RUNNING");
    }

    static public void generateProcess() {
        idCounter++;
        Process process = new Process();
        process.setID(r.nextInt(999999) + 1);
        process.setCounter(idCounter);
        process.setState("READY");
        process.setInstructions(instructionSet);
        process.setQuantum(r.nextInt(7));
        process.setPriority(r.nextInt(5));

        //add to the proper queue based on priority
        switch (process.getPriority()) {
            case 1:
                q1.add(process);
                break;
            case 2:
                q2.add(process);
                break;
            case 3:
                q3.add(process);
                break;
            case 4:
                q4.add(process);
                break;
        }
    }

    static public void printQueue(Queue<Process> queue){
        int i = 0;
        for(Process p: queue){
            System.out.println("Process " + i + ": " + p.getPrint() + "\n--------------------");
            i++;
        }
    }
}
