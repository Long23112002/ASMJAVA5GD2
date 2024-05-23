<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Admin Product Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
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

        .item-row.selected {
            background-color: #ffffcc;
            font-weight: bold;
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
        <jsp:include page="../.././views/base/navbarbase.jsp"></jsp:include>
    </aside>
    <div class="main">
        <div class="d-flex justify-content-end mx-5 mt-4">
            <span class="text-center" style="color: #000000 ; margin-left:50px ">Hello : ${sessionScope.userName}</span>
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
                    <h1>Sell Manager</h1>
                    <div class="row">
                        <div class="col-8">
                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-title">Order</h5>
                                        </div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-9 mt-2">

                                                    <a class="mx-2"
                                                       href="${pageContext.request.contextPath}/sell-manager/create-order">
                                                        <button class="btn btn-outline-success">Create Order</button>
                                                    </a>
                                                    <c:if test="${ empty idOrderChose}">
                                                        <a href="#">
                                                            <button disabled class="btn btn-outline-danger">Cancel
                                                                Order
                                                            </button>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${not empty idOrderChose }">
                                                        <a href="${pageContext.request.contextPath}/sell-manager/cancel-order/${idOrderChose}">
                                                            <button class="btn btn-outline-danger">Cancel Order</button>
                                                        </a>
                                                    </c:if>

                                                </div>
                                                <div class="col-3 mt-2">
                                                    <h6>
                                                        OrderChoose : <span>
                                                                        <strong>
                                                                            ${not empty idOrderChose ? idOrderChose : '0'}
                                                                        </strong>
                                                                      </span>
                                                    </h6>
                                                </div>
                                            </div>
                                            <table class="table mt-2">
                                                <thead>
                                                <tr class="item-row">
                                                    <th scope="col">#</th>
                                                    <th scope="col">Staff Name</th>
                                                    <th scope="col">Customer Name</th>
                                                    <th scope="col">Phone</th>
                                                    <th scope="col">Total Price</th>
                                                    <th scope="col">Date Buy</th>
                                                    <th scope="col">Status</th>
                                                    <th scope="col">Handle</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${empty billList}">
                                                    <tr>
                                                        <td colspan="8" class="text-center">No data Order</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${not empty billList}">
                                                    <c:forEach items="${billList}" var="item">
                                                        <tr>
                                                            <th scope="row">${item.id}</th>
                                                            <td>${staffNames[item.idStaff]}</td>
                                                            <td>${customerNames[item.idCustomer]}</td>
                                                            <td>${customerInfo.phone}</td>
                                                            <td>${item.total}</td>
                                                            <td>
                                                                <fmt:parseDate value="${item.dateBuy}"
                                                                               pattern="EEE MMM dd HH:mm:ss zzz yyyy"
                                                                               var="parsedDate"/>
                                                                <fmt:formatDate value="${parsedDate}"
                                                                                pattern="yyyy-MM-dd' 'HH:mm"
                                                                                var="formattedDateTime"/>
                                                                    ${formattedDateTime}
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${item.status eq 0}">
                                                                        <span style="color: chocolate;">Pending</span>
                                                                    </c:when>
                                                                    <c:when test="${item.status eq 1}">
                                                                        <span style="color: green;">Completed</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span style="color: red;">Cancel</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <a href="${pageContext.request.contextPath}/sell-manager/choose-oder/${item.id}">
                                                                    <button class="btn btn-warning">Chose</button>
                                                                </a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>


                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-5">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="row">
                                                <div class="col-6">
                                                    <h5 class="card-title">Cart</h5>
                                                </div>
                                                <div class="col-6">

                                                    <div class=" d-flex justify-content-end ">
                                                        <c:if test="${empty sessionScope.idOrderChose}">
                                                            <%--                                                            <a  href="#" > <button class="btn btn-danger" disabled >Clear All Cart</button></a>--%>
                                                        </c:if>
                                                        <c:if test="${not empty sessionScope.idOrderChose}">
                                                            <a href="${pageContext.request.contextPath}/sell-manager/clear-all"
                                                               class="btn btn-danger">Clear All Cart</a>
                                                        </c:if>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Code</th>
                                                    <th scope="col">Product</th>
                                                    <th scope="col">Color</th>
                                                    <th scope="col">Size</th>
                                                    <th scope="col">Price</th>
                                                    <th scope="col">Quantity</th>
                                                    <th scope="col">Total Money</th>
                                                    <th scope="col">Functions</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${empty listCart}">
                                                    <tr>
                                                        <td colspan="9" class="text-center">No data Cart</td>
                                                    </tr>
                                                </c:if>


                                                <c:if test="${not empty listCart}">

                                                    <c:forEach items="${listCart}" var="item">
                                                        <tr>
                                                            <th scope="row">${item.id}</th>
                                                            <c:forEach items="${item.productDetailList}" var="product">
                                                                <td>${product.code}</td>
                                                                <td>${productNames[product.idProduct]}</td>
                                                                <td>${sizeNames[product.idColor]}</td>
                                                                <td>${colorNames[product.idSize]}</td>
                                                                <td>${item.price}</td>
                                                            </c:forEach>
                                                            <td>${item.quantity}</td>
                                                            <td>${item.total}</td>
                                                            <td>
                                                                <a href="${pageContext.request.contextPath}/sell-manager/delete-cart/${item.id}">
                                                                    <button class="btn btn-danger">Delete</button>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>


                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-5">
                                <div class="col-12">
                                    <div>

                                        <div class="row">
                                            <div class="col-6">
                                                <form class="row">
                                                    <div class="col-8" style="margin-top: 23px">
                                                        <input placeholder="Search name product" type="text"
                                                               class="form-control" id="search"
                                                               name="nameProductSearch">

                                                    </div>
                                                    <div class="col-4" style="margin-top: 23px">
                                                        <button class="btn btn-secondary">Search</button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-6">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/sell-manager/filter">
                                                    <div class="row">
                                                        <div class="col-3">
                                                            <label>Product</label>
                                                            <select name="searchProduct" class="form-select"
                                                                    aria-label="Default select example">
                                                                <option value="" ${not empty sessionScope.selectedProduct ? '' : 'selected'}>
                                                                    All
                                                                </option>
                                                                <c:forEach items="${listProduct}" var="product">
                                                                    <option value="${product.id}" ${product.id == idProduct ? 'selected' : ''}>
                                                                            ${product.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-3">
                                                            <label>Size</label>
                                                            <select name="searchSize" class="form-select"
                                                                    aria-label="Default select example">
                                                                <option value="" ${not empty sessionScope.selectedSize ? '' : 'selected'}>
                                                                    All
                                                                </option>
                                                                <c:forEach items="${listSize}" var="size">
                                                                    <option value="${size.id}" ${size.id == idSize ? 'selected' : ''}>
                                                                            ${size.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-3">
                                                            <label>Color</label>
                                                            <select name="searchColor" class="form-select"
                                                                    aria-label="Default select example">
                                                                <option value="" ${not empty sessionScope.selectedColor ? '' : 'selected'}>
                                                                    All
                                                                </option>
                                                                <c:forEach items="${listColor}" var="color">
                                                                    <option value="${color.id}" ${color.id == idColor ? 'selected' : ''}>
                                                                            ${color.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-3" style="margin-top: 23px">
                                                            <button type="submit" class="btn btn-outline-dark">Filter
                                                            </button>
                                                        </div>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-title">Product</h5>
                                        </div>
                                        <div class="card-body">

                                            <%--                                            <!-- ModalProductDetail -->--%>
                                            <div class="modal fade" id="modalProduct" tabindex="-1"
                                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Product</h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="row">
                                                                <div class="col-12">
                                                                    <form action="${pageContext.request.contextPath}/sell-manager/add-to-cart"
                                                                          method="post">
                                                                        <div class="row">
                                                                            <div class="col-12 mt-2">
                                                                                <label for="code" class="form-label">Code</label>
                                                                                <input type="text" class="form-control"
                                                                                       name="code" readonly>
                                                                            </div>
                                                                            <div class="col-12 mt-2">
                                                                                <label for="code" class="form-label">Product</label>
                                                                                <input type="text" class="form-control"
                                                                                       name="product" disabled>
                                                                            </div>
                                                                            <div class="col-12 mt-2">
                                                                                <label for="code" class="form-label">Color</label>
                                                                                <input type="text" class="form-control"
                                                                                       name="color" disabled>
                                                                            </div>
                                                                            <div class="col-12 mt-2">
                                                                                <label for="code" class="form-label">Size</label>
                                                                                <input type="text" class="form-control"
                                                                                       name="size" disabled>
                                                                            </div>
                                                                            <div class="col-12 mt-2">
                                                                                <label for="code" class="form-label">Quantity</label>
                                                                                <input type="number"
                                                                                       class="form-control"
                                                                                       name="quantity">
                                                                            </div>
                                                                            <div class="col-12 mt-2">
                                                                                <label for="code" class="form-label">Price</label>
                                                                                <input type="number"
                                                                                       class="form-control"
                                                                                       name="price" disabled>
                                                                            </div>
                                                                            <div class="col-12 mt-2">
                                                                                <label for="code" class="form-label">Total
                                                                                    Money</label>
                                                                                <input type="number"
                                                                                       class="form-control"
                                                                                       name="totalMoney" readonly>
                                                                            </div>
                                                                            <div class="col-12 mt-3 d-flex justify-content-center">
                                                                                <button class="btn btn-success"
                                                                                        type="submit">Add to cart
                                                                                </button>
                                                                            </div>
                                                                        </div>
                                                                    </form>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <%--                                            <!-- ModalProductDetail -->--%>
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Code</th>
                                                    <th scope="col">Product</th>
                                                    <th scope="col">Color</th>
                                                    <th scope="col">Size</th>
                                                    <th scope="col">Quantity</th>
                                                    <th scope="col">Price</th>
                                                    <th scope="col">Status</th>
                                                    <th scope="col">Functions</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${empty listProductDetail}">
                                                    <tr>
                                                        <td colspan="9" class="text-center">No data Product</td>
                                                    </tr>
                                                </c:if>

                                                <c:if test="${not empty listProductDetail}">
                                                    <c:forEach var="item" items="${listProductDetail}">
                                                        <tr>
                                                            <th scope="row">${item.id}</th>
                                                            <td>${item.code}</td>
                                                            <td>${productNames[item.idProduct]}</td>
                                                            <td>${sizeNames[item.idColor]}</td>
                                                            <td>${colorNames[item.idSize]}</td>
                                                            <td>${item.quantity}</td>
                                                            <td>${item.price}</td>
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
                                                                <c:if test="${empty sessionScope.idOrderChose}">
                                                                    <button disabled type="button"
                                                                            class="btn btn-primary"
                                                                            data-bs-toggle="#"
                                                                            data-bs-target="#modalProduct"
                                                                            data-id="${item.id}"
                                                                            data-code="${item.code}"
                                                                            data-product="${productNames[item.idProduct]}"
                                                                            data-color="${sizeNames[item.idColor]}"
                                                                            data-size="${colorNames[item.idSize]}"
                                                                            data-quantity="${item.quantity}"
                                                                            data-price="${item.price}"
                                                                            data-status="${item.status}">
                                                                        Choose
                                                                    </button>
                                                                </c:if>
                                                                <c:if test="${not empty sessionScope.idOrderChose}">
                                                                    <button type="button" class="btn btn-primary"
                                                                            data-bs-toggle="modal"
                                                                            data-bs-target="#modalProduct"
                                                                            data-id="${item.id}"
                                                                            data-code="${item.code}"
                                                                            data-product="${productNames[item.idProduct]}"
                                                                            data-color="${sizeNames[item.idColor]}"
                                                                            data-size="${colorNames[item.idSize]}"
                                                                            data-quantity="${item.quantity}"
                                                                            data-price="${item.price}"
                                                                            data-status="${item.status}">
                                                                        Choose
                                                                    </button>
                                                                </c:if>

                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="card-title">Order - Payment</h5>
                                </div>
                                <div class="card-body">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="card-title">Customer information</h5>
                                        </div>
                                        <div class="card-body">
                                            <%-- modal--%>
                                            <div class="modal fade" id="changeModal" tabindex="-1"
                                                 aria-labelledby="changeModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="changeModalLabel">Change
                                                                Information</h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <!-- Nav tabs -->
                                                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                                                <li class="nav-item" role="presentation">
                                                                    <button class="nav-link active" id="tab1-tab"
                                                                            data-bs-toggle="tab" data-bs-target="#tab1"
                                                                            type="button" role="tab"
                                                                            aria-controls="tab1" aria-selected="true">
                                                                        Customer List
                                                                    </button>
                                                                </li>
                                                                <li class="nav-item" role="presentation">
                                                                    <button class="nav-link" id="tab2-tab"
                                                                            data-bs-toggle="tab" data-bs-target="#tab2"
                                                                            type="button" role="tab"
                                                                            aria-controls="tab2" aria-selected="false">
                                                                        Add New Customer
                                                                    </button>
                                                                </li>
                                                            </ul>

                                                            <!-- Tab panes -->
                                                            <div class="tab-content" id="myTabContent">
                                                                <div class="tab-pane fade show active" id="tab1"
                                                                     role="tabpanel" aria-labelledby="tab1-tab">
                                                                    <table class="table">
                                                                        <thead>
                                                                        <tr>
                                                                            <th scope="col">#</th>
                                                                            <th scope="col">Name</th>
                                                                            <th scope="col">Phone</th>
                                                                            <th scope="col">Functions</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <c:forEach items="${listCustomer}" var="item">
                                                                            <tr>
                                                                                <th scope="row">${item.id}</th>
                                                                                <td>${item.name}</td>
                                                                                <td>${item.phone}</td>
                                                                                <td>
                                                                                    <a href="${pageContext.request.contextPath}/sell-manager/choose-customer/${item.id}">
                                                                                        <button class="btn btn-success">
                                                                                            Choose
                                                                                        </button>
                                                                                    </a></td>
                                                                            </tr>
                                                                        </c:forEach>


                                                                        </tbody>
                                                                    </table>

                                                                </div>
                                                                <div class="tab-pane fade" id="tab2" role="tabpanel"
                                                                     aria-labelledby="tab2-tab">
                                                                    <!-- Content for Tab 2 -->
                                                                    <div class="mb-3 mt-2">
                                                                        <form action="${pageContext.request.contextPath}/sell-manager/add-customer"
                                                                              method="post">
                                                                            <div class="mb-3">
                                                                                <label for="code" class="form-label">Code
                                                                                    Customer</label>
                                                                                <input type="text" class="form-control"
                                                                                       id="code"
                                                                                       value="${customer.code}"
                                                                                       name="code">
                                                                                <c:if test="${not empty errors}">
                                                                                    <span id="code-error"
                                                                                          class="text-danger">${errors['code']}</span>
                                                                                </c:if>

                                                                            </div>

                                                                            <div class="mb-3">
                                                                                <label for="name" class="form-label">Name
                                                                                    Customer</label>
                                                                                <input type="text" class="form-control"
                                                                                       id="name"
                                                                                       value="${customer.name}"
                                                                                       name="name">
                                                                                <c:if test="${not empty errors}">
                                                                                    <span id="code-error"
                                                                                          class="text-danger">${errors['name']}</span>
                                                                                </c:if>
                                                                            </div>

                                                                            <div class="mb-3">
                                                                                <label for="phone" class="form-label">Phone</label>
                                                                                <input type="text" class="form-control"
                                                                                       id="phone"
                                                                                       value="${customer.phone}"
                                                                                       name="phone">
                                                                                <c:if test="${not empty errors}">
                                                                                    <span id="code-error"
                                                                                          class="text-danger">${errors['phone']}</span>
                                                                                </c:if>
                                                                            </div>

                                                                            <div class="mb-3">
                                                                                <select class="form-select"
                                                                                        name="status"
                                                                                        aria-label="Default select example">
                                                                                    <option value="1"
                                                                                            <c:if test="${customer.status eq 1}">selected</c:if>>
                                                                                        Active
                                                                                    </option>
                                                                                </select>
                                                                                <span id="status-error"
                                                                                      class="text-danger"></span>
                                                                            </div>

                                                                            <button type="submit"
                                                                                    class="btn btn-outline-success">Add
                                                                            </button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary"
                                                                    data-bs-dismiss="modal">Close
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <%-- modal--%>

                                            <form>
                                                <div class="row">
                                                    <div class="col-12">
                                                        <label class="form-label">Phone</label>
                                                        <input value="${customerInfo.phone}" type="text"
                                                               class="form-control" name="phone"
                                                               disabled>
                                                    </div>
                                                    <div class="col-12">
                                                        <label for="fullName" class="form-label">FullName</label>
                                                        <input value="${customerInfo.name}" type="text"
                                                               class="form-control" id="fullName"
                                                               name="fullName" disabled>
                                                    </div>
                                                    <div class="text-center mt-2"><span
                                                            class="fw-bold text-danger">${errorCustomer}</span></div>
                                                    <div class="col-12 mt-2 d-flex justify-content-center">

                                                        <button class="btn btn-primary" type="button"
                                                                data-bs-toggle="modal" data-bs-target="#changeModal">
                                                            Change
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <form action="${pageContext.request.contextPath}/sell-manager/pay" method="post">
                                        <div class="row">
                                            <div class="col-12 mt-2">
                                                <label>Staff Name</label>
                                                <input disabled value="${staffNames[bill.idStaff]}" type="text"
                                                       class="form-control" name="staffName">
                                            </div>
                                            <div class="col-12 mt-2">
                                                <label>Date Buy</label>
                                                <fmt:formatDate value="${bill.dateBuy}" pattern="yyyy-MM-dd"
                                                                var="formattedDate"/>
                                                <fmt:formatDate value="${bill.dateBuy}" pattern="HH:mm"
                                                                var="formattedTime"/>
                                                <input disabled value="${formattedDate}T${formattedTime}"
                                                       type="datetime-local" class="form-control" id="dateBuy"
                                                       name="dateBuy">
                                            </div>
                                            <div class="col-12 mt-2">
                                                <label>Total Money</label>
                                                <input id="total" disabled value="${bill.total}" type="number"
                                                       class="form-control" name="totalMoney">
                                            </div>
                                            <div class="col-12 mt-2">
                                                <label>Cash</label>
                                                 <c:if test="${empty sessionScope.idOrderChose}">
                                                     <input disabled min="1" max="99999999" id="cash" type="number" class="form-control" name="totalMoney">
                                                 </c:if>

                                                <c:if test="${ not empty sessionScope.idOrderChose}">
                                                    <input  min="1" max="99999999" id="cash" type="number" class="form-control" name="totalMoney">
                                                </c:if>
                                                <span id="error-cash" style="color: red" class=" text-center fw-bold"></span>
                                            </div>
                                            <div class="col-12 mt-2">
                                                <label>Refund</label>
                                                <input id="refund" disabled type="number" class="form-control"
                                                       name="totalMoney">
                                            </div>

                                        </div>
                                        <div class="col-12 mt-2 d-flex justify-content-center">
                                            <button type="submit" class="btn btn-success">Pay</button>
                                        </div>
                                    </form>
                                </div>
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
<script>
    const hamBurger = document.querySelector(".toggle-btn");

    hamBurger.addEventListener("click", function () {
        document.querySelector("#sidebar").classList.toggle("expand");
    });


</script>

<script src="../../js/scopeFilter.js"></script>


<script>
    document.addEventListener('DOMContentLoaded', function () {
        var modalProduct = document.getElementById('modalProduct');
        modalProduct.addEventListener('show.bs.modal', function (event) {
            let button = event.relatedTarget;

            let id = button.getAttribute('data-id');
            let code = button.getAttribute('data-code');
            let product = button.getAttribute('data-product');
            let color = button.getAttribute('data-color');
            let size = button.getAttribute('data-size');
            let price = button.getAttribute('data-price');
            let quantityInput = modalProduct.querySelector('input[name="quantity"]');
            let priceModal = modalProduct.querySelector('input[name="price"][disabled]');
            let totalMoneyInput = modalProduct.querySelector('input[name="totalMoney"][readonly]');

            modalProduct.querySelector('input[name="code"]').value = code;
            modalProduct.querySelector('input[name="product"][disabled]').value = product;
            modalProduct.querySelector('input[name="color"][disabled]').value = color;
            modalProduct.querySelector('input[name="size"][disabled]').value = size;
            quantityInput.value = 1;
            priceModal.value = price;
            totalMoneyInput.value = (quantityInput.value * priceModal.value).toFixed(1)
            quantityInput.addEventListener('input', function () {
                totalMoneyInput.value = (quantityInput.value * priceModal.value).toFixed(1);
            });

            let modalTitle = modalProduct.querySelector('.modal-title');
            modalTitle.textContent = `Product ${product}`;
        });
    });
</script>

<script>
    let cash = document.getElementById('cash');
    let total = document.getElementById('total');
    let refund = document.getElementById('refund');
    let errorCash = document.getElementById('error-cash');

    cash.addEventListener('blur', function () {
        if (cash.value < 0) {
            errorCash.textContent = 'Cash must be greater than total money';
        } else {
            errorCash.textContent = '';
        }
    });

    cash.addEventListener('blur', function () {
        refund.value = cash.value - total.value;
    });
</script>


</body>
</html>