
/**
 * Usage:
 * arg[0] -> nom du fichier contenant les grams
 * arg[1] -> taille des grams
 */

public class Main {

    private static final String text = "EDPGSONSCQHSEUCEOAURGRCQRSFOQINHSODLGPCEOLNRDQGXSNNCDUCEOEQNCBGADEKFKNCPOWOAKQDHHOLZDZASRUQDRACPIEFKTMSAQDOEECCBSAGWRQQNBXSPWGASSMOSSOBNHUIEDORAGQPRQRIOAOFKOSUMAHMLSRCQWASOASOLQGAOHDASQFGRSPSCOEORSEOGSHXFECAWSQGOOAQFIZHSAOMLARCQNLOISPHKAHDISQLOCGDEGODOQDOAYCAWSOAOTSASPBNRIOAOOKSHAWOSSAOEORSEOGOGUMAIGQPGWHLOFNHSEOAOSOQZFKCGAWOAGOTSNCQKDKOSKNDHRPNSPRLZAOESNCASQIASWACBNCARASEUOKQY";

    public static void main(String args[]) throws Exception {

        PlayFair playFair = new PlayFair();
        String chiffre = playFair.chiffre_texte(/*"ttxtlechattonestlatotxt"*/text.toLowerCase(), PlayFair.KEY_CONST);
        String dechiffre = playFair.dechiffre_texte(chiffre, PlayFair.KEY_CONST);
        System.out.println("Texte chiffrer: " + chiffre);
        System.out.println("Texte dechiffre: " + dechiffre);

        System.out.println("Key original");
        PlayFair.display_key(PlayFair.KEY_CONST);

        Grams grams = new Grams(Integer.parseInt(args[1]), GramsReader.readGrams(args[0]));
        Crack crack = new Crack(grams, new PlayFair());
        char[][] crackedKey = crack.crack(chiffre, 5, text.length() / 8, 0.1, 50000);
        System.out.println("Cracked key");
        PlayFair.display_key(crackedKey);

        dechiffre = playFair.dechiffre_texte(chiffre, crackedKey);
        System.out.println("Texte dechiffre: " + dechiffre);
    }






}
