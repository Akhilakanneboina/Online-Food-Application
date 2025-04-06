<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu</title>
    <link rel="stylesheet" href="menu.css">
</head>
<body>
    <header>
        <h1>Our Menu</h1>
       
    
    <div class="header-buttons">
        <a href="cart.jsp" class="button-link">View Cart</a>
        <a href="HomeServlet" class="button-link">Back To Home</a>
    </div>
</header>
        
   
    
    <main>
        <section class="menu">
            
            <ul>
                <%
                    List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
                    if (menuList != null && !menuList.isEmpty()) {
                        for (Menu item : menuList) {
                %>
                <li class="menu-item">
                    <img src="<%= request.getContextPath() %>/<%= item.getImagePath() %>" alt="<%= item.getItemName() %>" width="150" height="150">
                    <h2><%= item.getItemName() %></h2>
                    <p><%= item.getDescription() %></p>
                    <p>Price: $<%= item.getPrice() %></p>

                    <!-- ✅ Ensure unique menu items are added -->
                    <form action="AddToCartServlet" method="post">
                    
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
                        <input type="hidden" name="price" value="<%= item.getPrice() %>">
                        <button class="add-to-cart-btn">Add to Cart</button>
                        
                    </form>
                </li>
                <%
                        }
                    } else {
                %>
                <p>No menu items available.</p>
                <%
                    }
                %>
            </ul>
        </section>
    </main>

    <br>
    
</body>
</html>
