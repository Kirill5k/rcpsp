package app.algorithm;

/**
 * Created by Kirill on 18/04/2016.
 */

class ParallelSpeciesConservingGeneticAlgorithm {//extends ParallelGeneticAlgorithm {
//    private final int speciesDistance;
//    public ParallelSpeciesConservingGeneticAlgorithm(BenchmarkInstance bi, int populationSize, int stopCriterion, double mutationRate, int speciesDistance) {
//        super(bi, populationSize, stopCriterion, mutationRate);
//        this.speciesDistance = speciesDistance;
//    }
//
//    @Override
//    public List<EventList> findSolution() {
//        for (int i = 0; i < stopCriterion; i++) {
//            // DETERMINE SPECIES SEEDS
//            List<EventList> speciesSeeds = SpeciesConservation.findSpeciesSeeds(population, speciesDistance);
//
//            // DO CROSSOVER AND MUTATION
//            performGAOperationsInParallel();
//
//            // FIND NEW SPECIES SEEDS
//            List<EventList> newGeneration = SpeciesConservation.conserveSpecies(population, speciesSeeds, speciesDistance);
//
//            if (newGeneration.size() > populationSize) {
////                System.out.println("Too many species " + newGeneration.size());
//                Collections.sort(newGeneration, Comparator.reverseOrder());
//                while (newGeneration.size() > 100)
//                    newGeneration.remove(0);
//            } else {
//                // REMOVE WORST INDIVIDUALS
//                Collections.sort(population);
//                while (newGeneration.size() < populationSize && !population.isEmpty()) {
//                    if (!newGeneration.contains(population.get(0)))
//                        newGeneration.add(population.get(0));
//                    else
//                        population.remove(0);
//                }
//            }
//
//            population.clear();
//            population.addAll(newGeneration);
//        }
//
//        executorService.shutdown();
//        return population;
//    }
}
