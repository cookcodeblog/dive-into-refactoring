package org.example.refactoringtechniques;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://refactoring.guru/inline-temp
 */
public class InlineTempTest {

    class Order {
        private double basePrice;

        public Order(double basePrice) {
            this.basePrice = basePrice;
        }

        public double basePrice() {
            return basePrice;
        }
    }

    class Problem {
        boolean hasDiscount(Order order) {
            double basePrice = order.basePrice();
            return basePrice > 1000;
        }
    }

    class Solution {
        boolean hasDiscount(Order order) {
            return order.basePrice() > 1000;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertTrue(new Problem().hasDiscount(new Order(1234)));
        assertFalse(new Problem().hasDiscount(new Order(999)));

        // Solution cases
        assertTrue(new Solution().hasDiscount(new Order(1234)));
        assertFalse(new Solution().hasDiscount(new Order(999)));
    }
}
