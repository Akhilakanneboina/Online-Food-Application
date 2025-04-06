package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.implementation.OrderImplementation;
import com.interfaces.OrderDao;
import com.model.Orders;

@WebServlet("/OrderConfirmationServlet")
public class OrderConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp?error=sessionExpired");
            return;
        }

        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam == null || orderIdParam.isEmpty()) {
        	 orderIdParam = session.getAttribute("orderId") != null ? session.getAttribute("orderId").toString() : null;
        }

        if (orderIdParam == null || orderIdParam.equals("null")) {
            System.out.println("Error: Order ID is missing. Redirecting to home page.");
            response.sendRedirect("home.jsp?error=missingOrderId");
            return;
        }

        try {
            Long orderId = Long.parseLong(orderIdParam);
            System.out.println("Received orderId in OrderConfirmationServlet: " + orderId);

            OrderDao orderDAO = new OrderImplementation();
            Orders order = orderDAO.getOrder(orderId);

            if (order == null) {
                request.setAttribute("errorMessage", "Order not found.");
                request.getRequestDispatcher("ordercon.jsp").forward(request, response);
                return;
            }

            request.setAttribute("order", order);
            System.out.println("Order found! Forwarding to ordercon.jsp.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("ordercon.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid order ID format. Redirecting to home.");
            response.sendRedirect("home.jsp?error=invalidOrderId");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
