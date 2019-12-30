<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <div class="header">
        <a href="main">Главная</a>
        <a href="catalog">Каталог</a>
        <a href="product">Товар</a>
        <a href="order">Заказ</a>
        <a href="cart">Корзина</a>
    </div>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <link rel="stylesheet" href="style.css">
    <title>EShop application</title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<div class="content">
    <div class="container">
        <div class="row py-2">
            <div class="col-12">
                <c:url value="/create" var="productCreateUrl"/>
                <a class="btn btn-primary" href="${productCreateUrl}">Add Product</a>
            </div>
            <div class="col-12">
                <table class="table table-bordered my-2">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Название</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${requestScope.products}">
                        <tr>
                            <th scope="row"><c:out value="${product.id}"/></th>
                            <td><c:out value="${product.name}"/></td>
                            <td><c:out value="${product.description}"/></td>
                            <td><c:out value="${product.price}"/></td>
                            <td>
                                <c:url value="/edit" var="productEditUrl">
                                    <c:param name="id" value="${product.id}"/>
                                </c:url>
                                <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                                <c:url value="/delete" var="productDeleteUrl">
                                    <c:param name="id" value="${product.id}"/>
                                </c:url>
                                <a class="btn btn-danger" href="${productDeleteUrl}"><i class="far fa-trash-alt"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

<div class="header">
    Copyright &copy; 2019 Максим Фомин
</div>

</html>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <title>Товары</title>--%>
<%--    <link rel="stylesheet" href="style.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="content">--%>
<%--    <h1>Товары магазина</h1>--%>
<%--    <div class="center">--%>
<%--        <div class="box_text">--%>
<%--            <p>Вот именно тут</p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>