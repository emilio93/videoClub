package videoClub.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClientesSL", urlPatterns = {"/clientes", "/"})
public class ClientesSL extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Este servelet maneja el módulo de los clientes";
    }
}
