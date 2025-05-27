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

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
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

        // Obtener valores del formulario
        String nrodni = request.getParameter("nrodni");
        String password = request.getParameter("password");

        System.out.println("LOGIN INGRESADO: " + nrodni + " / " + password);

        List<Estudianteweb> estudiantes = controller.findEstudiantewebEntities();
        for (Estudianteweb e : estudiantes) {
            System.out.println("Comparando con: " + e.getNdniEstdWeb() + " / " + e.getPassEstd());

            if (e.getNdniEstdWeb().trim().equals(nrodni.trim()) &&
                e.getPassEstd().trim().equals(password.trim())) {

                // Iniciar sesión
                HttpSession session = request.getSession();
                session.setAttribute("usuario", e);

                // Redireccionar correctamente al index
                response.sendRedirect(request.getContextPath() + "/index.html");
                return;
            }
        }

        // Falló el login
        response.sendRedirect("login.html?error=invalid");
    }
}

