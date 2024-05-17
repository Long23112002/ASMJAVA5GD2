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

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

        ::after,
        ::before {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        a {
            text-decoration: none;
        }

        li {
            list-style: none;
        }

        body {
            font-family: 'Poppins', sans-serif;
        }

        .wrapper {
            display: flex;
        }

        .main {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            width: 100%;
            overflow: hidden;
            transition: all 0.35s ease-in-out;
            background-color: #fff;
            min-width: 0;
        }

        #sidebar {
            width: 70px;
            min-width: 70px;
            z-index: 1000;
            transition: all .25s ease-in-out;
            background-color: #0e2238;
            display: flex;
            flex-direction: column;
        }

        #sidebar.expand {
            width: 260px;
            min-width: 260px;
        }

        .toggle-btn {
            background-color: transparent;
            cursor: pointer;
            border: 0;
            padding: 1rem 1.5rem;
        }

        .toggle-btn i {
            font-size: 1.5rem;
            color: #FFF;
        }

        .sidebar-logo {
            margin: auto 0;
        }

        .sidebar-logo a {
            color: #FFF;
            font-size: 1.15rem;
            font-weight: 600;
        }

        #sidebar:not(.expand) .sidebar-logo,
        #sidebar:not(.expand) a.sidebar-link span {
            display: none;
        }

        #sidebar.expand .sidebar-logo,
        #sidebar.expand a.sidebar-link span {
            animation: fadeIn .25s ease;
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
            }

            100% {
                opacity: 1;
            }
        }

        .sidebar-nav {
            padding: 2rem 0;
            flex: 1 1 auto;
        }

        a.sidebar-link {
            padding: .625rem 1.625rem;
            color: #FFF;
            display: block;
            font-size: 0.9rem;
            white-space: nowrap;
            border-left: 3px solid transparent;
        }

        .sidebar-link i,
        .dropdown-item i {
            font-size: 1.1rem;
            margin-right: .75rem;
        }

        a.sidebar-link:hover {
            background-color: rgba(255, 255, 255, .075);
            border-left: 3px solid #3b7ddd;
        }

        .sidebar-item {
            position: relative;
        }

        #sidebar:not(.expand) .sidebar-item .sidebar-dropdown {
            position: absolute;
            top: 0;
            left: 70px;
            background-color: #0e2238;
            padding: 0;
            min-width: 15rem;
            display: none;
        }

        #sidebar:not(.expand) .sidebar-item:hover .has-dropdown + .sidebar-dropdown {
            display: block;
            max-height: 15em;
            width: 100%;
            opacity: 1;
        }

        #sidebar.expand .sidebar-link[data-bs-toggle="collapse"]::after {
            border: solid;
            border-width: 0 .075rem .075rem 0;
            content: "";
            display: inline-block;
            padding: 2px;
            position: absolute;
            right: 1.5rem;
            top: 1.4rem;
            transform: rotate(-135deg);
            transition: all .2s ease-out;
        }

        #sidebar.expand .sidebar-link[data-bs-toggle="collapse"].collapsed::after {
            transform: rotate(45deg);
            transition: all .2s ease-out;
        }

        .navbar {
            background-color: #f5f5f5;
            box-shadow: 0 0 2rem 0 rgba(33, 37, 41, .1);
        }

        .navbar-expand .navbar-collapse {
            min-width: 200px;
        }

        .avatar {
            height: 40px;
            width: 40px;
        }


        @media (min-width: 768px) {
        }
    </style>
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
        <ul class="sidebar-nav">
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/admin/manager_product" class="sidebar-link">
                    <i class="fa-solid fa-shirt"></i>
                    <span>Manager Product</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/admin/manager_color" class="sidebar-link">
                    <i class="fa-solid fa-palette"></i>
                    <span>Manager Color</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/admin/manager_size" class="sidebar-link">
                    <i class="fa-solid fa-ruler"></i>
                    <span>Manager Size</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a href="${pageContext.request.contextPath}/admin/manager_product_detail" class="sidebar-link">
                    <i class="fa-solid fa-ruler"></i>
                    <span>Manager Product Detail</span>
                </a>
            </li>

        </ul>
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
                    <h1>Product Detail</h1>
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