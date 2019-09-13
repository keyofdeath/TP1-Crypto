public class PlayFair {

    private static final char[][] KEY = {{'t', 'q', 'o', 'h', 'w'},
            {'e', 'i', 'l', 'c', 's',},
            {'k', 'u', 'r', 'a', 'g'},
            {'d', 'f', 'v', 'm', 'n'},
            {'y', 'z', 'b', 'p', 'x'}};

    public PlayFair() {
    }

    /**
     * Obtien la position d'un caractere dans le tableau KEY
     * @param c caractere a trouver
     * @return la position y, x du character. null si il n'est pas dans le tableau
     */
    private int[] get_pos(char c) {
        for (int y = 0; y < KEY.length; y++)
            for (int x = 0; x < KEY[y].length; x++)
                if (KEY[y][x] == c) {
                    return new int[]{y, x};
                }
        return null;
    }

    /**
     * Chiffre une paire de lettre
     * @param pos1 list position dans le tableau KEY (y, x)
     * @param pos2 list position dans le tableau KEY (y, x)
     * @return les deux lettres chiffrees
     */
    private String chiffre_paire(int[] pos1, int[] pos2) {

        String str = "";
        // Si c'est la meme ligne
        if (pos1[0] == pos2[0]) {
            int x1, x2;
            x1 = (pos1[1] + 1) % KEY[pos1[0]].length;
            x2 = (pos2[1] + 1) % KEY[pos2[0]].length;
            return str + KEY[pos1[0]][x1] + KEY[pos2[0]][x2];
        // Si c'est la meme ligne
        } else if (pos1[1] == pos2[1]) {
            int y1, y2;
            y1 = (pos1[0] + 1) % KEY.length;
            y2 = (pos2[0] + 1) % KEY.length;
            return str + KEY[y1][pos1[1]] + KEY[y2][pos2[1]];
        // Si il sont en digonale
        } else {
            return str + KEY[pos2[0]][pos1[1]] + KEY[pos1[0]][pos2[1]];
        }
    }

    /**
     * dechiffre une paire de lettre
     * @param pos1 list position dans le tableau KEY (y, x)
     * @param pos2 list position dans le tableau KEY (y, x)
     * @return les deux lettres dechiffrees
     */
    private String dechiffre_paire(int[] pos1, int[] pos2) {

        String str = "";
        // Si c'est la meme ligne
        if (pos1[0] == pos2[0]) {
            int x1, x2;
            x1 = (pos1[1] - 1) % KEY[pos1[0]].length;
            x2 = (pos2[1] - 1) % KEY[pos2[0]].length;
            return str + KEY[pos1[0]][x1] + KEY[pos2[0]][x2];
        // Si c'est la meme ligne
        } else if (pos1[1] == pos2[1]) {
            int y1, y2;
            y1 = (pos1[0] - 1) % KEY.length;
            y2 = (pos2[0] - 1) % KEY.length;
            return str + KEY[y1][pos1[1]] + KEY[y2][pos2[1]];
        // Si il sont en digonale
        } else {
            return str + KEY[pos2[0]][pos1[1]] + KEY[pos1[0]][pos2[1]];
        }
    }

    /**
     * Chiffre un texte
     * @param text
     * @return le texte chiffre
     * @throws Exception Si il y a des lettres qui ne sont pas dans le tableau KEY
     */
    public String chiffre_texte(String text) throws Exception {

        StringBuilder res = new StringBuilder();
        text = text.replace('j', 'i');
        if (text.length() % 2 != 0)
            text += 'x';
        for (int i = 0; i < text.length(); i += 2) {
            int[] pos1 = get_pos(text.charAt(i));
            int[] pos2 = get_pos(text.charAt(i + 1));
            if (pos1 == null || pos2 == null) {
                throw new Exception("l1 or l2 not in the key tab");
            // Si les deux char son egaux on rajoute x
            } else if (text.charAt(i) == text.charAt(i + 1)) {
                res.append(text.charAt(i)).append('x').append(text.charAt(i + 1));
            } else {
                res.append(chiffre_paire(pos1, pos2));
            }
        }
        return res.toString();
    }

    /**
     * dechiffre un texte
     * @param text
     * @return le texte dechiffree
     * @throws Exception Si il y a des lettres qui ne sont pas dans le tableau KEY
     */
    public String dechiffre_texte(String text) throws Exception {

        StringBuilder res = new StringBuilder();
        if (text.length() % 2 != 0)
            text += 'x';
        for (int i = 0; i < text.length(); i += 2) {
            int[] pos1 = get_pos(text.charAt(i));
            int[] pos2 = get_pos(text.charAt(i + 1));
            if (pos1 == null || pos2 == null) {
                throw new Exception("l1 or l2 not in the key tab");
            // Si les deux char suivant sont un x on suppose qu'il a ete rajouter donc on le retire
            } else if (text.charAt(i + 1) == 'x') {
                res.append(text.charAt(i)).append(text.charAt(i + 2));
                i += 1;
            } else {
                res.append(dechiffre_paire(pos1, pos2));
            }
        }
        return res.toString();
    }
}
