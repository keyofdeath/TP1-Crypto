import java.util.Collection;
import java.util.HashMap;

public class Grams {

    private int gramSize;
    private int totalGramValue;
    private HashMap<String, Integer> dictionary;

    public Grams(int gram_size, HashMap<String, Integer> dico){
        dictionary = dico;
        gramSize = gram_size;
        totalGramValue = getTotalGramValue();
        System.out.println("Prob QIRSOPBBMF = " + getWordScore("QIRSOPBBMF"));
    }

    /**
     * Fonction qui permet de calculer le score d'une chaine.
     *
     * @param word La chaine
     * @return Le score de la chaine
     */
    public double getWordScore(String word) {
        double res = 0;
        for (int i = 0; i <= word.length() - gramSize; i++)
            res += Math.log(getGramProbability(word.substring(i, i + (gramSize))));
        return res;
    }

    /**
     * Fonction qui calcul le taux d'apparition d'une chaine.
     *
     * @param gram La chaine
     * @return Le taux d'apparition de la chaine
     */
    private double getGramProbability(String gram) {
        double res = 0;
        if (dictionary.get(gram) != null) res = dictionary.get(gram);
        else res = 0.01;
        return res / totalGramValue;
    }

    /**
     * Fonction qui calcule le nombre total d'occurence dans le fichier.
     *
     * @return Le nombre total d'occurence dans le fichier.
     */
    private int getTotalGramValue() {
        int res = 0;
        Collection<Integer> values = dictionary.values();
        for (int value : values) res += value;
        return res;
    }
}
