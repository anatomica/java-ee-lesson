<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Товар</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
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
                <c:url value="${requestScope.action}" var="productPostUrl"/>
                <form action="${productPostUrl}" method="post">
                    <input type="hidden" value="${product.id}" id="id" name="id">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" value="${product.name}" class="form-control" id="name" name="name" placeholder="Enter name">
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" value="${product.description}" class="form-control" id="description" name="description" placeholder="Enter description">
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" value="${product.price}" class="form-control" id="price" name="price" placeholder="Enter price">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>