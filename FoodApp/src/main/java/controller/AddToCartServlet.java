package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.CartItem;
import com.model.Menu;
import com.implementation.MenuImplementation;
import com.interfaces.MenuDao;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Get menuId from request
        Long menuId = Long.parseLong(request.getParameter("menuId"));
        
        // Retrieve menu item from database
        MenuDao menuDAO = new MenuImplementation();
        Menu menuItem = menuDAO.getMenu(menuId);
        
        if (menuItem == null) {
            response.sendRedirect("menu.jsp?error=ItemNotFound");
            return;
        }

        // Retrieve cart from session or initialize it
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Check if the item is already in the cart
        boolean itemExists = false;
        for (CartItem item : cart) {
            if (item.getMenu().getMenuId().equals(menuId)) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        // If item is not in cart, add new CartItem
        if (!itemExists) {
            cart.add(new CartItem(menuItem, 1)); // Constructor takes menu and quantity
        }

        // Save updated cart in session
        session.setAttribute("cart", cart);
        
        response.sendRedirect("cart.jsp");
    }
}
