package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.implementation.RestaurantImplementation;
import com.model.Restaurant;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private RestaurantImplementation restaurantService = new RestaurantImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); // ✅ Get session
        
        try {
            // Fetch list of restaurants
            List<Restaurant> restaurantList = restaurantService.getAllRestaurants();

            

            if (restaurantList == null || restaurantList.isEmpty()) {
                request.setAttribute("errorMessage", "No restaurants found.");
            } else {
                request.setAttribute("restaurantList", restaurantList);
                
                // ✅ Store the first restaurantId in session if it's not set
                if (session.getAttribute("restaurantId") == null && !restaurantList.isEmpty()) {
                    session.setAttribute("restaurantId", restaurantList.get(0).getRestaurantId());
                }
            }

            // Forward request to home.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.err.println("Error fetching restaurants: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching restaurants.");
        }
    }
}
