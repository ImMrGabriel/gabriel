package com.github.immrgabriel.servlet.logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SimpleSelectTest {

    @Test
    public void testGetEmployees() throws Exception {
        for(Employee employee : SimpleSelect.getEmployees()) {
            System.out.println(employee);
        }
    }
}