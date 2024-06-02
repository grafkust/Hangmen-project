package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static char symbol = '*';
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Rules:\n" +
                "When you'll see the encrypted word you have to predict it for one letter." +
                "\nYou have the 6 attempts per one game session." +
                "\nAll words in this game are in English");


            while (true) {
                Scanner scanner = new Scanner(System.in);
                int attempt = 6;

                System.out.println("[S]tart new game or [B]reak the game");
                System.out.print("Print your choice: ");

                String response = scanner.nextLine();

                   if (response.equals("S")) {
                       char[] charWord = getRandomWord().toCharArray();
                       char[] passWord = encryption(charWord);
                       System.out.println(passWord);

                       while (true) {
                           System.out.print("Enter any letter: ");
                           char letter = scanner.next().charAt(0);

                           if (checkLetter(letter,charWord)) {

                              openLetter(letter, charWord, passWord);
                                  if (checkPass(passWord)==0) {
                                      System.out.println("Your are WINNER!");
                                      break;
                                  }
                           }

                           else {
                               attempt -= 1;
                               draw(attempt);
                                    if (attempt == 0) {
                                        System.out.print("Encrypted word is: ");
                                        System.out.println(charWord);
                                        break;
                                    }
                           }
                       }
                   }

                    if (response.equals("B")) {
                        System.out.println("SEE YOU NEXT WEEK. SAME BAT TIME. SAME BAT STATION.");
                        break;
                    }

                   else
                       System.out.println("Please press 'S' for Start or press 'B' for Exit.");
            }
    }



    public static void openLetter(char letter, char[] word, char[] passWord) {

        for (int i = 0; i < word.length; i ++) {
            if (word[i] == letter) {
                passWord[i] = letter;
            }
        }
        System.out.println(passWord);
    }

    public static int checkPass(char[] passWord){
        int cipher = passWord.length;
        for (int i = 0; i < passWord.length; i ++) {
           if (passWord[i] != symbol)
               cipher -=1;
            }
        return cipher;
    }

    public static void draw (int attempt){
        switch (attempt) {
            case 5:
                System.out.println("There are 5 remaining attempts");
                break;
            case 4:
                System.out.println("There are 4 remaining attempts");
                break;
            case 3:
                System.out.println("There are 3 remaining attempts");
                break;
            case 2:
                System.out.println("There are 2 remaining attempts");
                break;
            case 1:
                System.out.println("There are 1 remaining attempts");
                break;
            case 0:
                System.out.println("YOU LOSE");
                break;
        }
    }

    public static boolean checkLetter(char letter, char[] word) {

        for (char a : word) {
            if (a == letter)
                return true;
        }

       return false;
    }


    public static String getRandomWord() throws FileNotFoundException {
        Random random = new Random();

        String separator = File.separator;
        final String path = "src"+separator+"main"+separator+"resources"+separator+"dictionary.txt";

        File words = new File(path);
        Scanner scanner = new Scanner(words);

        List <String> dictionary = new ArrayList<>();
        while (scanner.hasNextLine()) {
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine());
            String firstArg = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
            dictionary.add(firstArg);
        }

        scanner.close();
        return dictionary.get(random.nextInt(dictionary.size()));
    }

    public static char[] encryption (char[] later){
        char[] passWord = later.clone();
        for (int i = 0; i < later.length; i ++)
            passWord[i] = symbol;
        return passWord;
    }
}



