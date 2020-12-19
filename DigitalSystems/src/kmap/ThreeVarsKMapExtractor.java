package kmap;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreeVarsKMapExtractor implements KMapExtractor {

    // Sum of min terms kmap
    private final HashMap<Integer, String> somKMap;
    // product of max terms
    private final HashMap<Integer, String> pomKMap;
    // how terms will look when there are neighbors
    private final HashMap<Integer, String> alternateSoMKMapRows;
    private final HashMap<Integer, String> alternateSoMKMapCols;
    private final HashMap<Integer, String> alternatePoMKMapRows;
    private final HashMap<Integer, String> alternatePoMKMapCols;
    // neighbors indexes
    private int []rowNeighbors;
    private int []colNeighbors;

    private boolean []kmap;
    // TODO
    // make boolean function an instance attribute blyat

    public ThreeVarsKMapExtractor(boolean []kmap) {
        this.kmap = kmap;

        this.somKMap = new HashMap<>();
        this.pomKMap = new HashMap<>();
        this.initializeSoMKMap();
        this.initializePoMKMap();

        this.rowNeighbors = new int[4]; // four possible neighbors
        this.colNeighbors = new int[4]; // four possible neighbors
        this.initializeRowsNeighbors();
        this.initializeColsNeighbors();

        this.alternateSoMKMapRows = new HashMap<>();
        this.alternateSoMKMapCols = new HashMap<>();
        this.initializeAlternateSoMRows();
        this.initializeAlternateSoMCols();

        this.alternatePoMKMapRows = new HashMap<>();
        this.alternatePoMKMapCols = new HashMap<>();
        this.initializeAlternatePoMRows();
        this.initializeAlternatePoMCols();
    }

    // generate and get boolean functions
    @Override
    public String getSoMBooleanFunction() {
        return this.createBooleanFunction(1);
    }

    @Override
    public String getSimplifiedSoMBooleanFunction() {
        return this.createSimplifiedBooleanFunction(1);
    }

    @Override
    public String getPoMBooleanFunction() {
        return this.createBooleanFunction(2);
    }

    @Override
    public String getSimplifiedPoMBooleanFunction() {
        return this.createSimplifiedBooleanFunction(2);
    }

    // TODO
    // truth table, and neighbors count
    @Override
    public String[][] getTruthTable() {
        return new String[0][];
    }

    @Override
    public int[] getNeighbors() {
        return new int[0];
    }

    // create boolean function equation from the given kmap, where type=1 SoM, and type=2 PoM
    private String createBooleanFunction(int standardType) {
        StringBuilder booleanFunction = new StringBuilder();
        // loop over the kmap and append the corresponding term
        for (int cellIndex = 0; cellIndex < kmap.length; cellIndex++ ) {
            if (this.kmap[cellIndex]) {
                booleanFunction.append(
                    String.format(" %s %c",
                        ((standardType == 1)? this.somKMap: this.pomKMap).get(cellIndex),
                        (standardType == 1)? '+': '·')
                );
            }
        }
        removeLastUnwantedSign(booleanFunction);

        return booleanFunction.toString();
    }

    // create simplified boolean function equation from the given kmap, where type=1 SoM, and type=2 PoM
    private String createSimplifiedBooleanFunction(int standardType) {
        StringBuilder booleanFunction = new StringBuilder();
        // loop over the kmap
        // if a cell is 1 and has a neighbor append the corresponding simplified term
        // if there's no neighbor then append a regular term
        for (int cellIndex = 0; cellIndex < kmap.length; cellIndex++ ) {
            // row neighbor
            if (this.kmap[cellIndex] && this.kmap[this.rowNeighbors[cellIndex]] ) {
                booleanFunction.append(
                    String.format(" %s %c",
                        ((standardType == 1)? this.alternateSoMKMapRows: this.alternatePoMKMapRows).get(cellIndex),
                        (standardType == 1)? '+': '·')
                );
            }
            // columns neighbors
            else if (this.kmap[cellIndex] && this.kmap[this.colNeighbors[cellIndex]]) {
                booleanFunction.append(
                    String.format(" %s %c",
                        ((standardType == 1)? this.alternateSoMKMapCols: this.alternatePoMKMapCols).get(cellIndex),
                        (standardType == 1)? '+': '·')
                );
            }
            else if (this.kmap[cellIndex]) {
                booleanFunction.append(
                    String.format(" %s %c",
                        ((standardType == 1)? this.somKMap: this.pomKMap).get(cellIndex),
                        (standardType == 1)? '+': '·')
                );
            }
        }
        removeLastUnwantedSign(booleanFunction);

        return validateSoMBooleanFunction(booleanFunction.toString(), standardType);
    }

    // remove repeated terms and signs, again type=1 SoM, type=2 PoM
    private static String validateSoMBooleanFunction(String booleanFunction, int standardType) {
        // aFreq, a`Freq, bFreq, b`Freq
        int []freqs = {0, 0, 0, 0};

        String []terms = booleanFunction.replace(" ", "").split((standardType == 1)? "[+]": "[·]");
        for ( String term : terms ) {
            switch (term) {
                case "a":
                    freqs[0]++;
                    break;
                case "a`":
                    freqs[1]++;
                    break;
                case "b":
                    freqs[2]++;
                    break;
                case "b`":
                    freqs[3]++;
                    break;
            }
        }

        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] == 2) {
                switch (i) {
                    case 0:
                        booleanFunction = booleanFunction.replace("a ", "");
                        booleanFunction += String.format("%c a", (standardType == 1)? '+': '·');
                        break;
                    case 1:
                        booleanFunction = booleanFunction.replace("a` ", "");
                        booleanFunction += String.format("%c a`", (standardType == 1)? '+': '·');
                        break;
                    case 2:
                        booleanFunction = booleanFunction.replace("b ", "");
                        booleanFunction += String.format("%c b", (standardType == 1)? '+': '·');
                        break;
                    case 3:
                        booleanFunction = booleanFunction.replace("b` ", "");
                        booleanFunction += String.format("%c b`", (standardType == 1)? '+': '·');
                        break;
                }
            }
        }

        return removeRepeatedSigns(booleanFunction, standardType);
    }

    private static String removeRepeatedSigns(String booleanFunction, int standardType) {
        Pattern multipleSignsWithSpaces = Pattern.compile((standardType == 1)? "[+]+[\\s]*[+]+": "[·]+[\\s]*[·]+");
        Matcher matchedFromGivenString = multipleSignsWithSpaces.matcher(booleanFunction);


        return matchedFromGivenString.replaceAll("");
    }

    private static void removeLastUnwantedSign(StringBuilder booleanFunction) {
        int lastCharIndex = booleanFunction.length()-1;
        if (booleanFunction.charAt(lastCharIndex) == '+' || booleanFunction.charAt(lastCharIndex) == '·') {
            booleanFunction.deleteCharAt(lastCharIndex);
        }
    }

    // the more private part :)

    private void initializeSoMKMap() {
        this.somKMap.put(0, "a`b`");
        this.somKMap.put(1, "a`b");
        this.somKMap.put(2, "ab`");
        this.somKMap.put(3, "ab");
    }

    private void initializePoMKMap() {
        this.pomKMap.put(0, "(a+b)");
        this.pomKMap.put(1, "(a+b`)");
        this.pomKMap.put(2, "(a`+b)");
        this.pomKMap.put(3, "(a`+b`)");
    }

    private void initializeRowsNeighbors() {
        this.rowNeighbors[0] = 1;
        this.rowNeighbors[2] = 3;
        this.rowNeighbors[1] = 0;
        this.rowNeighbors[3] = 2;
    }

    private void initializeColsNeighbors() {
        this.colNeighbors[0] = 2;
        this.colNeighbors[1] = 3;
        this.colNeighbors[2] = 0;
        this.colNeighbors[3] = 1;
    }

    // rows neighbors with SoM
    private void initializeAlternateSoMRows() {
        alternateSoMKMapRows.put(0, "a`");
        alternateSoMKMapRows.put(2, "a");
        alternateSoMKMapRows.put(1, "a`");
        alternateSoMKMapRows.put(3, "a");
    }

    // columns neighbors with SoM
    private void initializeAlternateSoMCols() {
        alternateSoMKMapCols.put(0, "b`");
        alternateSoMKMapCols.put(1, "b");
        alternateSoMKMapCols.put(2, "b`");
        alternateSoMKMapCols.put(3, "b");
    }

    // rows neighbors with PoM
    private void initializeAlternatePoMRows() {
        alternatePoMKMapRows.put(0, "a");
        alternatePoMKMapRows.put(2, "a`");
        alternatePoMKMapRows.put(1, "a");
        alternatePoMKMapRows.put(3, "a`");
    }

    // columns neighbors with PoM
    private void initializeAlternatePoMCols() {
        alternatePoMKMapCols.put(0, "b");
        alternatePoMKMapCols.put(1, "b`");
        alternatePoMKMapCols.put(2, "b");
        alternatePoMKMapCols.put(3, "b`");
    }
}
