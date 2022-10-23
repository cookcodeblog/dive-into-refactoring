package org.example.refactoringtechniques;

import org.junit.Test;

/**
 * https://refactoring.guru/consolidate-duplicate-conditional-fragments
 */
public class ConsolidateDuplicateConditionalFragmentsTest {

    class Problem {
        private double price;
        private boolean isSpecial;

        public Problem(double price, boolean isSpecial) {
            this.price = price;
            this.isSpecial = isSpecial;
        }

        void sendTotal() {
            double total = 0;
            if (isSpecialDeal()) {
                total = price * 0.95;
                System.out.println("Total: " + total);
                send();
            } else {
                total = price * 0.98;
                System.out.println("Total: " + total);
                send();
            }
        }

        private void send() {
            System.out.println("Send...");
        }

        private boolean isSpecialDeal() {
            return this.isSpecial;
        }
    }

    class Solution {
        private double price;
        private boolean isSpecial;

        public Solution(double price, boolean isSpecial) {
            this.price = price;
            this.isSpecial = isSpecial;
        }

        void sendTotal() {
            double total = 0;
            if (isSpecialDeal()) {
                total = price * 0.95;
            } else {
                total = price * 0.98;
            }
            System.out.println("Total: " + total);
            send();
        }

        private void send() {
            System.out.println("Send...");
        }

        private boolean isSpecialDeal() {
            return this.isSpecial;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        new Problem(100, true).sendTotal();
        new Problem(100, false).sendTotal();

        new Solution(100, true).sendTotal();
        new Solution(100, false).sendTotal();
    }
}
