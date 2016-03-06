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
import videoClub.sistema.Cliente;

@WebServlet(name = "EjecutorClientes", urlPatterns = {"/clientes/ejecutor"})
public class EjecutorClientes extends HttpServlet {

    PrintWriter out;
    String responseType;
    String pedido;
    boolean exito;
    String json;
    ClientesBD cbd;
    
    public void bootstrap(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        pedido = request.getParameter("pedido");
        exito = false;
        json = "";
        cbd = new ClientesBD();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        ArrayList<Cliente> lc = null;
        if (pedido != null && pedido.equals("obtener")) {
            String conjunto = request.getParameter("conjunto");
            if (conjunto != null) {
                switch (conjunto) {
                    // Obtiene los clientes morosos.
                    case "morosos":
                        lc = cbd.clientesMorosos();
                        exito = lc != null;
                        break;

                    // Obtiene cliente por cedula.
                    case "cedula":
                        lc = new ArrayList<>();
                        lc.add(cbd.obtener(Integer.parseInt(request.getParameter("cedula"))));
                        exito = lc != null && lc.size() > 0 &&lc.get(0) != null;
                        break;

                    // Obtiene cliente por id.
                    case "id":
                        lc = new ArrayList<>();
                        lc.add(cbd.obtenerConId(Integer.parseInt(request.getParameter("id"))));
                        exito = lc != null && lc.size() > 0 &&lc.get(0) != null;
                        break;

                    // Obtiene clientes según cantidad y página.
                    case "pagina":
                        lc = cbd.obtener(
                                Integer.parseInt(request.getParameter("cantidad")), 
                                Integer.parseInt(request.getParameter("pagina")));
                        exito = lc != null;
                        break;

                    // Obtiene todos los clientes.
                    case "todos":
                        lc = cbd.obtener();
                        exito = lc != null;
                        break;
                }
            } else {
                lc = cbd.obtener();
                exito = lc != null;
            }
        }
        Gson gs = new Gson();
        String clientes = gs.toJson(lc);
        out.println("{"
                + "\"success\": \"" + Boolean.toString(exito) + "\","
                + "\"error\": \"" + StringEscapeUtils.escapeJson(cbd.getError()) + "\","
                + "\"clientes\": " + clientes
                + "}");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bootstrap(request, response);
        Cliente cliente;
        int id;
        switch (pedido) {
            case "agregar":
                cliente = new Cliente(
                    Integer.parseInt(request.getParameter("cedula")),
                    request.getParameter("nombre"),
                    request.getParameter("apellido1"),
                    request.getParameter("apellido2"),
                    Integer.parseInt(request.getParameter("telefono")),
                    request.getParameter("email"),
                    request.getParameter("direccion")
                );

                exito = cbd.agregar(cliente);
                json = "{" + 
                        "\"success\": \"" + exito + "\", " + 
                        "\"error\": \"" + StringEscapeUtils.escapeJson(cbd.getError()) + "\"" + 
                        "}";
                break;
            case "actualizar":
                cliente = new Cliente(
                    Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("cedula")),
                    request.getParameter("nombre"),
                    request.getParameter("apellido1"),
                    request.getParameter("apellido2"),
                    Integer.parseInt(request.getParameter("telefono")),
                    request.getParameter("email"),
                    request.getParameter("direccion")
                );
                exito = cbd.actualizar(cliente);
                json = "{"
                        + "\"success\": \"" + exito + "\", "
                        + "\"error\": \"" + StringEscapeUtils.escapeJson(cbd.getError()) + "\""
                        + "}";
                break;
            case "eliminar":
                exito = cbd.eliminar(Integer.parseInt(request.getParameter("id")));
                json = "{"
                        + "\"success\": \"" + Boolean.toString(exito) + "\", "
                        + "\"error\": \"" + StringEscapeUtils.escapeJson(cbd.getError()) + "\""
                        + "}";
                break;
        }
        out.println(json);
    }
    
    @Override
    public String getServletInfo() {
        return "Realiza la interacción con la base de datos.";
    }
}
