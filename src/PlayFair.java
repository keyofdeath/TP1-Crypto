import java.util.concurrent.ThreadLocalRandom;

public class PlayFair {

    public static final char[][] KEY_CONST = {{'t', 'q', 'o', 'h', 'w'},
            {'e', 'i', 'l', 'c', 's',},
            {'k', 'u', 'r', 'a', 'g'},
            {'d', 'f', 'v', 'm', 'n'},
            {'y', 'z', 'b', 'p', 'x'}};

    public PlayFair() {
    }

    public static void display_key(char[][] key) {
        for (char[] line : key) {
            for (char l : line) {
                System.out.print(l);
            }
            System.out.print('\n');
        }
    }

    /**
     * Obtien la position d'un character dans le tableau key
     *
     * @param c character a trouver
     * @return la position y, x du character. null si il n'est pas dans le tableau
     */
    private int[] get_pos(char c, char[][] key) {
        for (int y = 0; y < key.length; y++)
            for (int x = 0; x < key[y].length; x++)
                if (key[y][x] == c) {
                    return new int[]{y, x};
                }
        return null;
    }

    /**
     * Fait le modulo mathematique si chiffre < 0 il revient a b - 1
     *
     * @param a
     * @param b
     * @return a mod b
     */
    private int math_mod(int a, int b) {
        return ((a % b) + b) % b;
    }

    private int[] get_rand_pair(int min, int max) {
        int random1 = ThreadLocalRandom.current().nextInt(min, max);
        int random2 = random1;
        while (random1 == random2)
            random2 = ThreadLocalRandom.current().nextInt(min, max);
        return new int[]{random1, random2};
    }

    private char[][] permutation(int[] pos1, int[] pos2, char[][] t) {
        char temp = t[pos1[0]][pos1[1]];
        t[pos1[0]][pos1[1]] = t[pos2[0]][pos2[1]];
        t[pos2[0]][pos2[1]] = temp;
        return t;
    }

    /**
     * Modifie le clef en entrer
     *
     * @param key
     * @return
     */
    public char[][] perturbe_cle(char[][] key) {

        int[] pos1, pos2;
        char[][] res = new char[key.length][key[0].length];
        for (int i = 0; i < key.length; i++)
            System.arraycopy(key[i], 0, res[i], 0, key[i].length);
        pos1 = get_rand_pair(0, key.length);
        pos2 = pos1;
        while (pos1[0] == pos2[0] || pos1[1] == pos2[1])
            pos2 = get_rand_pair(0, key.length);

        // Permutation de deux lettre
        permutation(pos1, pos2, res);

        // Permutation de deux ligne
        char[] l_temp = res[pos1[0]];
        res[pos1[0]] = res[pos2[0]];
        res[pos2[0]] = l_temp;

        // Permutation de deux colomne
        for (int y = 0; y < key.length; y++)
            permutation(new int[]{y, pos1[1]}, new int[]{y, pos2[1]}, res);
        return res;

    }

    /**
     * Chiffrer une paire de lettre
     *
     * @param pos1 list position dans le tableau key (y, x)
     * @param pos2 list position dans le tableau key (y, x)
     * @return les deux lettre chiffrer
     */
    private String chiffre_paire(int[] pos1, int[] pos2, char[][] key) {

        String str = "";
        // Si c'est la maime ligne
        if (pos1[0] == pos2[0]) {
            int x1, x2;
            x1 = math_mod(pos1[1] + 1, key[pos1[0]].length);
            x2 = math_mod(pos2[1] + 1, key[pos2[0]].length);
            return str + key[pos1[0]][x1] + key[pos2[0]][x2];
            // Si c'est la maime ligne
        } else if (pos1[1] == pos2[1]) {
            int y1, y2;
            y1 = math_mod(pos1[0] + 1, key.length);
            y2 = math_mod(pos2[0] + 1, key.length);
            return str + key[y1][pos1[1]] + key[y2][pos2[1]];
            // Si il sont en digonale
        } else {
            return str + key[pos2[0]][pos1[1]] + key[pos1[0]][pos2[1]];
        }
    }

