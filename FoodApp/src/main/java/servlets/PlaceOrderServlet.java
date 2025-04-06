package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.implementation.OrderImplementation;
import com.implementation.OrderItemsImplementation;
import com.interfaces.OrderDao;
import com.interfaces.OrderItemsDao;
import com.interfaces.RestaurantDao;
import com.implementation.RestaurantImplementation;
import com.model.*;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        if (session == null) {
            System.out.println("Session is null! Redirecting to login.jsp.");
            response.sendRedirect("login.jsp?error=sessionExpired");
            return;
        }

        // Retrieve user
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            System.out.println("User not found in session! Redirecting to login.jsp.");
            response.sendRedirect("login.jsp?error=sessionExpired");
            return;
        }

        System.out.println("User found: " + user.getUserName() + " | Processing order...");

        // Retrieve cart items
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            System.out.println("Cart is empty! Redirecting back to cart.jsp.");
            response.sendRedirect("cart.jsp?error=emptyCart");
            return;
        }

        System.out.println("Cart contains " + cart.size() + " items. Proceeding with order creation...");

        // Get restaurant ID from request
        String restaurantIdStr = request.getParameter("restaurantId");
        if (restaurantIdStr == null || restaurantIdStr.isEmpty()) {
            System.out.println("Error: Restaurant ID missing! Redirecting to cart.jsp.");
            response.sendRedirect("cart.jsp?error=restaurantIdMissing");
            return;
        }

        Long restaurantId = Long.parseLong(restaurantIdStr);
        RestaurantDao restaurantDao = new RestaurantImplementation();
        Restaurant restaurant = restaurantDao.getRestaurant(restaurantId);

        if (restaurant == null) {
            System.out.println("Error: Restaurant not found in database! Redirecting to cart.jsp.");
            response.sendRedirect("cart.jsp?error=restaurantNotFound");
            return;
        }

        // Get payment mode from request
        String paymentMode = request.getParameter("paymentMode");
        if (paymentMode == null || paymentMode.isEmpty()) {
            paymentMode = "Cash on Delivery";
        }
        System.out.println("Checking restaurant details for cart items...");
        for (CartItem cartItem : cart) {
            if (cartItem.getMenu() == null) {
                System.out.println("Error: Menu item is null for cart item!");
            } else {
                System.out.println("Menu Item: " + cartItem.getMenu().getItemName());
                System.out.println("Checking restaurant...");
                if (cartItem.getMenu().getRestaurant() == null) {
                    System.out.println("Error: Restaurant is null for menu item " + cartItem.getMenu().getItemName());
                } else {
                    System.out.println("Restaurant Found: " + cartItem.getMenu().getRestaurant().getName());
                }
            }
        }


        // Create Order
        OrderImplementation orderDAO = new OrderImplementation();
        Orders order = new Orders();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(cart.stream().mapToDouble(CartItem::getTotalPrice).sum());
        order.setStatus("Processing");
        order.setPaymentMode(paymentMode);

        Orders savedOrder = orderDAO.addOrder(order);

        if (savedOrder == null || savedOrder.getOrderId() == null) {
            System.out.println("Error: Order not saved in database. Redirecting back to cart.jsp.");
            response.sendRedirect("cart.jsp?error=orderNotSaved");
            return;
        }

        System.out.println("Order saved with Order ID: " + savedOrder.getOrderId());

        // Save Order Items
        OrderItemsImplementation orderItemsDAO = new OrderItemsImplementation();
        for (CartItem cartItem : cart) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(savedOrder);
            orderItem.setMenuItem(cartItem.getMenu());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            orderItemsDAO.addOrderItem(orderItem);
        }

        // Clear cart after order is placed
        session.removeAttribute("cart");

        System.out.println("Redirecting to OrderConfirmationServlet with orderId: " + savedOrder.getOrderId());

        // Redirect to confirmation servlet
        response.sendRedirect("OrderConfirmationServlet?orderId=" + savedOrder.getOrderId());
    }
}
