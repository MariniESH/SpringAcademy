<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Elenco Corsi</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/corsi/nuovo'/>">Nuovo Corso</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Ore</th>
        <th>Anno Accademico</th>
        <th>ID Docente</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${corsi}">
        <tr>
            <td>${c.id}</td>
            <td>${c.nome}</td>
            <td>${c.ore}</td>
            <td>${c.anno}</td>
            <td>${c.docente.id}</td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/corsi/${c.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/corsi/${c.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
