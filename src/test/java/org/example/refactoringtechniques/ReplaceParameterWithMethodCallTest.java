package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/replace-parameter-with-method-call
 */
public class ReplaceParameterWithMethodCallTest {

    class Problem {
        int quantity;
        int itemPrice;

        public Problem(int quantity, int itemPrice) {
            this.quantity = quantity;
            this.itemPrice = itemPrice;
        }

        double finalPrice() {
            int basePrice = quantity * itemPrice;
            double seasonDiscount = this.getSeasonalDiscount();
            double fees = this.getFees();
            double finalPrice = discountedPrice(basePrice, seasonDiscount, fees);
            return finalPrice;
        }

        private double discountedPrice(int basePrice, double seasonDiscount, double fees) {
            return basePrice * seasonDiscount + fees;
        }

        private double getFees() {
            return 2;
        }

        private double getSeasonalDiscount() {
            return 0.95;
        }
    }

    class Solution {
        int quantity;
        int itemPrice;

        public Solution(int quantity, int itemPrice) {
            this.quantity = quantity;
            this.itemPrice = itemPrice;
        }

        double finalPrice() {
            int basePrice = quantity * itemPrice;
            double finalPrice = discountedPrice(basePrice);
            return finalPrice;
        }

        private double discountedPrice(int basePrice) {
            double seasonDiscount = this.getSeasonalDiscount();
            double fees = this.getFees();
            return basePrice * seasonDiscount + fees;
        }

        private double getFees() {
            return 2;
        }

        private double getSeasonalDiscount() {
            return 0.95;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        assertThat(new Problem(5, 20).finalPrice())
                .isCloseTo(97, Offset.offset(0.001));

        assertThat(new Solution(5, 20).finalPrice())
                .isCloseTo(97, Offset.offset(0.001));
    }
}
