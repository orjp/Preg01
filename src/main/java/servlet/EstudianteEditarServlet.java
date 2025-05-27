
import dao.EstudiantewebJpaController;
import dto.Estudianteweb;
import java.io.IOException;
import java.sql.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EstudianteEditar")
public class EstudianteEditarServlet extends HttpServlet {
    private EstudiantewebJpaController controller;

    @Override
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Preg01_war_1.0-SNAPSHOTPU");
        controller = new EstudiantewebJpaController(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Estudianteweb e = controller.findEstudianteweb(id);
        e.setNdniEstdWeb(request.getParameter("ndni"));
        e.setAppEstdWeb(request.getParameter("appa"));
        e.setApmaEstWeb(request.getParameter("apma"));
        e.setNombEstdWeb(request.getParameter("nomb"));
        e.setFechNaciEstdWeb(Date.valueOf(request.getParameter("fecha")));
        e.setLogiEstd(request.getParameter("logi"));

        try {
            controller.edit(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("EstudianteListar");
    }
}

