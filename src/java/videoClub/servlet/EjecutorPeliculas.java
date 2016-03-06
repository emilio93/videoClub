package videoClub.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringEscapeUtils;
import videoClub.bd.PeliculasBD;
import videoClub.sistema.Pelicula;

@WebServlet(name = "EjecutorPeliculas", urlPatterns = {"/peliculas/ejecutor"})
public class EjecutorPeliculas extends HttpServlet {
    
    PrintWriter out;
    String responseType;
    String pedido;
    boolean exito;
    String json;
    PeliculasBD pbd;
    
    public void bootstrap(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        pedido = request.getParameter("pedido");
        exito = false;
        json = "";
        PeliculasBD pbd = new PeliculasBD();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        ArrayList<Pelicula> lp = null;
        if (pedido != null && pedido.equals("obtener")) {
            String conjunto = request.getParameter("conjunto");
            if (conjunto != null) {
                switch (conjunto) {
                    // Obtiene las peliculas en mora.
                    case "moras":
                        lp = pbd.peliculasEnMora();
                        exito = lp != null;
                        break;

                    // Obtiene pelicula por titulo.
                    case "titulo":
                        lp = new ArrayList<>();
                        lp.add(pbd.obtener(request.getParameter("titulo")));
                        exito = lp != null && lp.size() > 0 && lp.get(0) != null;
                        break;

                    // Obtiene peliculas por id.
                    case "id":
                        lp = new ArrayList<>();
                        lp.add(pbd.obtener(Integer.parseInt(request.getParameter("id"))));
                        exito = lp != null && lp.size() > 0 && lp.get(0) != null;
                        break;

                    // Obtiene clientes según cantidad y página.
                    case "pagina":
                        lp = pbd.obtener(
                                Integer.parseInt(request.getParameter("cantidad")), 
                                Integer.parseInt(request.getParameter("pagina")));
                        exito = lp != null;
                        break;

                    // Obtiene todos los clientes.
                    case "todas":
                        lp = pbd.obtener();
                        exito = lp != null;
                        break;
                }
            } else {
                lp = pbd.obtener();
                exito = lp != null;
            }
        }
        Gson gs = new Gson();
        String peliculas = gs.toJson(lp);
        out.println("{"
                + "\"success\": \"" + Boolean.toString(exito) + "\","
                + "\"error\": \"" + StringEscapeUtils.escapeJson(pbd.getError()) + "\","
                + "\"peliculas\": " + peliculas
                + "}");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        Pelicula pelicula;
        if (pedido != null && pedido.equals("agregar")) {
            pelicula = new Pelicula(
                request.getParameter("titulo"),
                request.getParameter("direccion"),
                request.getParameter("produccion"),
                Integer.parseInt(request.getParameter("ano")),
                request.getParameter("genero"),
                Integer.parseInt(request.getParameter("duracion")),
                request.getParameter("sinopsis"),
                Integer.parseInt(request.getParameter("cantidad"))
            );
            exito = pbd.agregar(pelicula);
            json = "{"
                    + "\"success\": \"" + Boolean.toString(exito) + "\", "
                    + "\"error\": \"" + StringEscapeUtils.escapeJson(pbd.getError()) + "\""
                    + "}";
        }
        out.println(json);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        Pelicula pelicula;
        if (pedido != null && pedido.equals("actualizar")) {
            pelicula = new Pelicula(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("titulo"),
                request.getParameter("direccion"),
                request.getParameter("produccion"),
                Integer.parseInt(request.getParameter("ano")),
                request.getParameter("genero"),
                Integer.parseInt(request.getParameter("duracion")),
                request.getParameter("sinopsis"),
                Integer.parseInt(request.getParameter("cantidad"))
            );
            exito = pbd.actualizar(pelicula);
            json = "{"
                    + "\"success\": \"" + Boolean.toString(exito) + "\", "
                    + "\"error\": \"" + StringEscapeUtils.escapeJson(pbd.getError()) + "\""
                    + "}";
        }
        out.println(json);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        if (pedido != null && pedido.equals("eliminar")) {
            exito = pbd.eliminar(Integer.parseInt(request.getParameter("id")));
            json = "{"
                    + "\"success\": \"" + Boolean.toString(exito) + "\", "
                    + "\"error\": \"" + StringEscapeUtils.escapeJson(pbd.getError()) + "\""
                    + "}";
        }
        out.println(json);
    }
    
    public String getServletInfo() {
        return "Realiza la interacción con la base de datos.";
    }
    
    

}
