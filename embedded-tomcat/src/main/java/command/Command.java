package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
     void service(HttpServletRequest request, HttpServletResponse response);
}
