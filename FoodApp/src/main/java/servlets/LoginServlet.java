package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.implementation.UserImplementation;
import com.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Call DAO to get user
        UserImplementation userDAO = new UserImplementation();
        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            // Create session and store user
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            
            response.sendRedirect("HomeServlet"); // Redirect to home
        } else {
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
