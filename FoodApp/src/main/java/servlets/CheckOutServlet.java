package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp?error=sessionExpired");
            return;
        }

        // ✅ Ensure Order ID is set before checkout
        Long orderId = (Long) session.getAttribute("orderId");
        if (orderId == null) {
            orderId = new Random().nextLong(1000000); // Generate mock order ID
            session.setAttribute("orderId", orderId);
        }

        System.out.println("Order ID set in session: " + orderId);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Received GET request for checkout. Redirecting to POST handler.");
        doPost(request, response);
    }
}
