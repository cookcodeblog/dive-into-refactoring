package org.example.refactoringtechniques;

import org.junit.Test;

/**
 * https://refactoring.guru/split-temporary-variable
 */
public class SplitTemporaryVariableTest {

    class Problem {
        private double height;
        private double width;

        public Problem(double height, double width) {
            this.height = height;
            this.width = width;
        }

        void print() {
            double temp = 2 * (height + width);
            System.out.println(temp);
            temp = height * width;
            System.out.println(temp);
        }
    }

    class Solution {
        private double height;
        private double width;

        public Solution(double height, double width) {
            this.height = height;
            this.width = width;
        }

        void print() {
            final double perimeter = 2 * (height + width);
            System.out.println(perimeter);
            final double area = height * width;
            System.out.println(area);
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        Problem problem = new Problem(3, 4);
        problem.print();

        Solution solution = new Solution(3, 4);
        solution.print();
    }
}
