package com.github.immrgabriel.servlet.tmct;

import com.github.immrgabriel.servlet.logic.Employee;
import com.github.immrgabriel.servlet.logic.SimpleSelect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletAndDB extends HttpServlet{
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        List<Employee> list = SimpleSelect.getEmployees();
        if(list.isEmpty()) {
            pw.println("<h2>empty :(</h2>");
            return;
        }
        pw.println("<h1><b>List employees:</b></h1><br>");
        pw.println("<table border=1>");
        for (Employee employee : list) {
            pw.println("<tr>");
                pw.println("<td>" + employee.getId() + "</td>");
                pw.println("<td>" + employee.getFirst_name() + "</td>");
                pw.println("<td>" + employee.getLast_name() + "</td>");
            pw.println("</tr>");
        }
        pw.println("</table>");
    }
}
