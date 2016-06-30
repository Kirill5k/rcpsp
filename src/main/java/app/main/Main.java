package app.main;

import app.asset.BenchmarkInstance;
import app.utility.Benchmarks;

/**
 * Created by Kirill on 16/02/2016.
 */
public class Main {
    private static final BenchmarkInstance BI_30 = Benchmarks.instances30.get("J3039_3.RCP");
    private static final BenchmarkInstance BI_60 = Benchmarks.instances60.get("J6039_4.RCP");
    private static final BenchmarkInstance BI_120 = Benchmarks.instances120.get("J12019_4.RCP");

    public static void main(String[] args) {
        //Tests.fullTestGA(Benchmarks.instances30.entrySet(), 100, 1000, 0.3, AlgorithmType.NORMAL_SCGA);

//        Benchmarks.instances60.entrySet().forEach((e) -> System.out.println(e.getKey() + ": " + e.getValue().getResCapacities()));
    }

}
