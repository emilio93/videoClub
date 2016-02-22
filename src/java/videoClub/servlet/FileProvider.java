/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoClub.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario54
 */
@WebServlet(name = "FileProvider", urlPatterns = {"*.js", "*.css"})
public class FileProvider extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Da acceso a diferentes tipos de archivos.";
    }

}
