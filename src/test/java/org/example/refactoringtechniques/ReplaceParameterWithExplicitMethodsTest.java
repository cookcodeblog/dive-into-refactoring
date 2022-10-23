package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/replace-parameter-with-explicit-methods
 */
public class ReplaceParameterWithExplicitMethodsTest {

    class Problem {
        int height;
        int width;

        void setValue(String name, int value) {
            if (name.equals("height")) {
                height = value;
                return;
            }
            if (name.equals("width")) {
                width = value;
                return;
            }
            assert false;
        }
    }

    class Solution {
        int height;
        int width;

        void setHeight(int arg) {
            height = arg;
        }

        void setWidth(int arg) {
            width = arg;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        Problem problem = new Problem();
        problem.setValue("height", 3);
        problem.setValue("width", 4);
        assertThat(problem.height).isEqualTo(3);
        assertThat(problem.width).isEqualTo(4);

        // Solution cases
        Solution solution = new Solution();
        solution.setHeight(3);
        solution.setWidth(4);
        assertThat(solution.height).isEqualTo(3);
        assertThat(solution.width).isEqualTo(4);
    }
}
