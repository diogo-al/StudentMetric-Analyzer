package DAA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DAAMTReader {
    private static final int NUMBER_OF_MT = 3;
    private static final String STUDENTS_PAHT = "Samples/Nomes";
    private static Map<String,String> studentMap = new TreeMap<>();
    private static Map<String, Map<String, String>> allGrades = new HashMap<>();

    private static void loadStudentes() throws FileNotFoundException {
        Scanner scNames = new Scanner(new File(STUDENTS_PAHT));
        while(scNames.hasNextLine()) {
            String[] line = scNames.nextLine().split(" ",2);
            studentMap.put(line[0],line[1]);
        }

    }

    private static void loadAllGrades() throws FileNotFoundException {
        for (int i = 1; i <= NUMBER_OF_MT; i++) {
            String mtName = "MT" + i;
            Map<String, String> mtGrades = new HashMap<>();

            try (Scanner sc = new Scanner(new File("Samples/MTs/MT" + i))) {
                while (sc.hasNextLine()) {
                    String[] line = sc.nextLine().split(" ");
                    mtGrades.put(line[0], line[2]);
                }
            }
            allGrades.put(mtName, mtGrades);
        }
    }

    private static void MTReader() throws FileNotFoundException {
        loadStudentes();
        loadAllGrades();
        System.out.printf("%-6s","Num.");
        System.out.printf(" | %-45s", "Nomes");
        for(int i = 1; i <= NUMBER_OF_MT; i ++) System.out.printf(" | %-4s", "MT" + i);
        System.out.printf(" | %-5s","Media");
        System.out.println();
        for(String numero : studentMap.keySet()){
            System.out.printf("%-6s",numero);
            System.out.printf(" | %-45s",studentMap.get(numero));
            double media = 0.0;
            for(int i = 1; i <= NUMBER_OF_MT; i ++){
                String auxGrade = allGrades.get("MT" + i).getOrDefault(numero, "na");
                if(!auxGrade.equals("na")) media += Double.parseDouble(auxGrade.replace(",","."));
                System.out.printf(" | %-4s", auxGrade);
            }
            System.out.printf(" | %-5.2f",media/NUMBER_OF_MT);
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        MTReader();
    }
}
