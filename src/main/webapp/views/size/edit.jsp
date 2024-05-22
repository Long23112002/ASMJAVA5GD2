<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Update Size</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">


    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <%--    <link rel="stylesheet" type="text/css"--%>
    <%--          href="${pageContext.request.contextPath}/src/main/webapp/css/style.css"/>--%>

    <link rel="stylesheet" href="../.././css/style.css">
</head>

<body>
<div class="wrapper">
    <aside id="sidebar">
        <div class="d-flex">
            <button class="toggle-btn" type="button">
                <i class="lni lni-grid-alt"></i>
            </button>
            <div class="sidebar-logo">
                <a href="#">ADMIN MANAGER</a>
            </div>
        </div>
        <jsp:include page="../.././views/base/navbarbase.jsp"></jsp:include>
        <div class="sidebar-footer">
            <a href="#" class="sidebar-link">
                <i class="lni lni-exit"></i>
                <span>Logout</span>
            </a>
        </div>
    </aside>
    <div class="main">
        <nav class="navbar navbar-expand px-4 py-3">
            <form action="#" class="d-none d-sm-inline-block">

            </form>
            <div class="navbar-collapse collapse">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item dropdown">
                        <div class="dropdown-menu dropdown-menu-end rounded">

                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <main class="content px-3 py-4">
            <div class="container-fluid">
                <div class="mb-3">
                    <h1 class="text-center">Update size</h1>
                </div>
                <div class="row">
                    <div class="col-12 d-flex justify-content-center">
                        <div class="card w-50">
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/size/update/${size.id}" method="post"  >
                                    <div class="mb-3">
                                        <label for="code" class="form-label">Code</label>
                                        <input type="text" class="form-control" id="code" value="${size.code}" name="code">
                                        <c:if test="${ not empty errors}">
                                            <span id="code-error" class="text-danger">${errors['code']}</span>
                                        </c:if>
                                    </div>

                                    <div class="mb-3">
                                        <label for="name" class="form-label">Name</label>
                                        <input type="text" class="form-control" id="name" value="${size.name}" name="name">
                                        <c:if test="${ not empty errors}">
                                            <span id="code-error" class="text-danger">${errors['name']}</span>
                                        </c:if>
                                    </div>
                                    <div class="mb-3">
                                        <select class="form-select" name="status" aria-label="Default select example">
                                            <option value="1" <c:if test="${size.status eq 1}">selected</c:if>>Active</option>
                                            <option value="0" <c:if test="${size.status != 1}">selected</c:if>>No Active</option>
                                        </select>
                                        <span id="status-error" class="text-danger"></span>
                                    </div>
                                    <a href="${pageContext.request.contextPath}/size/index">
                                        <button type="button" class="btn btn-warning">Back</button>
                                    </a>
                                    <button type="submit" class="btn btn-outline-success">Update</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer class="footer">
            <div class="container-fluid">
                <div class="row text-body-secondary">
                    <div class="col-6 text-start ">
                        <a class="text-body-secondary" href=" #">
                            <strong>Nguyễn Hải Long</strong>
                        </a>
                    </div>
                    <div class="col-6 text-end text-body-secondary d-none d-md-block">
                        <ul class="list-inline mb-0">
                            <li class="list-inline-item">
                                <a class="text-body-secondary" href="#">Contact</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="text-body-secondary" href="#">About Us</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="text-body-secondary" href="#">Terms & Conditions</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>


</body>
</html>