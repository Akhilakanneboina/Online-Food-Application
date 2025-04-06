package servlets;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.implementation.UserImplementation;
import com.model.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the form
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = "USER"; // Default role

        // Create a user object
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);
        user.setCreateDate(Date.valueOf(LocalDate.now()));
        user.setLastLoginDate(null); // Initially null

        // Call DAO to save user
        UserImplementation userDAO = new UserImplementation();
        boolean isRegistered = userDAO.addUser(user) != null;

        if (isRegistered) {
            response.sendRedirect("HomeServlet"); // Redirect to home after successful registration
        } else {
            request.setAttribute("error", "Registration failed. Try again!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
