package com.RunExample;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// GenericServlet是Servlet接口的基本实现，给了 init destroy 等过程的默认实现, HttpServlet也是继承自GenericServlet抽象类
@WebServlet("/try/GenericServletImpl")
public class GenericServletImpl extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request;
        HttpServletResponse response;
        try
        {
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)res;
        }
        catch(ClassCastException e)
        {
            throw new ServletException("non-HTTP request or response");
        }
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.println("<html><body>");
        pw.println("This is a GenericServlet Implementation");
        pw.println("<br>尝试GenericServlet实现");
        pw.println("</body></html>");

        pw.close();
    }
}
