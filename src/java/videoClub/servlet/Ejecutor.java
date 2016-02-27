/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoClub.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringEscapeUtils;
import videoClub.bd.ClientesBD;
import videoClub.sistema.Cliente;

/**
 *
 * @author usuario54
 */
@WebServlet(name = "Ejecutor", urlPatterns = {"/ejecutor"})
public class Ejecutor extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf8");
        response.getWriter().write("");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=utf-8");
        String pedido = request.getParameter("pedido");
        String json = "";
        if (pedido != null) {
            switch (pedido) {
                case "agregarCliente":
                    Cliente cliente = new Cliente(
                        Integer.parseInt(request.getParameter("cedula")),
                        request.getParameter("nombre"),
                        request.getParameter("apellido1"),
                        request.getParameter("apellido2"),
                        Integer.parseInt(request.getParameter("telefono")),
                        request.getParameter("email"),
                        request.getParameter("direccion")
                    );
                    ClientesBD cbd = new ClientesBD();
                    String exito = Boolean.toString(cbd.agregar(cliente));
                    json = "{\"success\": \"" + exito + "\", \"error\": \"" + StringEscapeUtils.escapeJson(cbd.getError()) + "\"}";
                    break;
                case "actualizarCliente":
                    
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
