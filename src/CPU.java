import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hakkı Can Akut
 */
public class CPU {
    public static void main(String[] args) {

        //Text Path
        Scanner scan = new Scanner(System.in);
        System.out.println("write file path (example: D:\\Midterm\\)");
        String pathname= scan.nextLine();
        File folder = new File(pathname);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
        System.out.println("Select command file (example: program.txt)");
        String filename= scan.nextLine();


        // Text Reading
        // created ArrayLists for adding commands there.
        ArrayList<String> operations= new ArrayList<>();
        ArrayList<Integer> operationValues= new ArrayList<>();
        // with patterns, could add strings to operations and integers to operationValues.
        Pattern p = Pattern.compile("[A-Z]+");
        Pattern pV = Pattern.compile("[0-9]+");
        // file reader
        try(Scanner scanner= new Scanner(new BufferedReader(new FileReader(pathname+filename)))) {
            // while file has line continues to read lines.
            while(scanner.hasNextLine()){
                String s=scanner.nextLine();
                Matcher m = p.matcher(s);
                Matcher mV = pV.matcher(s);
                boolean checks= false;
                // if there is a letter, saves this word to operation list
                while (m.find()){
                    operations.add(m.group());
                    // START, DISP and HALT doesn't have operation values, so checks = true
                    if(operations.get(operations.size()-1).equals("START")|| operations.get(operations.size()-1).equals("DISP")|| operations.get(operations.size()-1).equals("HALT")){
                        checks = true;
                    }
                }
                // if there is an integer, saves this int to operationValues list.
                while (mV.find()){
                    operationValues.add(Integer.valueOf(mV.group()));
                    // if checks true we add 0 to opValues because we want an order. For example,
                    // operationValues(2n) operations(n) operationValues(2n+1)
                    // opV(0)= 0            op(0)= START   opV(1)= 0(doesn't mean anything, we add it for maintain order)
                    // opV(2)= 1            op(1)= LOAD    opV(3)= 20
                    if (checks){
                        operationValues.add(0);
                        checks=false;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found exception!");
        }

        // Central Process Unit (CPU)
        // Accumulator(AC)
        int AC=0;
        // Program Counter(PC)
        int PC=-1;
        // Flag(F)
        int F=0;
        // Memory(M[]), initially every element is 0.
        int[] M= new int[256];

        // Finds which line runs the CPU, if there is any command before START, this command doesn't gonna run.
        // And if there isn't any START command doesn't gonna run the CPU.
        for(int i=0;i<operations.size();i++){
            if(operations.get(i).equals("START")){
                PC=i;
                break;
            }
        }

        // if PC doesn't -1, CPU starts running.
        while (PC!= -1){
            PC++;
            // if command lines ends loop gonna break
            if (PC==operations.size()){
                break;
            }
            // Loads immediate value X to AC
            if(operations.get(PC).equals("LOAD")) {
                AC=operationValues.get((PC*2)+1);
            }
            // Load memory value stored at M[X] to AC
            else if(operations.get(PC).equals("LOADM")) {
                AC= M[operationValues.get((PC*2)+1)];
            }
            else if(operations.get(PC).equals("STORE")) {
                M[operationValues.get((PC*2)+1)]= AC;
            }
            // Compare AC and M[X]
            else if(operations.get(PC).equals("CMPM")) {
                // If the value in AC is greater than value in M[X] then set F flag to 1
                if(AC>M[operationValues.get((PC*2)+1)]){
                    F=1;
                }
                // If the value in AC is less than value in M[X] then set F flag to -1
                else if(AC<M[operationValues.get((PC*2)+1)]){
                    F=-1;
                }
                // If the value in AC is equal to value in M[X] then set F flag to 0
                else {
                    F=0;
                }
            }
            // Update the PC with X if the F flag value is positive
            else if(operations.get(PC).equals("CJMP")) {
                if(F>0){
                    PC= operationValues.get((PC*2)+1)-1;
                }
            }
            // Update the PC value with X
            else if(operations.get(PC).equals("JMP")) {
                PC= operationValues.get((PC*2)+1)-1;
            }
            // Add immediate value of X to AC
            else if(operations.get(PC).equals("ADD")) {
                AC += operationValues.get((PC*2)+1);
            }
            // Add Memory value of M[X] to AC
            else if(operations.get(PC).equals("ADDM")) {
                AC += M[operationValues.get((PC*2)+1)];
            }
            // Subtract Memory value of M[X] from AC
            else if(operations.get(PC).equals("SUBM")) {
                AC -= M[operationValues.get((PC*2)+1)];
            }
            // Subtract immediate value of X from AC C
            else if(operations.get(PC).equals("SUB")) {
                AC -= operationValues.get((PC*2)+1);
            }
            // Multiply AC with immediate value of X
            else if(operations.get(PC).equals("MUL")) {
                AC -= operationValues.get((PC*2)+1);
            }
            // Multiply AC with M[X]
            else if(operations.get(PC).equals("MULM")) {
                AC *= M[operationValues.get((PC*2)+1)];
            }
            // Display the value in AC on screen
            else if(operations.get(PC).equals("DISP")) {
                System.out.println(AC);
            }
            // Stop Execution
            else if(operations.get(PC).equals("HALT")) {
                break;
            }
        }
    }
}