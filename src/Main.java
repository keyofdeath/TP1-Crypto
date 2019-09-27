import javax.swing.*;

/**
 * Usage: Main <nom-fichier> <grams-length> <text> <est-chiffrer>
 *
 * <nom-fichier> -> nom du fichier contenant les grams
 * <grams-length> -> taille des grams
 * <text> -> text
 * <est-chiffrer> -> True le text donner est chiffrer. False le test n'est pas chiffrer
 */
public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length < 4) {
            System.out.println("Usage: Main <nom-fichier> <grams-length> <text> <est-chiffrer>\n" +
                    "\n" +
                    "<nom-fichier> -> nom du fichier contenant les grams\n" +
                    "<grams-length> -> taille des grams\n" +
                    "<text> -> text\n" +
                    "<est-chiffrer> -> True le text donner est chiffrer. False le test n'est pas chiffrer");
            return;
        }
        String text_to_crack = args[2].toLowerCase();
        PlayFair playFair = new PlayFair();
        if (!args[3].toLowerCase().equals("true")) {
            text_to_crack = playFair.chiffre_texte(text_to_crack, PlayFair.KEY_CONST);
            String dechiffre = playFair.dechiffre_texte(text_to_crack, PlayFair.KEY_CONST);
            System.out.println("Texte chiffrer: " + text_to_crack);
            System.out.println("Texte dechiffre: " + dechiffre);
        }
        System.out.println("Key original");
        PlayFair.display_key(PlayFair.KEY_CONST);

        Grams grams = new Grams(Integer.parseInt(args[1]), GramsReader.readGrams(args[0]));
        Crack crack = new Crack(grams, new PlayFair());
        char[][] crackedKey = crack.crack(text_to_crack, 5,
                text_to_crack.length() / 8, 0.2, 500000);
        System.out.println("Clef trouver");
        PlayFair.display_key(crackedKey);
        System.out.println("Text de deciffrer avec la clef trouver");
        System.out.println(playFair.dechiffre_texte(text_to_crack, crackedKey));
    }
}
