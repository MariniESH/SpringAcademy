<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Elenco Corsi</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Corsi Frequentati</h1>

<form action="<c:url value='/alunni/corsi/${alunno.id}' />" method="post">
  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Ore</th>
      <th>Anno Accademico</th>
      <th>Docente</th>
      <th>Iscriviti</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${corsi}">
      <tr>
        <td>${c.id}</td>
        <td>${c.nome}</td>
        <td>${c.ore}</td>
        <td>${c.anno}</td>
        <c:choose>
          <c:when test="${c.docente.id != null}">
            <td>${c.docente.nome} ${c.docente.cognome}</td>
          </c:when>
          <c:otherwise>
            <td><mark><b>Nessun Docente</b></mark></td>
          </c:otherwise>
        </c:choose>
        <td>
          <input class="form-check-input" type="checkbox" value="${c.id}" name="corsiIds"
                 <c:if test="${iscritti.contains(c.id)}">checked="checked"</c:if>
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
