<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Product Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">


    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <%--    <link rel="stylesheet" type="text/css"--%>
    <%--          href="${pageContext.request.contextPath}/src/main/webapp/css/style.css"/>--%>

    <link rel="stylesheet" href="../../css/style.css">
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
        <jsp:include page=".././base/navbarbase.jsp"></jsp:include>
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
                    <h1>Manager Staff</h1>
                    <a href="${pageContext.request.contextPath}/staff/create"> <button class="btn btn-outline-success">Add Customer</button></a>

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Code</th>
                            <th scope="col">Fullname</th>
                            <th scope="col">Username</th>
                            <th scope="col">Status</th>
                            <th scope="col">Function</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${listStaff}" >
                            <tr>
                                <th scope="row">${item.id}</th>
                                <td>${item.code}</td>
                                <td>${item.name}</td>
                                <td>${item.username}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.status eq 1}">
                                            <span style="color: green;">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">No Active</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/staff/edit/${item.id}"> <button class="btn btn-outline-warning">Update</button></a>
                                    <a href="${pageContext.request.contextPath}/staff/delete/${item.id}" onclick="return confirm('Are you sure you want to delete customer ?')"> <button class="btn btn-outline-danger">Delete</button></a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:if test="${totalPages > 0}">
                                <c:if test="${currentPage > 0}">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage <= 0}">
                                    <li class="page-item disabled">
                                        <span class="page-link">Previous</span>
                                    </li>
                                </c:if>
                                <c:forEach var="i" begin="0" end="${totalPages - 1}">
                                    <li class="page-item <c:if test="${currentPage == i}">active</c:if>">
                                        <a class="page-link" href="?page=${i}">${i + 1}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages - 1}">
                                    <li class="page-item">
                                        <a class="page-link"  href="?page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == totalPages - 1}">
                                    <li class="page-item">
                                        <a class="page-link"  href="?page=0">Next</a>
                                    </li>
                                </c:if>
                            </c:if>
                        </ul>
                    </nav>
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
<script>
    const hamBurger = document.querySelector(".toggle-btn");

    hamBurger.addEventListener("click", function () {
        document.querySelector("#sidebar").classList.toggle("expand");
    });

</script>

</body>
</html>