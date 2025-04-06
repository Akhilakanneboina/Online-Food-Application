<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Food App</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

    <header>
        <h1>Food App</h1>
        <input type="text" placeholder="Search for restaurants...">
       
    </header>

    <section class="restaurant-list">
        <% 
            List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (errorMessage != null) { %>
                <p style="color:red; font-weight:bold;"><%= errorMessage %></p>
        <% } else if (restaurantList != null && !restaurantList.isEmpty()) {
                for (Restaurant restaurant : restaurantList) { 
        %>
                    <div class="restaurant-card">
                        <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>">
                        <h2><%= restaurant.getName() %></h2>
                        <p><strong>Cuisine:</strong> <%= restaurant.getCuisineType() %></p>
                        <p><strong>Delivery Time:</strong> <%= restaurant.getDeliveryTime() %> mins</p>
                        <p><strong>Address:</strong> <%= restaurant.getAddress() %></p>
                        <p><strong>Rating:</strong> <%= restaurant.getRating() %> ⭐</p>
                        <button onclick="location.href='<%= request.getContextPath() %>/MenuServlet?restaurantId=<%= restaurant.getRestaurantId() %>'">View Menu</button>
                        
                    </div>
        <%      }
            } else { %>
                <p>No restaurants available.</p>
        <% } %>
        

        
    </section>

</body>
</html>
