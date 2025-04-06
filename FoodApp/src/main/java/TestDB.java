import com.implementation.UserImplementation;
import com.model.User;

public class TestDB {
    public static void main(String[] args) {
        UserImplementation userDao = new UserImplementation();
        User newUser = new User(null, "John Doe", "password123", "john@example.com", "9876543210", "123 Main St", "Customer");
        userDao.addUser(newUser);

        System.out.println("User added successfully!");
    }
}
