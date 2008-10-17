// ============================================================================
//   Copyright 2006, 2007, 2008 Daniel W. Dyer
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
package org.uncommons.watchmaker.examples.geneticprogramming;

import java.util.Random;
import org.uncommons.watchmaker.framework.Probability;

/**
 * Operations supported by the different types of nodes that make up
 * program trees in the genetic programming example application.
 * @author Daniel Dyer
 */
public interface Node
{
    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any
     * child nodes) and returns a numeric value.
     * @param programParameters Program parameters (possibly used by this node and/or
     * in the evaluation of child nodes).
     * @return The result of evaluating this node and all of its children.
     */
    double evaluate(double[] programParameters);

    /**
     * Recursively builds a string representation of the tree rooted at this node.
     * @return A string representation of this tree.
     */
    String print();

    /**
     * @return The number of levels of nodes that make up this tree.
     */
    int getDepth();

    /**
     * Helper method for the {@link TreeMutation} evolutionary operator.
     * @param rng A source of randomness.
     * @param mutationProbability The probability that a given node will be mutated.
     * @param treeFactory A factory for creating the new sub-trees needed for mutation.
     * @return The mutated node (or the same node if no mutation occurred).
     */
    Node mutate(Random rng, Probability mutationProbability, TreeFactory treeFactory);
}