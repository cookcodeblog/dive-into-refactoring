package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReplaceNestedConditionalWithGuardClausesTest {

    class Problem {
        private boolean isDead;
        private boolean isSeparated;
        private boolean isRetired;
        private double amount;

        public Problem(boolean isDead, boolean isSeparated, boolean isRetired, double amount) {
            this.isDead = isDead;
            this.isSeparated = isSeparated;
            this.isRetired = isRetired;
            this.amount = amount;
        }

        public double getPayAmount() {
            double result;
            if (isDead) {
                result = deadAmount();
            } else {
                if (isSeparated) {
                    result = separatedAmount();
                } else {
                    if (isRetired) {
                        result = retireAmount();
                    } else {
                        result = normalPayAmount();
                    }
                }
            }
            return result;
        }

        private double normalPayAmount() {
            return amount * 12 * 10;
        }

        private double retireAmount() {
            return amount * 12 * 20;
        }

        private double separatedAmount() {
            return amount * 12 * 15;
        }

        private double deadAmount() {
            return amount * 12 * 30;
        }
    }

    class Solution {
        private boolean isDead;
        private boolean isSeparated;
        private boolean isRetired;
        private double amount;

        public Solution(boolean isDead, boolean isSeparated, boolean isRetired, double amount) {
            this.isDead = isDead;
            this.isSeparated = isSeparated;
            this.isRetired = isRetired;
            this.amount = amount;
        }

        public double getPayAmount() {
            if (isDead) {
                return deadAmount();
            }
            if (isSeparated) {
                return separatedAmount();
            }
            if (isRetired) {
                return retireAmount();
            }
            return normalPayAmount();
        }

        private double normalPayAmount() {
            return amount * 12 * 10;
        }

        private double retireAmount() {
            return amount * 12 * 20;
        }

        private double separatedAmount() {
            return amount * 12 * 15;
        }

        private double deadAmount() {
            return amount * 12 * 30;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new Problem(true, false, false, 10).getPayAmount())
                .isCloseTo(3600, Offset.offset(0.001));
        assertThat(new Problem(false, true, false, 10).getPayAmount())
                .isCloseTo(1800, Offset.offset(0.001));
        assertThat(new Problem(false, false, true, 10).getPayAmount())
                .isCloseTo(2400, Offset.offset(0.001));
        assertThat(new Problem(false, false, false, 10).getPayAmount())
                .isCloseTo(1200, Offset.offset(0.001));

        // Solution cases
        assertThat(new Solution(true, false, false, 10).getPayAmount())
                .isCloseTo(3600, Offset.offset(0.001));
        assertThat(new Solution(false, true, false, 10).getPayAmount())
                .isCloseTo(1800, Offset.offset(0.001));
        assertThat(new Solution(false, false, true, 10).getPayAmount())
                .isCloseTo(2400, Offset.offset(0.001));
        assertThat(new Solution(false, false, false, 10).getPayAmount())
                .isCloseTo(1200, Offset.offset(0.001));
    }
}
