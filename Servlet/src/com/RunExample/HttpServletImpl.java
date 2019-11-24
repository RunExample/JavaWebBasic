package com.RunExample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/try/HttpServletImpl")
public class HttpServletImpl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        pw.println("<html><body>");
        pw.println("This is a HttpServlet Implementation");
        pw.println("<br>尝试HttpServlet实现");
        pw.println("</body></html>");

        pw.close();
    }
}
