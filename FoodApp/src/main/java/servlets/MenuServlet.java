package servlets;

import com.implementation.MenuImplementation;
import com.model.Menu;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the restaurant ID from the query string (e.g., menu?restaurantId=1)
    	String restaurantIdStr = request.getParameter("restaurantId");

    	// ✅ Try getting restaurantId from session if not in request
    	if (restaurantIdStr == null || restaurantIdStr.isEmpty()) {
    	    Object sessionRestaurantId = request.getSession().getAttribute("restaurantId");
    	    if (sessionRestaurantId != null) {
    	        restaurantIdStr = sessionRestaurantId.toString();
    	    } else {
    	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required.");
    	        return;
    	    }
    	}

    	Long restaurantId;
    	try {
    	    restaurantId = Long.valueOf(restaurantIdStr);
    	} catch (NumberFormatException e) {
    	    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID format.");
    	    return;
    	}

    	// ✅ Store restaurantId in session for future requests
    	request.getSession().setAttribute("restaurantId", restaurantId);

    	// Fetch menu items
    	MenuImplementation menuImpl = new MenuImplementation();
    	List<Menu> menuList = menuImpl.getMenuByRestaurant(restaurantId);

    	// Set attributes and forward
    	request.setAttribute("menuList", menuList);
    	request.getRequestDispatcher("/menu.jsp").forward(request, response);

}
}
