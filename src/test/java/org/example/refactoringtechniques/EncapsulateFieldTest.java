package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/encapsulate-field
 */
public class EncapsulateFieldTest {

    class ProblemPerson {
        public String name;
    }

    class SolutionPerson {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        ProblemPerson problemPerson = new ProblemPerson();
        problemPerson.name = "SuperHero";
        assertThat(problemPerson.name).isEqualTo("SuperHero");

        SolutionPerson solutionPerson = new SolutionPerson();
        solutionPerson.setName("SuperHero");
        assertThat(solutionPerson.getName()).isEqualTo("SuperHero");
    }
}
