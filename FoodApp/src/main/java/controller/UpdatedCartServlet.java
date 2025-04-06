package controller;



	import service.CartServices;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	import java.io.IOException;

	@WebServlet("/UpdateCart")
	public class UpdatedCartServlet extends HttpServlet {
	    private CartServices cartService = new CartServices();

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session = request.getSession();
	        Long menuId = Long.parseLong(request.getParameter("menuId"));
	        String action = request.getParameter("action"); // "increase" or "decrease"

	        cartService.updateCartQuantity(session, menuId, action);

	        response.sendRedirect("cart.jsp"); // Refresh cart page
	    }
	}



