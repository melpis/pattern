package servlet;

import command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "dispatch",
        urlPatterns = {"/"}
)
public class DispatcherServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURL = req.getRequestURI();
        String method = req.getMethod().toLowerCase();
        String url = requestURL.substring(1);
        Command command = null;
        try {
            command = handlerMapping(method, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(command != null){
            command.service(req,resp);
            viewResolver(req, resp, url);
        }
    }

    private void viewResolver(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/"+url+".jsp").forward(req, resp);
    }

    private Command handlerMapping(String method, String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String[] urlToken = url.split("\\/");
        if(url.contains("/")){
            StringBuilder urlBuilder = new StringBuilder();
            for(String className:urlToken){
                urlBuilder.append(firstCharesterUpper(className));
            }
            url = urlBuilder.toString();
        } else {
            url = firstCharesterUpper(url);
        }
        return (Command)Class.forName("command."+firstCharesterUpper(method)+url+"Command").newInstance();
    }

    private String firstCharesterUpper(String url) {
        return url.substring(0, 1).toUpperCase() + url.substring(1);
    }
}