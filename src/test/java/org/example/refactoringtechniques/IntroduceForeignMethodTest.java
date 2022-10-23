package org.example.refactoringtechniques;

import org.junit.Test;

import java.util.Date;

/**
 * https://refactoring.guru/introduce-foreign-method
 */
public class IntroduceForeignMethodTest {

    class ProblemReport {
        private Date previousEnd;

        public ProblemReport(Date previousEnd) {
            this.previousEnd = previousEnd;
        }

        void sendReport() {
            // You can't modify `Date` class
            Date nextDay = new Date(previousEnd.getYear(),
                    previousEnd.getMonth(),
                    previousEnd.getDate() + 1);
            System.out.println("Next Day: " + nextDay);
            System.out.println("Send report....");
        }
    }

    class SolutionReport {
        private Date previousEnd;

        public SolutionReport(Date previousEnd) {
            this.previousEnd = previousEnd;
        }

        void sendReport() {
            // You can't modify `Date` class
            // But you can modify `DateHelper.nextDay()` method as you can
            Date nextDay = DateHelper.nextDay(previousEnd);
            System.out.println("Next Day: " + nextDay);
            System.out.println("Send report....");
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        Date previousEnd = new Date(2022, 10, 1);

        ProblemReport problemReport = new ProblemReport(previousEnd);
        problemReport.sendReport();

        SolutionReport solutionReport = new SolutionReport(previousEnd);
        solutionReport.sendReport();
    }
}

class DateHelper {

    public final static Date nextDay(Date arg) {
        return new Date(arg.getYear(),
                arg.getMonth(),
                arg.getDate() + 1);
    }
}
