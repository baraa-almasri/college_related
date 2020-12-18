package kmap;

public class Driver {
    public static void main(String[] argv) {
        boolean kmap[] = {true, true, true, false};
        TwoVarsKMap SoMKMap = new TwoVarsKMap(kmap);
        System.out.println(SoMKMap.getSimplifiedBooleanFunction());
    }

}










        /*
        // SoM KMap for each term
        HashMap<Integer, String> kmapSoM = new HashMap<>(4);
        kmapSoM.put(0, "a`b`");
        kmapSoM.put(1, "a`b");
        kmapSoM.put(2, "ab`");
        kmapSoM.put(3, "ab");

        // rows neighbors
        HashMap<Integer, Integer> rowNeighbors = new HashMap<>(2);
        rowNeighbors.put(0, 1);
        rowNeighbors.put(2, 3);
        rowNeighbors.put(1, 0);
        rowNeighbors.put(3, 2);
        // columns neighbors
        HashMap<Integer, Integer> colNeighbors = new HashMap<>(2);
        colNeighbors.put(0, 2);
        colNeighbors.put(1, 3);
        colNeighbors.put(2, 0);
        colNeighbors.put(3, 1);

        // alternate terms representation corresponding to the neighbors rule
        // assign the first index that might have a neighbor,
        // because it's visited iff the cell has a neighbor
        HashMap<Integer, String> alternateSoMKMapRows = new HashMap<>();
        alternateSoMKMapRows.put(0, "a`");
        alternateSoMKMapRows.put(2, "a");
        alternateSoMKMapRows.put(1, "a`");
        alternateSoMKMapRows.put(3, "a");
        // same but for columns
        HashMap<Integer, String> alternateSoMKMapCols = new HashMap<>();
        alternateSoMKMapCols.put(0, "b`");
        alternateSoMKMapCols.put(1, "b");
        alternateSoMKMapCols.put(2, "b`");
        alternateSoMKMapCols.put(3, "b");

        int []kmap = {1, 0, 0, 1};

        StringBuilder booleanFunction = new StringBuilder();
        // loop over the kmap
        // if a cell is 1 and has a neighbor append the corresponding simplified term
        // if there's no neighbor then append a regular term
        for (int cellIndex = 0; cellIndex < kmap.length; cellIndex++ ) {
            // row neighbor
            if (kmap[cellIndex] == 1 && kmap[rowNeighbors.get(cellIndex)] == 1) {
                booleanFunction.append(
                        String.format(" %s +", alternateSoMKMapRows.get(cellIndex))
                );
            }
            // columns neighbors
            else if (kmap[cellIndex] == 1 && kmap[colNeighbors.get(cellIndex)] == 1) {
                booleanFunction.append(
                        String.format(" %s +", alternateSoMKMapCols.get(cellIndex))
                );
            }
            else if (kmap[cellIndex] == 1) {
                booleanFunction.append(
                        String.format(" %s +", kmapSoM.get(cellIndex))
                );
            }
        }

        // remove last plus sign to make it not looking like shit
        int lastCharIndex = booleanFunction.length()-1;
        if (booleanFunction.charAt(lastCharIndex) == '+') {
            booleanFunction.deleteCharAt(lastCharIndex);
        }
        System.out.println(booleanFunction.toString());

        /*File f = new File("lol.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.printf("file %s can't be created\n", f.getName());
        }

        Scanner fileReader = null;
        try {
            fileReader = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.printf("file %s is not found\n", f.getName());
        }

        StringBuilder lines = new StringBuilder();
        while (fileReader != null && fileReader.hasNextLine()) {
            lines.append(fileReader.nextLine());
            lines.append('\n');
        }

        Pattern whiteSpaces = Pattern.compile("\\s+");
        Matcher whiteSpacesMatcher = whiteSpaces.matcher(lines);
        String linesWOSpaces = whiteSpacesMatcher.replaceAll("");
        String []words = linesWOSpaces.split("[|]");

        System.out.println(lines);
        for (String word : words) {
            System.out.println(word);
        }

        System.out.println(f.exists());
    }

}
*/