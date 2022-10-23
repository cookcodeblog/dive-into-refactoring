package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/preserve-whole-object
 */
public class PreserveWholeObjectTest {

    class ProblemPlan {
        int value;

        public ProblemPlan(int value) {
            this.value = value;
        }

        boolean withinRange(int low, int high) {
            return value >= low && value <= high;
        }
    }

    class SolutionPlan {
        int value;

        public SolutionPlan(int value) {
            this.value = value;
        }

        boolean withinRange(Range range) {
            return value >= range.low && value <= range.high;
        }
    }

    class Range {
        int low;
        int high;

        public Range(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        assertThat(new ProblemPlan(4).withinRange(3, 5)).isTrue();

        assertThat(new SolutionPlan(4).withinRange(new Range(3, 5))).isTrue();
    }
}
