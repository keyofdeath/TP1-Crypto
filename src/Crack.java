/**
 * Crack l'algorithm de PlayFair
 */
public class Crack {

    private PlayFair playFair;
    private Grams grams;

    /**
     * @param grams grams pour la detection des mots
     * @param playFair Algo de playfare pour dechiffrer
     */
    public Crack(Grams grams, PlayFair playFair){
        this.playFair = playFair;
        this.grams = grams;
    }

    /**
     *
     * @param text text dechiffrer
     * @param initialTemp temperature initial pour le calcule recuit simulé
     * @param finalTemp tmperature finale
     * @param tempStep décrément pour la températur
     * @param Nlimit nombre de clé à tester pour chaque température
     * @return la clef trouver
     * @throws Exception
     */
    public char[][] crack(String text, int initialTemp, int finalTemp, double tempStep, int Nlimit) throws Exception {
        int temp = initialTemp;
        char[][] key = PlayFair.KEY_CONST;
        for(int i = 0; i < 3; i++) key = playFair.perturbe_cle(key);
        while(temp > finalTemp){
            // on teste N perturbations de la clé
            for(int k = 1; k < Nlimit; k++){
                char[][] tempKey = playFair.perturbe_cle(key);
                double delta = grams.getWordScore(playFair.dechiffre_texte(text, key)) - grams.getWordScore(playFair.dechiffre_texte(text, tempKey));
                if (delta < 0 || Math.random() < Math.exp(- delta / temp)) key = tempKey;
            }
            // on a testé N perturbations de la clé, on diminue la température
            temp -= tempStep;
        }
        return key;
    }

}
