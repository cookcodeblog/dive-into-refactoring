package org.example.refactoringtechniques;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * https://refactoring.guru/replace-temp-with-query
 */
public class ReplaceTempWithQueryTest {

    class Problem {
        private int quantity;
        private double itemPrice;

        public Problem(int quantity, double itemPrice) {
            this.quantity = quantity;
            this.itemPrice = itemPrice;
        }

        double calculateTotal() {
            double basePrice = quantity * itemPrice;
            if (basePrice > 1000) {
                return basePrice * 0.95;
            } else {
                return basePrice * 0.98;
            }
        }
    }

    class Solution {
        private int quantity;
        private double itemPrice;

        public Solution(int quantity, double itemPrice) {
            this.quantity = quantity;
            this.itemPrice = itemPrice;
        }

        double calculateTotal() {
            if (basePrice() > 1000) {
                return basePrice() * 0.95;
            } else {
                return basePrice() * 0.98;
            }
        }

        double basePrice() {
            return quantity * itemPrice;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertEquals(980, new Problem(1, 1000).calculateTotal(), 0.001);
        assertEquals(1900, new Problem(2, 1000).calculateTotal(), 0.001);

        // Solution cases
        assertEquals(980, new Solution(1, 1000).calculateTotal(), 0.001);
        assertEquals(1900, new Solution(2, 1000).calculateTotal(), 0.001);
    }
}
