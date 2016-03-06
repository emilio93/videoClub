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
import videoClub.bd.ClientesBD;
import videoClub.bd.PeliculasBD;
import videoClub.bd.PrestamosBD;
import videoClub.sistema.Cliente;
import videoClub.sistema.Pelicula;
import videoClub.sistema.Prestamo;

@WebServlet(name = "EjecutorPrestamos", urlPatterns = {"/prestamos/ejecutor"})
public class EjecutorPrestamos extends HttpServlet {

    PrintWriter out;
    String responseType;
    String pedido;
    boolean exito;
    String json;
    PrestamosBD pbd;
    
    public void bootstrap(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        pedido = request.getParameter("pedido");
        exito = false;
        json = "";
        PrestamosBD pbd = new PrestamosBD();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        ArrayList<Prestamo> lp = null;
        int cobro = 0;
        if (pedido != null && pedido.equals("obtener")) {
            String conjunto = request.getParameter("conjunto");
            if (conjunto != null) {
                switch (conjunto) {
                    // Obtiene los prestamos en mora.
                    case "cobro":
                        int id = Integer.parseInt(request.getParameter("id"));
                        Prestamo prestamo = pbd.getPrestamo(id);
                        cobro = prestamo == null? 1: prestamo.obtenerCobro();
                        exito = cobro != 0;
                        break;

                    // Obtiene los prestamos en mora.
                    case "moras":
                        lp = pbd.getMoras();
                        exito = lp != null;
                        break;

                    // Obtiene prestamos por cedula de cliente.
                    case "cedula":
                        lp = pbd.getPrestamosCliente(Integer.parseInt(request.getParameter("cedula")));
                        exito = lp != null && lp.size() > 0 &&lp.get(0) != null;
                        break;

                    // Obtiene prestamos por cedula de cliente.
                    case "titulo":
                        lp = pbd.getPrestamosPelicula(request.getParameter("titulo"));
                        exito = lp != null && lp.size() > 0 &&lp.get(0) != null;
                        break;

                    // Obtiene clientes según cantidad y página.
                    case "pagina":
                        lp = pbd.obtener(
                                Integer.parseInt(request.getParameter("cantidad")), 
                                Integer.parseInt(request.getParameter("pagina")));
                        exito = lp != null;
                        break;

                    // Obtiene todos los clientes.
                    case "todos":
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
        String prestamos = gs.toJson(lp);
        if (lp != null) {
            out.println("{"
                + "\"success\": \"" + Boolean.toString(exito) + "\","
                + "\"error\": \"" + StringEscapeUtils.escapeJson(pbd.getError()) + "\","
                + "\"prestamos\": " + prestamos
                + "}");
        } else {
            out.println("{"
                + "\"success\": \"" + Boolean.toString(exito) + "\","
                + "\"error\": \"" + StringEscapeUtils.escapeJson(pbd.getError()) + "\","
                + "\"cobro\": " + cobro
                + "}");
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        Prestamo prestamo;
        int id;
        if (pedido != null) {
            switch (pedido) {
                case "agregar":
                    ClientesBD cbd = new ClientesBD();
                    Cliente c = cbd.obtener(Integer.parseInt(request.getParameter("cedula")));
                    PeliculasBD pelbd = new PeliculasBD();
                    Pelicula p = pelbd.obtener(request.getParameter("titulo"));
                    prestamo = new Prestamo(c, p);
                    exito = pbd.agregar(prestamo);
                    json = "{"
                            + "\"success\": \"" + exito + "\", "
                            + "\"error\": \"" + StringEscapeUtils.escapeJson(cbd.getError()) + "\""
                            + "}";
                    break;
                case "finalizar":
                    id = Integer.parseInt(request.getParameter("id"));
                    exito = pbd.finalizarPrestamo(id);
                    json = "{"
                            + "\"success\": \"" + exito + "\", "
                            + "\"error\": \"" + StringEscapeUtils.escapeJson(pbd.getError()) + "\""
                            + "}";
                    break;
            }
        }
        out.println(json);
    }
    
    @Override
    public String getServletInfo() {
        return "Realiza la interacción con la base de datos.";
    }
    
    

}