    /**
     * dechiffre une paire de lettre
     *
     * @param pos1 list position dans le tableau key (y, x)
     * @param pos2 list position dans le tableau key (y, x)
     * @return les deux lettre dechiffre
     */
    private String dechiffre_paire(int[] pos1, int[] pos2, char[][] key) {

        String str = "";
        // Si c'est la maime ligne
        if (pos1[0] == pos2[0]) {
            int x1, x2;
            x1 = math_mod(pos1[1] - 1, key[pos1[0]].length);
            x2 = math_mod(pos2[1] - 1, key[pos2[0]].length);
            return str + key[pos1[0]][x1] + key[pos2[0]][x2];
            // Si c'est la maime ligne
        } else if (pos1[1] == pos2[1]) {
            int y1, y2;
            y1 = math_mod(pos1[0] - 1, key.length);
            y2 = math_mod(pos2[0] - 1, key.length);
            return str + key[y1][pos1[1]] + key[y2][pos2[1]];
            // Si il sont en digonale
        } else {
            return str + key[pos2[0]][pos1[1]] + key[pos1[0]][pos2[1]];
        }
    }

    /**
     * Chiffre un texte
     *
     * @param text
     * @return le texte chiffrer
     * @throws Exception Si il y a des lettre qui ne sont pas dans la tableau key
     */
    public String chiffre_texte(String text, char[][] key) throws Exception {

        StringBuilder res = new StringBuilder();
        StringBuilder reformat_text = new StringBuilder();
        text = text.replace('j', 'i');
        // on rajoute un x si deux lettre son egaux
        for (int i = 0; i < text.length() - 1; i += 2) {
            if (text.charAt(i) == text.charAt(i + 1)) {
                reformat_text.append(text.charAt(i)).append('x').append(text.charAt(i + 1));
            } else {
                reformat_text.append(text.charAt(i)).append(text.charAt(i + 1));
            }
        }
        if (reformat_text.length() % 2 != 0)
            reformat_text.append('x');
        int[] pos1, pos2;
        char c1, c2;
        // on a un text avec un nombre de lettre paire
        for (int i = 0; i < reformat_text.length(); i += 2) {
            c1 = reformat_text.charAt(i);
            c2 = reformat_text.charAt(i + 1);
            pos1 = get_pos(c1, key);
            pos2 = get_pos(c2, key);
            if (pos1 == null || pos2 == null) {
                throw new Exception("l1 or l2 not in the key tab");
            }
            res.append(chiffre_paire(pos1, pos2, key));
        }
        return res.toString();
    }

    /**
     * dechiffre un texte
     *
     * @param text
     * @return le texte dechiffre
     * @throws Exception Si il y a des lettre qui ne sont pas dans la tableau key
     */
    public String dechiffre_texte(String text, char[][] key) throws Exception {

        StringBuilder dechifre_text = new StringBuilder();
        StringBuilder res = new StringBuilder();
        int[] pos1, pos2;
        char c1, c2;
        for (int i = 0; i < text.length(); i += 2) {
            c1 = text.charAt(i);
            c2 = text.charAt(i + 1);
            pos1 = get_pos(c1, key);
            pos2 = get_pos(c2, key);
            if (pos1 == null || pos2 == null) {
                throw new Exception("l1 or l2 not in the key tab");
            }
            // Si les deux char suivent est un x on supose qu'il a ete rajouter donc on le retire
            dechifre_text.append(dechiffre_paire(pos1, pos2, key));
        }
        for (int i = 0; i < dechifre_text.length(); i += 2) {
            if (i + 2 < dechifre_text.length() && dechifre_text.charAt(i) == dechifre_text.charAt(i + 2)) {
                res.append(dechifre_text.charAt(i)).append(dechifre_text.charAt(i + 2));
                i++;
            } else {
                res.append(dechifre_text.charAt(i)).append(dechifre_text.charAt(i + 1));
            }
        }

        if (res.charAt(res.length() - 1) == 'x')
            res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
