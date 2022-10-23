package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/replace-magic-number-with-symbolic-constant
 */
public class ReplaceMagicNumberWithSymbolicConstantTest {

    class Problem {
        double potentialEnergy(double mass, double height) {
            return mass * height * 9.81;
        }
    }

    class Solution {
        private static final double GRAVITATIONAL_CONSTANT = 9.81;

        double potentialEnergy(double mass, double height) {
            return mass * height * GRAVITATIONAL_CONSTANT;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        assertThat(new Problem().potentialEnergy(20, 5))
                .isCloseTo(981, Offset.offset(0.001));

        assertThat(new Solution().potentialEnergy(20, 5))
                .isCloseTo(981, Offset.offset(0.001));
    }
}
