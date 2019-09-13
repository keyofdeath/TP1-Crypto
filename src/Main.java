import java.util.Collection;
import java.util.HashMap;

/**
 * Usage:
 * arg[0] -> nom du fichier contenant les grams
 * arg[1] -> taille des grams
 */
public class Main {

    private static HashMap<String, Integer> dictionary;
    private static int gramSize;
    private static int totalGramValue;

    public static void main(String args[]) {
        dictionary = GramsReader.readGrams(args[0]);
        gramSize = Integer.parseInt(args[1]);
        totalGramValue = getTotalGramValue();

        System.out.println("Prob QIRSOPBBMF = " + getWordScore("QIRSOPBBMF"));

        PlayFair playFair = new PlayFair();
        try {
            String chiffre = playFair.chiffre_texte("ttxtlechattonestlatotxt");
            String dechiffre = playFair.dechiffre_texte(chiffre);
            System.out.println("Texte chiffrer: " + chiffre);
            System.out.println("Texte dechiffre: " + dechiffre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Fonction qui permet de calculer le score d'une chaine.
     * @param word La chaine
     * @return Le score de la chaine
     */
    private static double getWordScore(String word){
        double res = 0;
        for(int i = 0; i <= word.length() - gramSize; i ++) res += Math.log(getGramProbability(word.substring(i, i + (gramSize))));
        return res;
    }

    /**
     * Fonction qui calcul le taux d'apparition d'une chaine.
     * @param gram La chaine
     * @return Le taux d'apparition de la chaine
     */
    private static double getGramProbability(String gram){
        double res = 0;
        if(dictionary.get(gram) != null) res = dictionary.get(gram);
        else res = 0.01;
        return res / totalGramValue;
    }

    /**
     * Fonction qui calcule le nombre total d'occurence dans le fichier.
     * @return Le nombre total d'occurence dans le fichier.
     */
    private static int getTotalGramValue(){
        int res = 0;
        Collection<Integer> values = dictionary.values();
        for(int value: values) res += value;
        return res;
    }
}
