package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/replace-exception-with-test
 */
public class ReplaceExceptionWithTest {

    class Problem {
        private double[] values = {1, 2.2, 3, 5.5, 8, 13.13, 21};

        double getValueForPeriod(int periodNumber) {
            try {
                return values[periodNumber];
            } catch (ArrayIndexOutOfBoundsException e) {
                return 0;
            }
        }
    }

    class Solution {
        private double[] values = {1, 2, 3, 5, 8, 13, 21};

        double getValueForPeriod(int periodNumber) {
            try {
                return values[periodNumber];
            } catch (ArrayIndexOutOfBoundsException e) {
                return 0;
            }
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new Problem().getValueForPeriod(2))
                .isCloseTo(3, Offset.offset(0.001));

        // captured exception
        assertThat(new Problem().getValueForPeriod(99))
                .isCloseTo(0, Offset.offset(0.001));

        // Solution cases
        assertThat(new Solution().getValueForPeriod(2))
                .isCloseTo(3, Offset.offset(0.001));

        // conditional test
        assertThat(new Solution().getValueForPeriod(99))
                .isCloseTo(0, Offset.offset(0.001));
    }
}
