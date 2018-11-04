import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {


    public void readIn(HashMap<String, String> animals, HashMap<Character, Integer> indexCost, int[][] costMatrix) {

        String fileCost   = "data/BLOSUM62.txt";
        String fileAnimal = "data/toy.txt";

        readInCostMatrix(indexCost, costMatrix, fileCost);
        readInAnimals(animals, fileAnimal);

    }

    private void readInCostMatrix(HashMap<Character, Integer> indexCost, int[][] costMatrix, String file) {

        try {
            Scanner sc = new Scanner(new File(file));

            String line = sc.nextLine();

            while (line.startsWith("#")) line = sc.nextLine();

            char[] BLOSUM62 = line.replace(" ", "").toCharArray();

            for (int letter = 0; letter < BLOSUM62.length; letter++){
                indexCost.put(BLOSUM62[letter], letter);
            }


            int row = 0;

            while (sc.hasNextLine()) {

                String[] cost = sc.nextLine().split("\\s+");

                for (int column = 0; column < costMatrix.length; column++)
                    costMatrix[row][column] = Integer.parseInt(cost[column + 1]);

                row++;
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("file blosum not found");

        }

    }

    private void readInAnimals (HashMap<String, String> animals, String file) {

        try {
            Scanner sc = new Scanner(new File(file));

            String animal = "";
            String sequence = "";

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.startsWith(">")) {

                    if (!animal.isEmpty()) {
                        animals.put(animal, sequence);
                        sequence = "";
                    }

                    line = line.replace(">", "");
                    animal = line.trim();
                } else sequence += line;

                if (!sc.hasNextLine()) animals.put(animal, sequence);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("file animal not found");
        }
    }

}

