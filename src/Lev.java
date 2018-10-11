import java.io.*;
import java.util.*;
public class Lev {

    static HashSet<String> wordsAll;

    public static void main(String[]arg) throws FileNotFoundException {
        dictionary();
        Scanner read = new Scanner(System.in);
        String word = read.next();
        String word2 = read.next();
        if(word.length() != word2.length()){
            System.out.println("Error, please enter valid input");
        }else{
            int d = distance(word,word2);
            if(d == -1){
                System.out.println("Path does not exist");
            }else{
                System.out.println(d);
            }
        }

    }

    public static void dictionary() throws FileNotFoundException {
        File f = new File("dictionary words.txt");
        Scanner scan = new Scanner(f);
        while(scan.hasNext()){
            String string = scan.next();
            wordsAll.add(string);

        }
    }

    public static HashSet<String> getNeighbor(String word){
        HashSet<String> neighbors = new HashSet<>();
        for (int i = 0; i < word.length(); i++){
            for(int j = 0; j < 26; j++){
                String sub = word.substring(0,i)+(j+'a')+word.substring(i+1, word.length());
                if(isWord(sub) && !sub.equals(word)){
                    neighbors.add(sub);
                }
            }
        }
        return neighbors;
    }

    public static boolean isWord(String word){
        return wordsAll.contains(word);
    }

    public static int distance(String first, String comp){
        int d = 0;
        if (first.equals(comp)){
            return d;
        }
        else{
            HashSet<String> alpha = new HashSet<String>();
            HashSet<String> beta = new HashSet<>();
            HashSet<String> check = new HashSet<>();
            alpha.add(first);
            boolean find = false;
            while(!find){
                for (String word : alpha){
                    if(!check.contains(word)){
                        check.add(word);
                        HashSet<String> neighbor = getNeighbor(word);
                        beta.addAll(neighbor);
                    }

                }
                d++;
                if(beta.contains(comp)){
                    return d;
                }
                if (alpha.isEmpty()){
                    d = -1;
                    find = true;
                }
            }
        }
        return d;
    }
}
