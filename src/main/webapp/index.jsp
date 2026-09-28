<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Concursos - Inicio</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; }
        h1 { text-align: center; }
        fieldset { margin-bottom: 24px; }
        label { display: block; margin-top: 8px; }
        input, select { width: 100%; padding: 6px; margin-top: 2px; box-sizing: border-box; }
        button { margin-top: 12px; width: 100%; padding: 10px; background: #2c3e50; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Gestion de Cursos y Estudiantes</h1>

    <fieldset>
        <legend>Nuevo estudiante</legend>
        <form action="Concursos" method="post">
            <input type="hidden" name="accion" value="crearEstudiante" />
            <label>Nombre</label>
            <input type="text" name="nombre" required />
            <label>eMail</label>
            <input type="email" name="email" required />
            <button type="submit">Crear estudiante</button>
        </form>
    </fieldset>

    <fieldset>
        <legend>Nuevo curso</legend>
        <form action="Concursos" method="post">
            <input type="hidden" name="accion" value="crearCurso" />
            <label>Codigo del curso</label>
            <input type="text" name="codigoCurso" required />
            <label>Nombre del curso</label>
            <input type="text" name="nombreCurso" required />
            <label>Numero de creditos</label>
            <input type="number" name="numeroCreditos" min="1" required />
            <label>Semestre</label>
            <input type="text" name="semestre" placeholder="Ej: 2026-2" required />
            <label>Numero de estudiantes admitidos</label>
            <input type="number" name="numeroEstudiantesAdmitidos" min="1" required />
            <button type="submit">Crear curso</button>
        </form>
    </fieldset>

    <fieldset>
        <legend>Inscribir estudiante en curso</legend>
        <form action="Concursos" method="post">
            <input type="hidden" name="accion" value="inscribir" />
            <label>Id del estudiante</label>
            <input type="number" name="estudianteId" required />
            <label>Codigo del curso</label>
            <input type="text" name="codigoCurso" required />
            <button type="submit">Inscribir</button>
        </form>
    </fieldset>

    <p style="text-align:center"><a href="Concursos">Ver listado de estudiantes y cursos</a></p>
</body>
</html>
