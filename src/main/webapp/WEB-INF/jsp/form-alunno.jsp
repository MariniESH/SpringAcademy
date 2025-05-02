<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Alunno</title>
    <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container">
<h1>Alunno</h1>
    <div class= "container">
    <a class="btn btn-secondary" href="/alunni/lista">Torna Indietro</a>
        <form:form action="/alunni/save" method="post" modelAttribute="alunno">
            <form:hidden path="id"/>
            <div class="m-auto my-2">
                <form:label path="nome" class="form-label">Nome</form:label>
                <form:input path="nome" class="form-control" type="text"/>
            </div>
            <div class="m-auto my-2">
                <form:label path="cognome" class="form-label">Cognome</form:label>
                <form:input path="cognome" class="form-control" type="text"/>
            </div>
            <div class="m-auto my-2">
                <form:label path="data" class="form-label">Data</form:label>
                <form:input path="data" class="form-control" type="date"/>
            </div>
            <div class="m-auto my-2">
                 <form:label path="citta" class="form-label">Citt&agrave;</form:label>
                 <form:input path="citta" class="form-control" type="text"/>
            </div>
            <div class="m-auto my-2">
                <form:label path="voto" class="form-label">Voto</form:label>
                <form:input path="voto" class="form-control" type="number" step="0.1" min="0"/>
            </div>
            <div class="text-end">
                <button class="btn btn-primary" type="submit">Salva</button>
            </div>
        </form:form>
    </div>
</body>
</html>