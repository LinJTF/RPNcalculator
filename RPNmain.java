import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import rpntoken.Token;
import rpntoken.TokenType;

import java.util.List;
import java.util.ArrayList;

public class RPNmain{
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<Token>();

        try {
            FileInputStream fstream = new FileInputStream("./test.txt");
            Scanner file = new Scanner(fstream);

            while (file.hasNextLine()) {
                String line = file.nextLine();

                if (line.equals("")) {
                    continue;
                }

                Token token = tokenType(line);
                tokens.add(token);
                System.out.println(token);
            }

            while(tokens.size() != 1) {
                Token sign = tokens.remove(0);
                
                if (sign.type == TokenType.NUM) {
                    Token sign2 = tokens.remove(0);
                    if (sign2.type == TokenType.NUM) {
                        Token sign3 = tokens.remove(0);
                        if (sign3.type != TokenType.NUM) {
                            tokens.add(0, calculator(sign, sign2, sign3));     
                        }
                        else {
                            System.out.println("Cannot calculate with 3 numbers in a row");
                        }
                    }
                    else {
                        System.out.println("Cannot calculate with just 1 number");
                    }       
                }
                else {
                    System.out.println("Cannot calculate with just a sign");
                }
            }
            
            System.out.println("Result: " + tokens.get(0).lexeme);
            file.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();

        } catch (Exception ex){
            System.out.println("Error");
            ex.printStackTrace();
        }
    }
    private static Token calculator(Token sign, Token sign2, Token sign3) {
        String result = "";	

        switch (sign3.lexeme) {
            case "+":
                result = Integer.toString(Integer.parseInt(sign.lexeme) + Integer.parseInt(sign2.lexeme));
                break;
            case "-":
                result = Integer.toString(Integer.parseInt(sign.lexeme) - Integer.parseInt(sign2.lexeme));
                break;
            case "*":
                result = Integer.toString(Integer.parseInt(sign.lexeme) * Integer.parseInt(sign2.lexeme));
                break;
            case "/":
                result = Integer.toString(Integer.parseInt(sign.lexeme) / Integer.parseInt(sign2.lexeme));
                break;
            default:
                System.out.println("Cannot calculate with invalid sign");
                break;
        }

        return new Token(TokenType.NUM, result);
    }

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Token tokenType(String line) throws Exception{
        if (line.equals("+")) {
            return new Token(TokenType.PLUS, line);
        }
        else if (line.equals("-")) {
            return new Token(TokenType.MINUS, line);
        }
        else if (line.equals("*")) {
            return new Token(TokenType.STAR, line);
        }
        else if (line.equals("/")) {
            return new Token(TokenType.SLASH, line);
        }
        else if (isNumber(line)) {
            return new Token(TokenType.NUM, line);
        }
        else{
            throw new Exception("Error: Unexpected character: " + line);
        }
    }
}