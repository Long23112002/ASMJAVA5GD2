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