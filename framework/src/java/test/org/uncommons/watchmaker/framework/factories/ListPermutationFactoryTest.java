// ============================================================================
//   Copyright 2006 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.watchmaker.framework.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.testng.annotations.Test;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.CandidateFactory;

/**
 * Unit test for the list permutation candidate factory.  Checks that it
 * correctly generates populations of permutations.
 * @author Daniel Dyer
 */
public class ListPermutationFactoryTest
{
    private final Random rng = new MersenneTwisterRNG();
    private final int candidateLength = 10;
    private final int populationSize = 5;
    private final List<Integer> elements = new ArrayList<Integer>(candidateLength);
    {
        for (int i = 1; i <= candidateLength; i++)
        {
            elements.add(i);
        }
    }


    /**
     * Generate a completely random population.  Checks to make
     * sure that the correct number of candidate solutions is
     * generated and that each is valid.
     */
    @Test
    public void testUnseededPopulation()
    {
        CandidateFactory<List<Integer>> factory = new ListPermutationFactory<Integer>(elements);
        List<List<Integer>> population = factory.generateInitialPopulation(populationSize, rng);
        assert population.size() == populationSize : "Wrong size population generated: " + population.size();

        validatePopulation(population);
    }


    /**
     * Generate a random population with some seed candidates.  Checks to make
     * sure that the correct number of candidate solutions is generated and that
     * each is valid.
     */
    @Test
    public void testSeededPopulation()
    {
        CandidateFactory<List<Integer>> factory = new ListPermutationFactory<Integer>(elements);

        List<Integer> seed1 = new ArrayList<Integer>(elements);
        List<Integer> seed2 = new ArrayList<Integer>(elements);
        Collections.reverse(elements);

        List<List<Integer>> population = factory.generateInitialPopulation(populationSize,
                                                                           Arrays.asList(seed1, seed2),
                                                                           rng);
        // Check that the seed candidates appear in the generated population.
        assert population.contains(seed1) : "Population does not contain seed candidate 1.";
        assert population.contains(seed2) : "Population does not contain seed candidate 2.";

        validatePopulation(population);
    }


    /**
     * Make sure each candidate is valid (contains each element exactly once).
     * @param population The population to be validated.
     */
    private void validatePopulation(List<List<Integer>> population)
    {
        assert population.size() == populationSize : "Wrong size population generated: " + population.size();
        for (List<Integer> candidate : population)
        {
            assert candidate.size() == candidateLength : "Wrong size candidate generated: " + candidate.size();
            for (int i = 1; i < candidateLength; i++)
            {
                assert candidate.contains(i) : "Candidate is missing element " + i + ".";
            }
        }
    }
}