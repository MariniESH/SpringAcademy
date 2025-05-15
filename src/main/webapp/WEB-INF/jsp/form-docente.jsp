<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Docente</title>
    <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container">
<h1>${title}</h1>
    <div class= "container">
    <a class="btn btn-secondary" href="/docenti/lista">Torna Indietro</a>
        <form:form action="/docenti/new" method="post" modelAttribute="docente">
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
            <div class="text-end">
                <button class="btn btn-primary" type="submit">Salva</button>
            </div>
        </form:form>
    </div>

</body>
</html>