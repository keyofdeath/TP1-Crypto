public class Main {
    public static void main(String args[]) {

        PlayFair playFair = new PlayFair();

        try {
            String chiffre = playFair.chiffre_texte("lechattonestla");
            String dechiffre = playFair.dechiffre_texte(chiffre);
            System.out.println(chiffre);
            System.out.println(dechiffre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
