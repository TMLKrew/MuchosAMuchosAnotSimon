package es.albarregas.controllers;

import es.albarregas.beans.Profesor;
import es.albarregas.beans.Modulo;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO<Profesor> gdao = daof.getGenericoDAO();
        Profesor profesor = new Profesor();
        Set<Modulo> modulos = new HashSet<Modulo>();
        Set<Profesor> profesores = new HashSet<Profesor>();
        Modulo modulo = new Modulo();
        String url = null;
        switch (request.getParameter("op")) {
            case "add":

                try {

                    BeanUtils.populate(profesor, request.getParameterMap());
                    profesores.add(profesor);

                    Enumeration<String> parametros = request.getParameterNames();
                    while (parametros.hasMoreElements()) {
                        String nombre = parametros.nextElement();
                        if (nombre.startsWith("modulo")) {
                            modulo = new Modulo();
                            modulo.setDenominacion(request.getParameter(nombre));
                            modulo.setProfesores(profesores);
                            modulos.add(modulo);
                        }
                    }

                    profesor.setModulos(modulos);

                } catch (IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                gdao.insertUpdate(profesor);
                url = "index.html";
                break;
            case "delete":
//                profesor = adao.getOne(Integer.parseInt(request.getParameter("registro")));
//                adao.delete(profesor);
                profesor = gdao.select(Long.parseLong(request.getParameter("registro")), Profesor.class);
                gdao.delete(profesor);
                url = "index.html";
                break;
            case "update":
                profesor = gdao.select(Long.parseLong(request.getParameter("registro")), Profesor.class);
                request.setAttribute("profesor", profesor);
                url = "JSP/formularioActualizar.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
