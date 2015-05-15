package com.github.immrgabriel.junit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.util.Comparator;

public class TestCalc02 {

    @Test
    public void get002SumTest() {
        Calc calc = new Calc();
        Assert.assertEquals(calc.getSum(20, 30), 50);
    }

    @Test
    public void get001SubtractionTest() {
        Calc calc = new Calc();
        Assert.assertEquals(-10, calc.getSum(20, 30));
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
        core.run(Request.aClass(TestCalc02.class).sortWith(new Comparator<Description>() {
            @Override
            public int compare(Description o1, Description o2) {
                return o1.getDisplayName().compareTo(o2.getDisplayName());
            }
        }));
    }
}
