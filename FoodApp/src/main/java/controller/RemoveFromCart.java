package controller;

import service.CartServices;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.model.CartItem;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long menuId = Long.parseLong(request.getParameter("menuId"));

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(item -> item.getMenu().getMenuId().equals(menuId));
            session.setAttribute("cart", cart);
        }

        response.sendRedirect("cart.jsp");
    }
}
