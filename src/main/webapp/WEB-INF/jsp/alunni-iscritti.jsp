<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Alunni</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Alunni Iscritti</h1>


<a class="btn btn-primary mb-3" href="<c:url value='/'/>">Home</a>
<form action="<c:url value='/corsi/alunni/${corso.id}' />" method="post">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Data</th>
            <th>Citt&agrave;</th>
            <th>Voto</th>
            <th>Iscritto</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${alunni}">
            <tr>
                <td>${a.id}</td>
                <td>${a.nome}</td>
                <td>${a.cognome}</td>
                <td>${a.data}</td>
                <td>${a.citta}</td>
                <td>${a.voto}</td>
                <td>
                    <input class="form-check-input" type="checkbox" value="${a.id}" name="alunniIds"
                        <c:if test="${iscritti.contains(a.id)}">checked="checked"</c:if>
                    >
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-primary" type="submit">Salva</button>
</form>
</body>
</html>
