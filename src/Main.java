import java.util.HashMap;

public class Main {
    public static void main(String args[]) {
        HashMap<String, Integer> dictionary = GramsReader.readGrams("C:/Users/ClemiNeko/IdeaProjects/TP1-Crypto/ngrams/1-grams-english-ALPHA.save");
        System.out.println(dictionary);
    }

    public Main() {
    }
}
