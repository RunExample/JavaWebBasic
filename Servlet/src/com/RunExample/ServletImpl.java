package com.RunExample;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//最原始的Servlet接口实现
@WebServlet("/try/ServletImpl")
public class ServletImpl implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("--- initialized ---");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("--- do service ---");

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
        pw.println("This is a Servlet Interface Implementation");
        pw.println("<br>尝试Servlet接口实现");
        pw.println("</body></html>");

        pw.close();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("--- destroyed ---");
    }
}
