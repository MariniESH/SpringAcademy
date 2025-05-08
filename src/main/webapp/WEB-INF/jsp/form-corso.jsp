<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Corso</title>
    <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container">
<h1>Corso</h1>
    <div class= "container">
    <a class="btn btn-secondary" href="/corsi/lista">Torna Indietro</a>
        <form:form action="/corsi/save" method="post" modelAttribute="corso">
            <form:hidden path="id"/>
            <div class="m-auto my-2">
                <form:label path="nome" class="form-label">Nome</form:label>
                <form:input path="nome" class="form-control" type="text"/>
            </div>
            <div class="m-auto my-2">
                <form:label path="ore" class="form-label">Ore</form:label>
                <form:input path="ore" class="form-control" type="number"/>
            </div>
            <div class="m-auto my-2">
                <form:label path="anno" class="form-label">Anno Accademico</form:label>
                <form:input path="anno" class="form-control" type="number"/>
            </div>
            <div class="m-auto my-2">
                 <form:label path="docente.id" class="form-label">ID Docente</form:label>
                 <form:input path="docente.id" class="form-control" type="number"/>
            </div>
            <div class="text-end">
                <button class="btn btn-primary" type="submit">Salva</button>
            </div>
        </form:form>
    </div>
</body>
</html>