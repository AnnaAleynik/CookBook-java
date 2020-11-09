
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout>

    <hr>

    <div class="container mt-md-4 bg-white">
        <c:forEach var="item" items="${recipes}">
            <div class="card col-10 mb-2">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${item.getTitle()}"/></h5>
                    <a href="<c:url value="/recipe/${item.getId()}"/>" class="card-link">Перейти</a>
                </div>
            </div>
        </c:forEach>
    </div>
</t:mainLayout>
