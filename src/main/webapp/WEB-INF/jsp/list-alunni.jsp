<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Alunni</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Elenco Alunni</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/'/>">Home</a>

<a class="btn btn-primary mb-3" href="<c:url value='/alunni/nuovo'/>">Nuovo Alunno</a>
<c:choose>
    <c:when test="${viewType == 'lista'}">
        <a class="btn btn-info mb-3" href="<c:url value='/alunni/promossi'/>">Alunni Promossi</a>
    </c:when>
    <c:otherwise>
        <a class="btn btn-secondary mb-3" href="<c:url value='/alunni/lista'/>">Mostra Alunni</a>
    </c:otherwise>
</c:choose>

<!-- Input ricerca -->
<form method="get" action="<c:url value='/alunni/lista'/>">
    <div class="input-group mb-3">
        <input type="text" class="form-control" name="citta" placeholder="Filtra per citt&agrave;" value="${param.citta}">
    </div>
</form>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Data</th>
        <th>Citt&agrave;</th>
        <th>Voto</th>
        <th>Azioni</th>
        <th>Corsi</th>
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
                <a class="btn btn-sm btn-secondary" href="<c:url value='/alunni/${a.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/alunni/${a.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
            <td><a class="btn btn-warning mb-3" href="<c:url value='/alunni/corsi/${a.id}' />">Corsi</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
