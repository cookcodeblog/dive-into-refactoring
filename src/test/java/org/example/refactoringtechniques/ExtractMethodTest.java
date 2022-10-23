package org.example.refactoringtechniques;

import org.junit.Test;

/**
 * https://refactoring.guru/extract-method
 */
public class ExtractMethodTest {

    class Problem {
        void printOwing() {
            printBanner();

            // Print details
            String name = "SuperHero";
            System.out.println("name: " + name);
            System.out.println("amount: " + getOutstanding());
        }

        private double getOutstanding() {
            return 2300.00;
        }

        private void printBanner() {
            System.out.println("Banner...");
        }
    }

    class Solution {
        void printOwing() {
            printBanner();

            // Print details
            printDetails(getOutstanding());
        }

        private void printDetails(double outstanding) {
            String name = "SuperHero";
            System.out.println("name: " + name);
            System.out.println("amount: " + outstanding);
        }


        private double getOutstanding() {
            return 2300.00;
        }

        private void printBanner() {
            System.out.println("Banner...");
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        Problem problem = new Problem();
        problem.printOwing();

        Solution solution = new Solution();
        solution.printOwing();
    }


}
