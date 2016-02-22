package videoClub.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilio Rojas
 */
@WebServlet(name = "PeliculasSL", urlPatterns = {"/peliculas"})
public class PeliculasSL extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        request.setAttribute("message", "hola");
        request.getRequestDispatcher("peliculas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("message", "hola");
        request.getRequestDispatcher("peliculas.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Este servelet maneja el módulo de las películas";
    }// </editor-fold>

}
