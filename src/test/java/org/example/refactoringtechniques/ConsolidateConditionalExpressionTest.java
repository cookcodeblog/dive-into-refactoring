package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/consolidate-conditional-expression
 */
public class ConsolidateConditionalExpressionTest {

    class Problem {
        private int seniority;
        private int monthDisabled;
        private boolean isPartTime;

        public Problem(int seniority, int monthDisabled, boolean isPartTime) {
            this.seniority = seniority;
            this.monthDisabled = monthDisabled;
            this.isPartTime = isPartTime;
        }

        double disabilityAmount() {
            if (seniority < 2) {
                return 0;
            }
            if (monthDisabled > 12) {
                return 0;
            }
            if (isPartTime) {
                return 0;
            }
            return 1000;
        }
    }

    class Solution {
        private int seniority;
        private int monthDisabled;
        private boolean isPartTime;

        public Solution(int seniority, int monthDisabled, boolean isPartTime) {
            this.seniority = seniority;
            this.monthDisabled = monthDisabled;
            this.isPartTime = isPartTime;
        }

        double disabilityAmount() {
            if (isNotEligableForDisability()) {
                return 0;
            }
            return 1000;
        }

        private boolean isNotEligableForDisability() {
            return seniority < 2
                    || monthDisabled > 12
                    || isPartTime;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new Problem(3,10, false).disabilityAmount())
                .isCloseTo(1000, Offset.offset(0.001));
        assertThat(new Problem(3,10, true).disabilityAmount())
                .isCloseTo(0, Offset.offset(0.001));

        // Solution cases
        assertThat(new Solution(3,10, false).disabilityAmount())
                .isCloseTo(1000, Offset.offset(0.001));
        assertThat(new Solution(3,10, true).disabilityAmount())
                .isCloseTo(0, Offset.offset(0.001));
    }
}
