package com.github.immrgabriel.junit;

import org.junit.*;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCalc03 {

    @Parameterized.Parameters
    public static Collection<Integer[]> data() {
        return Arrays.asList(new Integer[][] {
                {5, 3, 8, 2},
                {15, 10, 25, 5},
                {5, 10, 15, -5}
        });
    }

    private int x1, x2, sum, sub;

    public TestCalc03(int x1, int x2, int sum, int sub) {
        this.x1 = x1;
        this.x2 = x2;
        this.sum = sum;
        this.sub = sub;
    }

    @Test
    public void getSumTest() {
        Calc calc = new Calc();
        Assert.assertEquals(calc.getSum(x1, x2), sum);
    }

    @Test
    public void getSubtractionTest() {
        Calc calc = new Calc();
        Assert.assertEquals(sub, calc.getSum(x1, x2));
    }

    @BeforeClass
    public static void allTestsStarted() {
        System.out.println("All tests started");
    }

    @AfterClass
    public static void allTestsFinished() {
        System.out.println("All tests finished");
    }

    @Before
    public void testStarted() {
        System.out.println("        Started");
    }

    @After
    public void testFinished() {
        System.out.println("        Finished");
    }


    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new RunListener() {
            @Override
            public void testFailure(Failure failure) throws Exception {
                System.out.println("        Failed:" + failure.getDescription().getDisplayName() + " [" + failure.getMessage() + "]");
            }

            @Override
            public void testStarted(Description description) throws Exception {
                System.out.println("    Started:" + description.getDisplayName());
            }

            @Override
            public void testFinished(Description description) throws Exception {
                System.out.println("    Finished:" + description.getDisplayName());
            }
        });
        core.run(TestCalc03.class);
    }
}
