import java.util.Collection;
import java.util.HashMap;

public class Main {

    private static HashMap<String, Integer> dictionary;
    private static int gramSize;
    private static int totalGramValue;

    public static void main(String args[]) {
        dictionary = GramsReader.readGrams(args[0]);
        gramSize = Integer.parseInt(args[1]);
        totalGramValue = getTotalGramValue();

        System.out.println("Prob QIRSOPBBMF = " + getWordScore("QIRSOPBBMF"));
    }

    public Main() {
    }

    private static double getWordScore(String word){
        double res = 0;
        for(int i = 0; i <= word.length() - gramSize; i ++)
        {
            System.out.println(word.substring(i, i + (gramSize)) + " : " + Math.log(getGramProbability(word.substring(i, i + (gramSize)))));
            res += Math.log(getGramProbability(word.substring(i, i + (gramSize))));
        }
        return res;
    }

    private static double getGramProbability(String gram){
        double res = 0;
        if(dictionary.get(gram) != null) res = dictionary.get(gram);
        else res = 0.01;
        return res / totalGramValue;
    }

    private static int getTotalGramValue(){
        int res = 0;
        Collection<Integer> values = dictionary.values();
        for(int value: values) res += value;
        return res;
    }
}
