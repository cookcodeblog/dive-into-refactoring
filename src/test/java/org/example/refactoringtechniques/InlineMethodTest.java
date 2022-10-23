package org.example.refactoringtechniques;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://refactoring.guru/inline-method
 */
public class InlineMethodTest {

    class ProblemPizzaDelivery {
        private int numberOfLateDeliveries;

        public ProblemPizzaDelivery(int numberOfLateDeliveries) {
            this.numberOfLateDeliveries = numberOfLateDeliveries;
        }

        int getRating() {
            return moreThanFiveLateDeliveries() ? 2 : 1;
        }

        private boolean moreThanFiveLateDeliveries() {
            return numberOfLateDeliveries > 5;
        }
    }

    class SolutionPizzaDelivery {
        private int numberOfLateDeliveries;

        public SolutionPizzaDelivery(int numberOfLateDeliveries) {
            this.numberOfLateDeliveries = numberOfLateDeliveries;
        }

        int getRating() {
            return numberOfLateDeliveries > 5 ? 2 : 1;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertEquals(2, new ProblemPizzaDelivery(6).getRating());
        assertEquals(1, new ProblemPizzaDelivery(5).getRating());
        assertEquals(1, new ProblemPizzaDelivery(4).getRating());

        // Solution cases
        assertEquals(2, new SolutionPizzaDelivery(6).getRating());
        assertEquals(1, new SolutionPizzaDelivery(5).getRating());
        assertEquals(1, new SolutionPizzaDelivery(4).getRating());
    }
}
