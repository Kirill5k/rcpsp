package app.algorithm;

/**
 * Created by Kirill on 22/06/2016.
 */
public enum AlgorithmType {
    NORMAL_GA("Genetic Algorithm"),
    NORMAL_SCGA("Species Conserving Genetic Algorithm"),
    PARALLEL_GA("Parallel Genetic Algorithm"),
    NORMAL_CS("Parallel Cuckoo Search");

    private final String name;


    AlgorithmType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
