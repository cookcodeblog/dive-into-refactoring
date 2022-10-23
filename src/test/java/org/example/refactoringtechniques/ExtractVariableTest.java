package org.example.refactoringtechniques;

import org.junit.Test;

/**
 * https://refactoring.guru/extract-variable
 */
public class ExtractVariableTest {

    class Problem {
        private String platform;
        private String browser;
        private int resize;

        public Problem(String platform, String browser, int resize) {
            this.platform = platform;
            this.browser = browser;
            this.resize = resize;
        }

        void renderBanner() {
            if ((platform.toUpperCase().indexOf("MAC") > -1) &&
                    (browser.toUpperCase().indexOf("IE") > -1) &&
                    wasInitialized() && resize > 0) {
                System.out.println("Render banner...");
            }
        }

        private boolean wasInitialized() {
            return true;
        }
    }

    class Solution {
        private String platform;
        private String browser;
        private int resize;

        public Solution(String platform, String browser, int resize) {
            this.platform = platform;
            this.browser = browser;
            this.resize = resize;
        }

        void renderBanner() {
            final boolean isMacOs = platform.toUpperCase().indexOf("MAC") > -1;
            final boolean isIE = browser.toUpperCase().indexOf("IE") > -1;
            final boolean wasResized = resize > 0;

            if (isMacOs && isIE && wasInitialized() && wasResized) {
                System.out.println("Render banner...");
            }
        }

        private boolean wasInitialized() {
            return true;
        }
    }

    @Test
    public void shouldHaveSameBehavior() {
        Problem problem = new Problem("MacOs 14", "IE11", 1);
        problem.renderBanner();

        Solution solution = new Solution("MacOs 14", "IE11", 1);
        solution.renderBanner();
    }
}
