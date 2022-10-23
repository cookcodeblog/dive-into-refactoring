package org.example.refactoringtechniques;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://refactoring.guru/replace-conditional-with-polymorphism
 */
public class ReplaceConditionalWithPolymorphismTest {

    class ProblemBird {
        final static String EUROPEAN = "EUROPEAN";
        final static String AFRICAN = "AFRICAN";
        final static String NORWEGIAN_BLUE = "NORWEGIAN_BLUE";

        private String type;
        private double baseSpeed;
        private double loadFactor;
        private int numberOfCoconuts;
        private boolean isNailed;
        private double voltage;

        public ProblemBird(String type) {
            this.type = type;
        }

        double getSpeed() {
            switch (type) {
                case EUROPEAN:
                    return getBaseSpeed();
                case AFRICAN:
                    return getBaseSpeed() - getLoadFactor() * numberOfCoconuts;
                case NORWEGIAN_BLUE:
                    return (isNailed) ? 0 : getBaseSpeed(voltage);
            }
            throw new RuntimeException("Should be unreachable");
        }

        private double getLoadFactor() {
            return loadFactor;
        }

        private double getBaseSpeed() {
            return baseSpeed;
        }

        private double getBaseSpeed(double voltage) {
            return baseSpeed + voltage;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setBaseSpeed(double baseSpeed) {
            this.baseSpeed = baseSpeed;
        }

        public void setLoadFactor(double loadFactor) {
            this.loadFactor = loadFactor;
        }

        public int getNumberOfCoconuts() {
            return numberOfCoconuts;
        }

        public void setNumberOfCoconuts(int numberOfCoconuts) {
            this.numberOfCoconuts = numberOfCoconuts;
        }

        public boolean isNailed() {
            return isNailed;
        }

        public void setNailed(boolean nailed) {
            isNailed = nailed;
        }

        public double getVoltage() {
            return voltage;
        }

        public void setVoltage(double voltage) {
            this.voltage = voltage;
        }
    }

    abstract class SolutionBird {
        protected double baseSpeed;

        public SolutionBird(double baseSpeed) {
            this.baseSpeed = baseSpeed;
        }

        abstract double getSpeed();

        protected double getBaseSpeed() {
            return baseSpeed;
        }

    }

    class EuropeanBird extends SolutionBird {

        public EuropeanBird(double baseSpeed) {
            super(baseSpeed);
        }

        @Override
        double getSpeed() {
            return getBaseSpeed();
        }
    }

    class AfricanBird extends SolutionBird {

        private double loadFactor;
        private int numberOfCoconuts;

        public AfricanBird(double baseSpeed, double loadFactor, int numberOfCoconuts) {
            super(baseSpeed);
            this.loadFactor = loadFactor;
            this.numberOfCoconuts = numberOfCoconuts;
        }

        @Override
        double getSpeed() {
            return getBaseSpeed() - getLoadFactor() * numberOfCoconuts;
        }

        private double getLoadFactor() {
            return loadFactor;
        }
    }

    class NorwegianBlueBird extends SolutionBird {
        private boolean isNailed;
        private double voltage;

        public NorwegianBlueBird(double baseSpeed, boolean isNailed, double voltage) {
            super(baseSpeed);
            this.isNailed = isNailed;
            this.voltage = voltage;
        }

        @Override
        double getSpeed() {
            return (isNailed) ? 0 : getBaseSpeed(voltage);
        }

        private double getBaseSpeed(double voltage) {
            return baseSpeed + voltage;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        // Problem cases
        ProblemBird problemEuropeanBird = new ProblemBird(ProblemBird.EUROPEAN);
        problemEuropeanBird.setBaseSpeed(20);
        assertThat(problemEuropeanBird.getSpeed())
                .isCloseTo(20, Offset.offset(0.001));

        ProblemBird problemAfricanBird = new ProblemBird(ProblemBird.AFRICAN);
        problemAfricanBird.setBaseSpeed(20);
        problemAfricanBird.setLoadFactor(0.5);
        problemAfricanBird.setNumberOfCoconuts(10);
        assertThat(problemAfricanBird.getSpeed())
                .isCloseTo(15, Offset.offset(0.001));

        ProblemBird problemNorwBird = new ProblemBird(ProblemBird.NORWEGIAN_BLUE);
        problemNorwBird.setBaseSpeed(20);
        problemNorwBird.setNailed(false);
        problemNorwBird.setVoltage(5);
        assertThat(problemNorwBird.getSpeed())
                .isCloseTo(25, Offset.offset(0.001));

        // Solution cases
        assertThat(new EuropeanBird(20).getSpeed())
                .isCloseTo(20, Offset.offset(0.001));

        assertThat(new AfricanBird(20, 0.5, 10).getSpeed())
                .isCloseTo(15, Offset.offset(0.001));

        assertThat(new NorwegianBlueBird(20, false, 5).getSpeed())
                .isCloseTo(25, Offset.offset(0.001));



    }
}
