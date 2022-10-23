package org.example.refactoringtechniques;

import org.junit.Test;

/**
 * https://refactoring.guru/replace-array-with-object
 */
public class ReplaceArrayWithObjectTest {

    class Problem {
        void print() {
            String[] row = new String[2];
            row[0] = "Liverpool";
            row[1] = "15";

            System.out.println(row[0] + ": " + row[1]);
        }
    }

    class Solution {
        void print() {
            Performance row = new Performance();
            row.setName("Liverpool");
            row.setWins("15");

            System.out.println(row.getName() + ": " + row.getWins());
        }
    }

    class Performance {
        private String name;
        private String wins;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWins() {
            return wins;
        }

        public void setWins(String wins) {
            this.wins = wins;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        Problem problem = new Problem();
        problem.print();

        Solution solution = new Solution();
        solution.print();
    }
}
