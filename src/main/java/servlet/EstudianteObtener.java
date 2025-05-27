package servlet;
import dao.EstudiantewebJpaController;
import dto.Estudianteweb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EstudianteObtener")
public class EstudianteObtener extends HttpServlet {
    private EstudiantewebJpaController controller;

    @Override
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Preg01_war_1.0-SNAPSHOTPU");
        controller = new EstudiantewebJpaController(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Estudianteweb e = controller.findEstudianteweb(id);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"codiEstdWeb\":" + e.getCodiEstdWeb() + ",");
        out.print("\"ndniEstdWeb\":\"" + e.getNdniEstdWeb() + "\",");
        out.print("\"appaEstdWeb\":\"" + e.getAppEstdWeb() + "\",");
        out.print("\"apmaEstdWeb\":\"" + e.getApmaEstWeb() + "\",");
        out.print("\"nombEstdWeb\":\"" + e.getNombEstdWeb() + "\",");
        out.print("\"fechNaciEstdWeb\":\"" + e.getFechNaciEstdWeb() + "\",");
        out.print("\"logiEstd\":\"" + e.getLogiEstd() + "\"");
        out.print("}");
    }
}

