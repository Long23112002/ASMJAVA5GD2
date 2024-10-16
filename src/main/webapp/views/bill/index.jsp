<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Manager Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">


    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet"/>
    <%--    <link rel="stylesheet" type="text/css"--%>
    <%--          href="${pageContext.request.contextPath}/src/main/webapp/css/style.css"/>--%>

    <link rel="stylesheet" href="../../css/style.css">
</head>

<body>
<security:authorize access="hasRole('ADMIN')">
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
        </aside>
        <div class="main">
            <div class="d-flex justify-content-end mx-5 mt-4">
                <span class="text-center"
                      style="color: #000000 ; margin-left:50px ">Hello : ${sessionScope.userName}</span>
                <br>
                <span style="color: #000000 ; margin-left: 23px">Role : ${sessionScope.role}</span>
            </div>
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
                        <h1>Manager Order</h1>
                    </div>
                    <div class="col-12" style="display: flex; justify-content: end">
                        <form style="padding-right: 100px" id="form"
                              action="${pageContext.request.contextPath}/bill/filter" method="post"
                              class="d-flex align-items-center">
                            <div class="col-8">
                                <span>Status</span>
                                <select name="statusSearch" class="form-select" aria-label="Default select example">
                                    <option value="-1" ${valueSearch == -1 ? 'selected' : ''}>All</option>
                                    <option value="0" ${valueSearch == 0 ? 'selected' : ''}>Pending</option>
                                    <option value="1" ${valueSearch  == 1 ? 'selected' : ''}>Completed</option>
                                    <option value="2" ${valueSearch == 2 ? 'selected' : ''}>Cancel</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-outline-dark mt-4 mx-2">Filter</button>
                        </form>
                    </div>


                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Staff</th>
                            <th scope="col">Customer</th>
                            <th scope="col">Phone Customer</th>
                            <th scope="col">Date</th>
                            <th scope="col">Total Money</th>
                            <th scope="col">Status</th>
                            <th scope="col">Functions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${listBill}">

                            <tr>
                                <th scope="row">${item.id}</th>
                                <td>${item.staff.name}</td>
                                <td>${item.customer.name}</td>
                                <td>${item.customer.phone}</td>
                                <td>
<%--                                    <fmt:parseDate value="${item.dateBuy}" pattern="EEE MMM dd HH:mm:ss zzz yyyy"--%>
<%--                                                   var="parsedDate"/>--%>
<%--                                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd' 'HH:mm"--%>
<%--                                                    var="formattedDateTime"/>--%>
<%--                                        ${formattedDateTime}--%>
                                    ${item.dateBuy}
                                </td>
                                <td>${item.total}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.status eq 1}">
                                            <span style="color: green;">Completed</span>
                                        </c:when>
                                        <c:when test="${item.status eq 0}">
                                            <span style="color: chocolate;">Pending</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">Cancel</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <security:authorize access="hasRole('ADMIN')">
                                        <a href="${pageContext.request.contextPath}/bill/edit/${item.id}">
                                            <button class="btn btn-outline-warning">Update</button>
                                        </a>
                                    </security:authorize>
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
                                        <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == totalPages - 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=0">Next</a>
                                    </li>
                                </c:if>
                            </c:if>
                        </ul>
                    </nav>
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
</security:authorize>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
    const hamBurger = document.querySelector(".toggle-btn");

    hamBurger.addEventListener("click", function () {
        document.querySelector("#sidebar").classList.toggle("expand");
    });

</script>
<script src="../../js/scopeFilter.js"></script>

</body>
</html>