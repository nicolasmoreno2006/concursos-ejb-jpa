<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Concursos - Listado</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 700px; margin: 40px auto; }
        h1, h2 { text-align: center; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 24px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #2c3e50; color: white; }
        .mensaje { color: #27632a; font-weight: bold; text-align: center; }
        .error { color: #C0392B; font-weight: bold; text-align: center; }
        a { display: block; text-align: center; margin-top: 16px; }
    </style>
</head>
<body>
    <h1>Estudiantes y Cursos</h1>

    <c:if test="${not empty mensaje}"><p class="mensaje">${mensaje}</p></c:if>
    <c:if test="${not empty error}"><p class="error">${error}</p></c:if>

    <h2>Estudiantes</h2>
    <table>
        <tr><th>Id</th><th>Nombre</th><th>eMail</th><th>Cursos inscritos</th></tr>
        <c:forEach var="est" items="${estudiantes}">
            <tr>
                <td>${est.id}</td>
                <td>${est.nombre}</td>
                <td>${est.email}</td>
                <td>
                    <c:forEach var="curso" items="${est.cursos}">
                        ${curso.nombreCurso}&nbsp;
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Cursos</h2>
    <table>
        <tr><th>Codigo</th><th>Nombre</th><th>Creditos</th><th>Semestre</th><th>Cupo</th><th>Inscritos</th></tr>
        <c:forEach var="curso" items="${cursos}">
            <tr>
                <td>${curso.codigoCurso}</td>
                <td>${curso.nombreCurso}</td>
                <td>${curso.numeroCreditos}</td>
                <td>${curso.semestre}</td>
                <td>${curso.numeroEstudiantesAdmitidos}</td>
                <td>${curso.estudiantes.size()}</td>
            </tr>
        </c:forEach>
    </table>

    <a href="index.jsp">Volver al formulario</a>
</body>
</html>
