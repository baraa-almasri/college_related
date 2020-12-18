package kmap;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwoVarsKMap implements KMap {
    // Sum of min terms kmap
    private HashMap<Integer, String> somKMap;
    // product of max terms
    private HashMap<Integer, String> pomKMap;
    // neighbors indexes
    private HashMap<Integer, Integer> rowNeighbors;
    private HashMap<Integer, Integer> colNeighbors;
    // how terms will look when there are neighbors
    private HashMap<Integer, String> alternateSoMKMapRows;
    private HashMap<Integer, String> alternateSoMKMapCols;

    private boolean []kmap;
    // TODO
    // make boolean function an instance attribute blyat

    public TwoVarsKMap(boolean []kmap) {
        this.kmap = kmap;

        this.somKMap = new HashMap<>();
        this.pomKMap = new HashMap<>();
        this.initializeSoMKMapHashMap();
        this.initializePoMKMapHashMap();

        this.rowNeighbors = new HashMap<>();
        this.colNeighbors = new HashMap<>();
        this.initializeRowsNeighborsHashMap();
        this.initializeColsNeighborsHashMap();

        this.alternateSoMKMapRows = new HashMap<>();
        this.alternateSoMKMapCols = new HashMap<>();
        this.initializeAlternateRowsHashMap();
        this.initializeAlternateColsHashMap();
    }

    @Override
    public String getBooleanFunction() {
        return this.createBooleanFunction();
    }

    @Override
    public String getSimplifiedBooleanFunction() {
        return this.createSimplifiedBooleanFunction();
    }

    @Override
    public String[][] getTruthTable() {
        return new String[0][];
    }

    @Override
    public int[] getNeighbors() {
        return new int[0];
    }

    private String createBooleanFunction() {
        StringBuilder booleanFunction = new StringBuilder();
        // loop over the kmap and append the corresponding term
        for (int cellIndex = 0; cellIndex < kmap.length; cellIndex++ ) {
            if (this.kmap[cellIndex]) {
                booleanFunction.append(
                        String.format(" %s +", this.somKMap.get(cellIndex))
                );
            }
        }

        return booleanFunction.toString();
    }

    private String createSimplifiedBooleanFunction() {
        StringBuilder booleanFunction = new StringBuilder();
        // loop over the kmap
        // if a cell is 1 and has a neighbor append the corresponding simplified term
        // if there's no neighbor then append a regular term
        for (int cellIndex = 0; cellIndex < kmap.length; cellIndex++ ) {
            // row neighbor
            if (this.kmap[cellIndex] && this.kmap[this.rowNeighbors.get(cellIndex)] ) {
                booleanFunction.append(
                        String.format(" %s +", this.alternateSoMKMapRows.get(cellIndex))
                );
            }
            // columns neighbors
            else if (this.kmap[cellIndex] && this.kmap[this.colNeighbors.get(cellIndex)]) {
                booleanFunction.append(
                        String.format(" %s +", this.alternateSoMKMapCols.get(cellIndex))
                );
            }
            else if (this.kmap[cellIndex]) {
                booleanFunction.append(
                        String.format(" %s +", this.somKMap.get(cellIndex))
                );
            }
        }

        // remove last plus sign to make it not looking like shit
        int lastCharIndex = booleanFunction.length()-1;
        if (booleanFunction.charAt(lastCharIndex) == '+') {
            booleanFunction.deleteCharAt(lastCharIndex);
        }

        return validateBooleanFunction(booleanFunction.toString());
    }

    // remove repeated terms and signs
    private static String validateBooleanFunction(String booleanFunction) {
        // aFreq, a`Freq, bFreq, b`Freq
        int []freqs = {0, 0, 0, 0};

        String []terms = booleanFunction.replace(" ", "").split("[+]");
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
                        booleanFunction += "+ a";
                        break;
                    case 1:
                        booleanFunction = booleanFunction.replace("a` ", "");
                        booleanFunction += "+ a`";
                        break;
                    case 2:
                        booleanFunction = booleanFunction.replace("b ", "");
                        booleanFunction += "+ b";
                        break;
                    case 3:
                        booleanFunction = booleanFunction.replace("b` ", "");
                        booleanFunction += "+ b`";
                        break;
                }
            }
        }

        return removeRepeatedPlusSigns(booleanFunction);
    }

    private static String removeRepeatedPlusSigns(String booleanFunction) {
        Pattern multipleSignsWithSpaces = Pattern.compile("[+]+[\\s]*[+]+");
        Matcher matchedFromGivenString = multipleSignsWithSpaces.matcher(booleanFunction);


        return matchedFromGivenString.replaceAll("");
    }

    // the more private part :)


    private void initializeSoMKMapHashMap() {
        this.somKMap.put(0, "a`b`");
        this.somKMap.put(1, "a`b");
        this.somKMap.put(2, "ab`");
        this.somKMap.put(3, "ab");
    }

    private void initializePoMKMapHashMap() {
        this.pomKMap.put(0, "(a+b)");
        this.pomKMap.put(1, "(a+b`)");
        this.pomKMap.put(2, "(a`+b)");
        this.pomKMap.put(3, "(a`+b`)");
    }

    private void initializeRowsNeighborsHashMap() {
        this.rowNeighbors.put(0, 1);
        this.rowNeighbors.put(2, 3);
        this.rowNeighbors.put(1, 0);
        this.rowNeighbors.put(3, 2);
    }

    private void initializeColsNeighborsHashMap() {
        this.colNeighbors.put(0, 2);
        this.colNeighbors.put(1, 3);
        this.colNeighbors.put(2, 0);
        this.colNeighbors.put(3, 1);
    }

    private void initializeAlternateRowsHashMap() {
        alternateSoMKMapRows.put(0, "a`");
        alternateSoMKMapRows.put(2, "a");
        alternateSoMKMapRows.put(1, "a`");
        alternateSoMKMapRows.put(3, "a");
    }

    private void initializeAlternateColsHashMap() {
        alternateSoMKMapCols.put(0, "b`");
        alternateSoMKMapCols.put(1, "b");
        alternateSoMKMapCols.put(2, "b`");
        alternateSoMKMapCols.put(3, "b");
    }

}
