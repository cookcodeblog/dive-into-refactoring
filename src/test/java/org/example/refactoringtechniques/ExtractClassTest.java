package org.example.refactoringtechniques;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtractClassTest {

    class ProblemPerson {
        private String name;
        private String officeAreaCode;
        private String officeNumber;

        public ProblemPerson(String name) {
            this.name = name;
        }

        private String getTelephoneNumber() {
            return officeAreaCode + officeNumber;
        }

        public String getOfficeAreaCode() {
            return officeAreaCode;
        }

        public void setOfficeAreaCode(String officeAreaCode) {
            this.officeAreaCode = officeAreaCode;
        }

        public String getOfficeNumber() {
            return officeNumber;
        }

        public void setOfficeNumber(String officeNumber) {
            this.officeNumber = officeNumber;
        }
    }

    class SolutionPerson {
        private String name;
        private TelephoneNumber telephoneNumber;

        public SolutionPerson(String name) {
            this.name = name;
        }

        public String getTelephoneNumber() {
            return telephoneNumber.getTelephoneNumber();
        }

        public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }
    }

    class TelephoneNumber {
        private String officeAreaCode;
        private String officeNumber;

        public TelephoneNumber(String officeAreaCode, String officeNumber) {
            this.officeAreaCode = officeAreaCode;
            this.officeNumber = officeNumber;
        }

        private String getTelephoneNumber() {
            return officeAreaCode + officeNumber;
        }

        public String getOfficeAreaCode() {
            return officeAreaCode;
        }

        public void setOfficeAreaCode(String officeAreaCode) {
            this.officeAreaCode = officeAreaCode;
        }

        public String getOfficeNumber() {
            return officeNumber;
        }

        public void setOfficeNumber(String officeNumber) {
            this.officeNumber = officeNumber;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        ProblemPerson problemPerson = new ProblemPerson("Huku");
        problemPerson.setOfficeAreaCode("010");
        problemPerson.setOfficeNumber("12345678");
        assertThat(problemPerson.getTelephoneNumber()).isEqualTo("01012345678");

        // Solution cases
        SolutionPerson solutionPerson = new SolutionPerson("Huku");
        solutionPerson.setTelephoneNumber(new TelephoneNumber("010", "12345678"));
        assertThat(solutionPerson.getTelephoneNumber()).isEqualTo("01012345678");
    }
}
