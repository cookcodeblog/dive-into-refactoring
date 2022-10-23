package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * https://refactoring.guru/introduce-assertion
 */
public class IntroduceAssertionTest {

    class Problem {
        private final double NULL_EXPENSE = 0.0;

        private double expenseLimit;
        private Project primaryProject;

        public Problem(double expenseLimit, Project primaryProject) {
            this.expenseLimit = expenseLimit;
            this.primaryProject = primaryProject;
        }

        double getExpenseLimit() {
            // Should have either expense limit or a primary project.
            return (expenseLimit != NULL_EXPENSE) ?
                    expenseLimit :
                    primaryProject.getMemberExpenseLimit();
        }
    }

    class Solution {
        private final double NULL_EXPENSE = 0.0;

        private double expenseLimit;
        private Project primaryProject;

        public Solution(double expenseLimit, Project primaryProject) {
            this.expenseLimit = expenseLimit;
            this.primaryProject = primaryProject;
        }

        double getExpenseLimit() {
            // Explicit assumption with `assert` keyword syntax
            assert (expenseLimit != NULL_EXPENSE || primaryProject != null);
            return (expenseLimit != NULL_EXPENSE) ?
                    expenseLimit :
                    primaryProject.getMemberExpenseLimit();
        }
    }

    class Project {
        double getMemberExpenseLimit() {
            return 300;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        assertThat(new Problem(500, null).getExpenseLimit())
                .isCloseTo(500, Offset.offset(0.001));
        assertThat(new Problem(0, new Project()).getExpenseLimit())
                .isCloseTo(300, Offset.offset(0.001));

        // verify NullPointerException
        assertThatThrownBy(() -> {
            new Problem(0, null).getExpenseLimit();
        }).isInstanceOf(NullPointerException.class);

        // Solution cases
        assertThat(new Solution(500, null).getExpenseLimit())
                .isCloseTo(500, Offset.offset(0.001));
        assertThat(new Solution(0, new Project()).getExpenseLimit())
                .isCloseTo(300, Offset.offset(0.001));

        // verify assertion errors
        assertThatThrownBy(() -> {
            new Solution(0, null).getExpenseLimit();
        }).isInstanceOf(AssertionError.class);
    }
}
