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


<a class="btn btn-primary mb-3" href="<c:url value='/corsi/lista'/>">Torna Indietro</a>


<!-- Input ricerca -->
<form method="get" action="<c:url value='/alunni/lista'/>">
    <div class="input-group mb-3">
        <input type="text" class="form-control" name="citta" placeholder="Filtra per citt&agrave;" value="${param.citta}">
    </div>
</form>

<input type="hidden" name=${corsoId}/>
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

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
