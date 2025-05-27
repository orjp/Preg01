/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.EstudiantewebJpaController;
import dto.Estudianteweb;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yojha
 */
@WebServlet(name = "SvAlumnos", urlPatterns = {"/SvAlumnos"})
public class SvAlumnos extends HttpServlet {

    private EstudiantewebJpaController controller;

    @Override
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Preg01_war_1.0-SNAPSHOTPU");
        controller = new EstudiantewebJpaController(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Estudianteweb> lista = controller.findEstudiantewebEntities();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><head><title>Listado</title></head><body>");
        out.println("<h2>Listado de Estudiantes</h2>");
        out.println("<a href='agregar.html'>Agregar Estudiante</a><br><br>");
        out.println("<table border='1'><tr><th>DNI</th><th>Ap. Paterno</th><th>Ap. Materno</th><th>Nombre</th><th>F. Nac</th><th>Usuario</th><th>Acción</th></tr>");
        for (Estudianteweb e : lista) {
            out.println("<tr>");
            out.println("<td>" + e.getNdniEstdWeb() + "</td>");
            out.println("<td>" + e.getAppEstdWeb() + "</td>");
            out.println("<td>" + e.getApmaEstWeb() + "</td>");
            out.println("<td>" + e.getNombEstdWeb() + "</td>");
            out.println("<td>" + e.getFechNaciEstdWeb() + "</td>");
            out.println("<td>" + e.getLogiEstd() + "</td>");
            out.println("<td><a href='editar.html?id=" + e.getCodiEstdWeb() + "'>Editar</a></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<br><a href='EstudianteImprimir'>Imprimir PDF</a>");
        out.println("</body></html>");
    }
}
