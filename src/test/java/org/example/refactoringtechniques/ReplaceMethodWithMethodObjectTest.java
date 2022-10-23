package org.example.refactoringtechniques;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://refactoring.guru/replace-method-with-method-object
 */
public class ReplaceMethodWithMethodObjectTest {

    class ProblemOrder {
        private double primaryBasePrice;
        private double secondaryBasePrice;
        private double tertiaryBasePrice;

        public ProblemOrder(double primaryBasePrice, double secondaryBasePrice, double tertiaryBasePrice) {
            this.primaryBasePrice = primaryBasePrice;
            this.secondaryBasePrice = secondaryBasePrice;
            this.tertiaryBasePrice = tertiaryBasePrice;
        }

        public double price() {
            double primaryBasePrice;
            double secondaryBasePrice;
            double tertiaryBasePrice;

            // Perform long computation.
            primaryBasePrice = this.primaryBasePrice;
            secondaryBasePrice = this.secondaryBasePrice;
            tertiaryBasePrice = this.tertiaryBasePrice;
            return primaryBasePrice + secondaryBasePrice + tertiaryBasePrice;
        }
    }

    class SolutionOrder {
        private double primaryBasePrice;
        private double secondaryBasePrice;
        private double tertiaryBasePrice;

        public SolutionOrder(double primaryBasePrice, double secondaryBasePrice, double tertiaryBasePrice) {
            this.primaryBasePrice = primaryBasePrice;
            this.secondaryBasePrice = secondaryBasePrice;
            this.tertiaryBasePrice = tertiaryBasePrice;
        }

        public double price() {
            return new PriceCalculator(this).compute();
        }
    }

    class PriceCalculator {
        private double primaryBasePrice;
        private double secondaryBasePrice;
        private double tertiaryBasePrice;

        public PriceCalculator(SolutionOrder order) {
            // Copy relevant information from the order object
            this.primaryBasePrice = order.primaryBasePrice;
            this.secondaryBasePrice = order.secondaryBasePrice;
            this.tertiaryBasePrice = order.tertiaryBasePrice;
        }

        public double compute() {
            // Perform long computation.
            return primaryBasePrice + secondaryBasePrice + tertiaryBasePrice;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        ProblemOrder problemOrder = new ProblemOrder(11.1, 22.2, 33.3);
        assertEquals(66.6, problemOrder.price(), 0.001);

        // Solution cases
        SolutionOrder solutionOrder = new SolutionOrder(11.1, 22.2, 33.3);
        assertEquals(66.6, solutionOrder.price(), 0.001);
    }
}
