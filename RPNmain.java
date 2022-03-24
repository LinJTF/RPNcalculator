import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class RPNmain {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        try {
            FileInputStream fstream = new FileInputStream("./test.txt");
            Scanner file = new Scanner(fstream);


            while(file.hasNextLine()) {
                String line = file.nextLine();

                if(line.equals("")) {
                    continue;
                }

                if(!isOperator(line)){
                    stack.push(Integer.parseInt(line));
                }

                else{
                    int num1 = stack.pop();
                    int num2 = stack.pop();

                    stack.push(calculator(num1, num2, line));
                } 
                
            }
            file.close();
            System.out.println(stack.pop());
        }   
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } 
    }

    public static boolean isOperator(String c) {
        return (c.equals("+") || c.equals("-")  || c.equals("*")  || c.equals("/") );
    }

    private static int calculator(int num1, int num2, String operator) {
        int result = 0;

        if(operator.equals("+")){
            result = num2 + num1;
        }

        else if(operator.equals("-")){
            result = num2 - num1;
        }

        else if(operator.equals("*")){
            result = num2 * num1;
        }

        else if(operator.equals("/")){
            result = num2 / num1;
        }

        return result;
    }

}