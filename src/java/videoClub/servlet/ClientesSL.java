package videoClub.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import videoClub.bd.ClientesBD;
import videoClub.sistema.Cliente;

/**
 *
 * @author Emilio Rojas.
 */
@WebServlet(name = "ClientesSL", urlPatterns = {"/clientes", "/"})
public class ClientesSL extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientesBD clientes = new ClientesBD();
        ArrayList<Cliente> listaClientes = clientes.obtener();
        
        Cliente cliente = clientes.obtener(207180854);
        
        String arr = listaClientes == null? "lista null": "lista no null";
        
        String nom;
        if (cliente != null) {
            nom = cliente.getNombre();
        } else {
            nom = "sin nombre";
        }
        
        response.setContentType("text/html");
        request.setAttribute("message", "hola");
        request.setAttribute("clientes", listaClientes);
        request.setAttribute("cliente", cliente);
        request.setAttribute("nombreCliente", nom);
        request.setAttribute("listaNull", arr);
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("message", "hola");
        request.setAttribute("clientes", new ArrayList<Cliente>());
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Este servelet maneja el módulo de los clientes";
    }// </editor-fold>

}
