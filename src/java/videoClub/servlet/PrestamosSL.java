package videoClub.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author emilio
 */
@WebServlet(name = "PrestamosSL", urlPatterns = {"/prestamos"})
public class PrestamosSL extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        request.setAttribute("message", "hola");
        request.getRequestDispatcher("prestamos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("message", "hola");
        request.getRequestDispatcher("prestamos.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Este servelet maneja el módulo de los clientes";
    }// </editor-fold>

}
