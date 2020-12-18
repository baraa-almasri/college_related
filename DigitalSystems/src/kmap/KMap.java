package kmap;

import java.util.HashMap;

public interface KMap {
    // return boolean function from the given kmap
    public abstract String getBooleanFunction();
    // return simplified function from the given kmap
    public abstract String getSimplifiedBooleanFunction();
    // TODO: get truth table from the given kmap
    public abstract String [][]getTruthTable();
    // TODO: get neighbors indexes
    public abstract int []getNeighbors();
}
