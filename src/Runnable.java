import java.util.Random;
import java.util.Stack;

public class Runnable {

    static int idCounter = 0;
    static Stack<Process> currentTask = new Stack();
    static String[] instructionSet = new String[] {"Test", "test", "TeSt"};

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i < 120; i++) {
            Random r = new Random();
            System.out.println("5 Second Pause");

            if(currentTask.empty()) {
                //increase id
                idCounter++;
                Process process = new Process();
                process.setID(r.nextInt(999999) + 1);
                process.setCounter(idCounter);
                process.setState("Running");
                process.setInstructions(instructionSet);
                currentTask.push(process);

                //print new current task
                System.out.printf("================================= \n" +
                        "New Current Task: \n" + "ID: " + currentTask.peek().getID() + "\n" +
                        "State: " + currentTask.peek().getState() + "\n" +
                        "Counter: " + currentTask.peek().getCounter() + "\n" +
                        "Instructions: " + currentTask.peek().getInstructions() +"\n" +
                        "=================================");
                System.out.println();
            }
            else {
                //print out current process info
                System.out.printf("================================= \n"+
                        "Current Task: \n" + "ID: " + currentTask.peek().getID() + "\n" +
                        "State: " + currentTask.peek().getState() + "\n" +
                        "Counter: " + currentTask.peek().getCounter() + "\n" +
                        "Instructions: " + currentTask.peek().getInstructions() + "\n" +
                        "=================================");
                System.out.println();
                //delete current process
                currentTask.pop();
                System.out.println();
                System.out.println("Current task has been removed!");
                System.out.println();

                //create new task
                idCounter++;
                Process process = new Process();
                process.setID(r.nextInt(999999)+1);
                process.setCounter(idCounter);
                process.setState("Running");
                process.setInstructions(instructionSet);
                currentTask.push(process);

                //print new current task
                System.out.printf("================================= \n" +
                        "New Current Task: \n" + "ID: " + currentTask.peek().getID() + "\n" +
                        "State: " + currentTask.peek().getState() + "\n" +
                        "Counter: " + currentTask.peek().getCounter() + "\n" +
                        "Instructions: " + currentTask.peek().getInstructions() + "\n" +
                        "=================================");
                System.out.println();
            }
            Thread.sleep(5000);
        }
    }
}
