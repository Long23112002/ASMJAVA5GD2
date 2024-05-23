<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<ul class="sidebar-nav">
    <li class="sidebar-item">
        <a href="${pageContext.request.contextPath}/sell-manager/index" class="sidebar-link">
            <i class="fa-solid fa-cart-shopping"></i>
            <span>Manager Sell</span>
        </a>
    </li>
    <li class="sidebar-item">
        <a href="${pageContext.request.contextPath}/product/index" class="sidebar-link">
            <i class="fa-solid fa-shirt"></i>
            <span>Manager Product</span>
        </a>
    </li>
    <li class="sidebar-item">
        <a href="${pageContext.request.contextPath}/color/index" class="sidebar-link">
            <i class="fa-solid fa-palette"></i>
            <span>Manager Color</span>
        </a>
    </li>
    <li class="sidebar-item">
        <a href="${pageContext.request.contextPath}/size/index" class="sidebar-link">
            <i class="fa-solid fa-ruler"></i>
            <span>Manager Size</span>
        </a>
    </li>
    <li class="sidebar-item">
        <a href="${pageContext.request.contextPath}/customer/index" class="sidebar-link">
            <i class="fa-solid fa-bars-progress"></i>
            <span>Manager Customer</span>

        </a>
    </li>
    <li class="sidebar-item">
        <a href="${pageContext.request.contextPath}/staff/index" class="sidebar-link">
            <i class="fa-solid fa-person"></i>
            <span>Manager Staff</span>
        </a>
    </li>
    <li class="sidebar-item">
        <a href="${pageContext.request.contextPath}/product-detail/index" class="sidebar-link">
            <i class="fa-solid fa-circle-info"></i>
            <span>Manager Product Detail</span>
        </a>
    </li>

    <security:authorize access="hasRole('ADMIN')">
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/bill/index" class="sidebar-link">
                <i class="fa-solid fa-money-bill"></i>
                <span>Manager Order</span>
            </a>
        </li>
    </security:authorize>

    <security:authorize access="hasRole('ADMIN')">
        <li class="sidebar-item">
            <a href="${pageContext.request.contextPath}/bill-detail/index" class="sidebar-link">
                <i class="far fa-money-bill-alt"></i>
                <span>Manager Order Detail</span>
            </a>
        </li>
    </security:authorize>



</ul>

<a href="${pageContext.request.contextPath}/auth/logout" class="sidebar-link">
    <i class="lni lni-exit"></i>
    <span>Logout</span>
</a>