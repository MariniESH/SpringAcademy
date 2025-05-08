<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tables</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<body class="container mt-4">
<h1>Scegli Tabella</h1>
<a class="btn btn-dark mb-3" href="<c:url value='/docenti/lista'/>"><i class="bi bi-person-badge"></i>Docenti</a>
<a class="btn btn-dark mb-3" href="<c:url value='/alunni/lista'/>"><i class="bi bi-people-fill"></i>Alunni</a>
<a class="btn btn-dark mb-3" href="<c:url value='/corsi/lista'/>"><i class="bi bi-people"></i>Corsi</a>

</body>
</html>
