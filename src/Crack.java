public class Crack {

    private PlayFair playFair;
    private Grams grams;

    public Crack(Grams grams, PlayFair playFair){
        this.playFair = playFair;
        this.grams = grams;
    }

    public char[][] crack(String text, int initialTemp, int finalTemp, double tempStep, int Nlimit) throws Exception {
        int temp = initialTemp;
        char[][] key = playFair.perturbe_cle(PlayFair.KEY_CONST);
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
