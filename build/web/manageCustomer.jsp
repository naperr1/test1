<%-- 
    Document   : manageCustomer
    Created on : Nov 19, 2023, 1:29:44 AM
    Author     : Fattt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"
        integrity="sha512-NhSC1YmyruXifcj/KFRWoC561YpHpc5Jtzgvbuzx5VozKpWvQ+4nXhPdFgmx8xqexRcpAglTj9sIBWINXa8x5w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    <link rel="stylesheet" href="assets/css/adminDashboard.css">
    <link rel="stylesheet" href="assets/css/manageUser.css">

</head>

<body>

    <!-- header -->
    <div class="header">
        <div class="header__logo">
            <img src="assets/image/logo.png" alt="">
            <span>Furnish</span>
        </div>
        <div class="header__nav">
            <ul class="header__icon">
                <li>
                    <i class="fa-solid fa-magnifying-glass"></i>
                </li>
                <li>
                    <i class="fa-regular fa-bell"></i>
                </li>
                <li>
                    <i class="fa-regular fa-envelope"></i>
                </li>
            </ul>
            <div class="header__manager">
                <div class="header__avatar">
                    <img src="assets/image/ava1.jpg" alt="">
                </div>
                <div class="header__info">
                    <div class="header__name">
                        Nguyen An Phat
                    </div>
                    <span>Manager</span>
                </div>
            </div>
        </div>
    </div>
    <!-- End header -->

    <!-- main -->
    <div class="main">
        <div class="main__sidebar">
            <ul>
                <li>
                    <a href="admin.html">
                        <span><i class='bx bx-home'></i></span>
                        <h3>Dashboard</h3>
                    </a>
                </li>
                <li>
                    <a href="productManager">
                        <span><i class='bx bx-shopping-bag'></i></span>
                        <h3>Product</h3>
                    </a>
                </li>
                <li>
                    <a href="categoryManager">
                        <span><i class='bx bx-category'></i></span>
                        <h3>Category</h3>
                    </a>
                </li>
                <li>
                    <a href="customerManager">
                        <span><i class='bx bx-user'></i></span>
                        <h3>Customers</h3>
                    </a>
                </li>
                <li>
                    <a href="manageOrders.html">
                        <span><i class='bx bx-cart'></i></span>
                        <h3>Orders</h3>
                    </a>
                </li>
            </ul>
            <div class="main__logout">
                <a href="#">
                    <span>
                        <i class="fa-solid fa-arrow-right-to-bracket"></i>
                    </span>
                    <h3>Logout</h3>
                </a>
            </div>
        </div>
        <div class="user">
            <h1>Manage Users</h1>
            <div class="user__function">
                <div class="user__search">
                    <input type="text" placeholder="Search user..." id="userSearchInput" oninput="searchUsers()">

                    <a href="#">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </a>
                </div>
                <div class="user__add">
                    <a href="AddCustomer.jsp">Add User</a>
                </div>
            </div>
            <div class="user__table">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>
                    
                    <c:forEach items="${requestScope.data}" var="c">
                        <tr>
                        <td class="user__id">${c.id}</td>
                        <td class="user__name">${c.username}</td>
                        <td class="user__email">${c.email}</td>
                        <td class="user__phone">${c.phone}</td>
                        <td class="user__isAdmin">${c.isAdmin}</td>
                        <td class="user__action">
                            <ul>
                                <li>
                                    <a href="updateCustomer?id=${c.id}">
                                        <i class="fa-solid fa-pencil"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="deleteCustomer?id=${c.id}">
                                        <i class="fa-regular fa-trash-can"></i>
                                    </a>
                                </li>
                            </ul>
                        </td>
                    </tr>
                    </c:forEach>
                    
                </table>
            </div>
            <div class="user__pagination">
                <ul>
                    <li>
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">3</a>
                    </li>
                    <li>
                        <a href="#">4</a>
                    </li>
                    <li>
                        <a href="#">5</a>
                    </li>
                    <li>
                        <a href="#">6</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- End main -->

</body>
<script>
    function searchUsers() {
        var searchInput = document.getElementById('userSearchInput').value.toLowerCase();

        var rows = document.querySelectorAll('.user__table table tr');
        for (var i = 1; i < rows.length; i++) { 
            var username = rows[i].querySelector('.user__name').innerText.toLowerCase();
            var email = rows[i].querySelector('.user__email').innerText.toLowerCase();
            var phone = rows[i].querySelector('.user__phone').innerText.toLowerCase();
            var isAdmin = rows[i].querySelector('.user__isAdmin').innerText.toLowerCase();

            if (username.includes(searchInput) || email.includes(searchInput) || phone.includes(searchInput) || isAdmin.includes(searchInput)) {
                rows[i].style.display = '';
            } else {
                rows[i].style.display = 'none';
            }
        }
    }
</script>

</html>
