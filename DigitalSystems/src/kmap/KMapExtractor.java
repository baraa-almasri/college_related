package kmap;

import java.util.HashMap;

public interface KMapExtractor {
    // return SoM boolean function from the given kmap
    public abstract String getSoMBooleanFunction();
    // return SoM simplified function from the given kmap
    public abstract String getSimplifiedSoMBooleanFunction();
    // return PoM boolean function from the given kmap
    public abstract String getPoMBooleanFunction();
    // return PoM simplified function from the given kmap
    public abstract String getSimplifiedPoMBooleanFunction();
    // TODO: get truth table from the given kmap
    public abstract String [][]getTruthTable();
    // TODO: get neighbors indexes
    public abstract int []getNeighbors();
}
