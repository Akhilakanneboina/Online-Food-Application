package service;

import com.model.CartItem;
import javax.servlet.http.HttpSession;

import java.util.Iterator;
import java.util.List;

public class CartServices {
    public void updateCartQuantity(HttpSession session, Long menuId, String action) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

        if (cartItems != null) {
            for (CartItem item : cartItems) {
                if (item.getMenu().getMenuId().equals(menuId)) {
                    if ("increase".equals(action)) {
                        item.setQuantity(item.getQuantity() + 1);
                    } else if ("decrease".equals(action) && item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1);
                    }
                    break;
                }
            }
            session.setAttribute("cartItems", cartItems); // ✅ Update session
        }
    }
    

	public void removeFromCart(HttpSession session, Long menuId) {
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

        if (cartItems != null) {
            Iterator<CartItem> iterator = cartItems.iterator();
            while (iterator.hasNext()) {
                CartItem item = iterator.next();
                if (item.getMenu().getMenuId().equals(menuId)) {
                    iterator.remove(); // ✅ Remove the item safely
                    break;
                }
            }
            session.setAttribute("cartItems", cartItems);
		
        }
	}
    }

