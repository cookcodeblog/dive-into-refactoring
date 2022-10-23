package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/replace-constructor-with-factory-method
 */
public class ReplaceConstructorWithFactoryMethodTest {

    @Test
    public void shouldHaveSameBehavior() {
        assertThat(new ProblemEmployee(1)).isNotNull();

        assertThat(SolutionEmployee.create(1)).isNotNull();
    }
}

class ProblemEmployee {
    private int type;

    public ProblemEmployee(int type) {
        this.type = type;
    }
}

class SolutionEmployee {
    private int type;

    private SolutionEmployee(int type) {
        this.type = type;
    }

    public static SolutionEmployee create(int type) {
        SolutionEmployee employee = new SolutionEmployee(type);
        return employee;
    }
}

