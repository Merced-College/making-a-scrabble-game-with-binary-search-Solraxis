  // Group Members: Kevin Leuthold, Adam Lopes, Adam Sanchez, Andres Garcia
// 10/8/2024
// CPSC-39-10111

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ScrabbleGame {

    public static final String VOWELS = "aeiou";
    public static final String ALPHABET = "qwertyuiopasdfghjklzxcvbnm";
    public static ArrayList<Word> words = new ArrayList<Word>();
    public static void main(String args[]){
        
        try{
            File wordList = new File("CollinsScrabbleWords_2019.txt");
            Scanner readFile = new Scanner(wordList);
            
            while(readFile.hasNextLine()){
                words.add(new Word(readFile.nextLine()));
            }
            
            words.remove(0); //Hardcoded :/
            words.remove(0); //Remove the first two lines of header from the text file
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
        startGame();
    }
    
    // Prints 4 random characters of the given word
    /*
    public void printRandomCharacters(String word) {
        Random random = new Random();
        String charsRandom = "";

        System.out.print("Four characters from the word: ");
        for (int i = 0; i < 3; i++) {
            charsRandom += word.charAt(random.nextInt(word.length())); // Assigns random character from word
            charsRandom.toLowerCase();
            System.out.print(charsRandom + ", ");
        }
        charsRandom += word.charAt(random.nextInt(word.length()));
        System.out.print("& " + charsRandom);

    }
    */
    
    // Game method
    public static void startGame(){
        Random random = new Random();
        char[] randomChars = new char[4];
        
        randomChars[0] = VOWELS.charAt(random.nextInt(VOWELS.length()));
        for (int i = 1; i < 4; i++) {
            randomChars[i] = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        // prints your random characters
        String randomCharsString = new String(randomChars);
        System.out.println("Your characters are: " + randomCharsString);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word made from these letters: ");
        String userWord = scanner.nextLine().toUpperCase();
            
        Word wordToCheck = new Word(userWord);
        if (binarySearch(words, wordToCheck) >= 0) {
            System.out.println();
            System.out.println("Valid word: " + userWord);
            System.out.println(userWord + " is worth: " + calculatePoints(userWord) + " points");
        } else {
            System.out.println("Invalid word: " + userWord);
        }

        scanner.close();
    }

    // Binary Search Algorithm
    private static int binarySearch(ArrayList<Word> words, Word target) {
        int left = 0;
        int right = words.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = words.get(mid).compareTo(target);
            if (comparison == 0) {
                return mid; // Found the word
            } else if (comparison < 0) {
                left = mid + 1; // Search right
            } else {
                right = mid - 1; // Search left
            }
        }
        return -1; // Not found
    }

    // calculates points using scrabble point values per letter
    private static int calculatePoints(String wordString) {
        int sum = 0;
        for(int i = 0; i < wordString.length(); i++){
            if(wordString.charAt(i) == 'A' || wordString.charAt(i) == 'E' || wordString.charAt(i) == 'I' || wordString.charAt(i) == 'O' || wordString.charAt(i) == 'U' || wordString.charAt(i) == 'L' || wordString.charAt(i) == 'N' || wordString.charAt(i) == 'S' || wordString.charAt(i) == 'T' || wordString.charAt(i) == 'R') {
                sum += 1;
            } else if(wordString.charAt(i) == 'D'|| wordString.charAt(i) == 'G'){
                sum += 2;
            } else if(wordString.charAt(i) == 'B' || wordString.charAt(i) == 'C' || wordString.charAt(i) == 'M'|| wordString.charAt(i) == 'P') {
                sum += 3;
            } else if(wordString.charAt(i) == 'F' || wordString.charAt(i) == 'H' || wordString.charAt(i) == 'V' || wordString.charAt(i) == 'W' || wordString.charAt(i) == 'Y') {
                sum += 4;
            } else if(wordString.charAt(i) == 'K') {
                sum += 5;
            } else if(wordString.charAt(i) == 'J' || wordString.charAt(i) == 'X') {
                sum += 8;
            } else if(wordString.charAt(i) == 'Q' || wordString.charAt(i) == 'Z') {
                sum += 10;
            }
        }
        return sum;
    }
}