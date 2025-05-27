package servlet;

import dao.EstudiantewebJpaController;
import dto.Estudianteweb;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="/LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private EstudiantewebJpaController controller;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Pregunta01_war_1.0-SNAPSHOTPU");
        controller = new EstudiantewebJpaController(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String passwordParam = request.getParameter("password");

        int password;
        try {
            password = Integer.parseInt(passwordParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("login.html?error=invalid");
            return;
        }

        List<Estudianteweb> estudiantes = controller.findEstudiantewebEntities();
        for (Estudianteweb e : estudiantes) {
            if (e.getNdniEstdWeb().equals(username) &&
                e.getPassEstd().equals(password)) {

                HttpSession session = request.getSession();
                session.setAttribute("usuario", e);
                response.sendRedirect("index.html");
                return;
            }
        }

        // Si no coincide ningún usuario
        response.sendRedirect("login.html?error=invalid");
    }
}

