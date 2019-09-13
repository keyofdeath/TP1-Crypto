import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GramsReader {

    /**
     * Fonction qui créer un dictionnaire ayant pour clés des grams et pour valeurs le nombre d'occurence de ces grams.
     * @param fileName Le nom du fichier contenant les grams et leur nombre d'occurence
     * @return Notre dictionnaire
     */
    public static HashMap<String, Integer> readGrams(String fileName){
        HashMap<String, Integer> dictionary = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("ngrams/" + fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    String[] words = line.split(" ");
                    dictionary.put(words[0], Integer.parseInt(words[1]));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dictionary;
    }
}
