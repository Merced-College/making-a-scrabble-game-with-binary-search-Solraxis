// Group 2 
// Names: Andres Garcia, Adam Lopes, Kevin Leuthold, Adam Sanchez 

public class Word implements Comparable <Word> {
    String word;
    
    public Word(){
        word = "asdlkf";
   }

    public Word(String word) {
        this.word = word;
    }

    public String getWord(){
        return word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public void printWord(){
        System.out.println("Word: " + word);
    }
    
    public int compareTo(Word target){
        return word.compareTo(target.getWord());   
    }
}