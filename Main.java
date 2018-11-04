
import java.util.HashMap;


public class Main {

    private static HashMap<String, String> animals       = new HashMap<>();
    // index of each char in cost matrix
    private static HashMap<Character, Integer> indexCost = new HashMap<>();
    // cost to allign each letter
    private static int[][] costMatrix = new int[24][24];

    public static void main(String[] args) {


        FileReader fr = new FileReader();
        fr.readIn(animals, indexCost, costMatrix);

        String s1 = animals.get("Snark");
        String s2 = animals.get("Sphinx");

        SeqAllign sa = new SeqAllign(indexCost, costMatrix);
        int cost = sa.allignSequence(s1, s2);

    }


}
