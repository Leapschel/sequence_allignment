import java.util.Arrays;
import java.util.HashMap;

public class SeqAllign {

    // letters of cost matrix and their indices
    private HashMap<Character, Integer> indexChar;
    // costs
    private int[][] costMatrix;
    // allignment matrix
    private int[][] m;


    public SeqAllign(HashMap<Character, Integer> indexCost, int[][] costMatrix) {

        this.indexChar = indexCost;
        this.costMatrix = costMatrix;

    }

    public int allignSequence(String s1, String s2) {

        m = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < s1.length() + 1; i++)
            m[i][0] = i * costMatrix[indexChar.get('*')][0];

        for (int j = 0; j < s2.length() + 1; j++)
            m[0][j] = j * costMatrix[0][indexChar.get('*')];

        return  OptRec(s1, s2);
    }

    /**
     * Method that returns cost for the entire sequence allignment
     * @param s1 Sequence 1
     * @param s2 Sequence 2
     * @return cost for alligning s1 and s2
     */
    private int OptRec(String s1, String s2) {

        // end of sequence
        if (s1.length() == 0 && s2.length() == 0) {
            return 0;
        }

        // return the gap penalty * length of remaining string
        if (s1.length() == 0) {
            return m[0][s2.length()];
        }

        // return the gap penalty * length of remaining string
        if (s2.length() == 0) {
            return m[s1.length()][0];
        }

        int indexA = indexChar.get(s1.charAt(s1.length() - 1));
        int indexB = indexChar.get(s2.charAt(s2.length() - 1));
        int delta  = indexChar.get('*');

        // generating the allignment matrix
        m[s1.length()][s2.length()] = Math.max(
                costMatrix[indexA][indexB] + OptRec(s1.substring(0, s1.length() - 1), s2.substring(0, s2.length() - 1)),
                Math.max(
                        costMatrix[indexA][delta] + OptRec(s1.substring(0, s1.length() - 1), s2),
                        costMatrix[delta][indexB] + OptRec(s1, s2.substring(0, s2.length() - 1))));

        return m[s1.length()][s2.length()];
    }

    // helper method for debugging
    private void printM(int[][] m) {
        for (int[] r : m) {
            System.out.println(Arrays.toString(r));
            System.out.println();
        }

    }

}
