package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/self-encapsulate-field
 */
public class SelfEncapsulateFieldTest {

    class ProblemRange {
        private int low;
        private int high;

        public ProblemRange(int low, int high) {
            this.low = low;
            this.high = high;
        }

        boolean includes(int arg) {
            return arg>= low && arg <= high;
        }
    }

    class SolutionRange {
        private int low;
        private int high;

        public SolutionRange(int low, int high) {
            this.low = low;
            this.high = high;
        }

        boolean includes(int arg) {
            return arg>= getLow() && arg <= getHigh();
        }

        public int getLow() {
            return low;
        }

        public int getHigh() {
            return high;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new ProblemRange(34, 45).includes(40)).isTrue();
        assertThat(new ProblemRange(34, 45).includes(50)).isFalse();

        // Solution cases
        assertThat(new SolutionRange(34, 45).includes(40)).isTrue();
        assertThat(new SolutionRange(34, 45).includes(50)).isFalse();
    }

}
