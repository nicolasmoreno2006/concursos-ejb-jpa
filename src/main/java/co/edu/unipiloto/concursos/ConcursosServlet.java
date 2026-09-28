package co.edu.unipiloto.concursos;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controlador (capa Web) que recibe las acciones del formulario y delega
 * en el EJB (capa de logica de negocio), que a su vez usa JPA (capa de
 * persistencia) para guardar/consultar las entidades.
 */
@WebServlet(name = "ConcursosServlet", urlPatterns = {"/Concursos"})
public class ConcursosServlet extends HttpServlet {

    @EJB
    private ConcursosBean concursosBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String error = null;
        String mensaje = null;

        try {
            switch (accion == null ? "" : accion) {
                case "crearEstudiante":
                    concursosBean.crearEstudiante(
                            request.getParameter("nombre"),
                            request.getParameter("email"));
                    mensaje = "Estudiante creado correctamente.";
                    break;

                case "crearCurso":
                    concursosBean.crearCurso(
                            request.getParameter("codigoCurso"),
                            request.getParameter("nombreCurso"),
                            Integer.parseInt(request.getParameter("numeroCreditos")),
                            request.getParameter("semestre"),
                            Integer.parseInt(request.getParameter("numeroEstudiantesAdmitidos")));
                    mensaje = "Curso creado correctamente.";
                    break;

                case "inscribir":
                    concursosBean.inscribir(
                            Integer.valueOf(request.getParameter("estudianteId")),
                            request.getParameter("codigoCurso"));
                    mensaje = "Estudiante inscrito correctamente.";
                    break;

                default:
                    error = "Accion no reconocida.";
            }
        } catch (NumberFormatException e) {
            error = "Verifica que los campos numericos sean validos.";
        } catch (IllegalArgumentException | IllegalStateException e) {
            error = e.getMessage();
        } catch (Exception e) {
            error = "No fue posible completar la operacion: " + e.getMessage();
        }

        request.setAttribute("error", error);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estudiantes", concursosBean.listarEstudiantes());
        request.setAttribute("cursos", concursosBean.listarCursos());

        RequestDispatcher rd = request.getRequestDispatcher("studentInfo.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("estudiantes", concursosBean.listarEstudiantes());
        request.setAttribute("cursos", concursosBean.listarCursos());
        RequestDispatcher rd = request.getRequestDispatcher("studentInfo.jsp");
        rd.forward(request, response);
    }
}
