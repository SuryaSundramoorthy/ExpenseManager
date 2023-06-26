import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Base64;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.database.connection.*;
import java.sql.*;
@WebServlet("/EncryptionServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    	 try (Connection connection = DatabaseConnection.GetConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
                statement.setString(1, request.getParameter("username"));
                statement.setString(2, request.getParameter("password"));
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException(e);
            } catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	String password = "surya";
        String encryptedPassword = hashPassword(password);
        try {
			response.getWriter().write("\n Encrypted Password: " + encryptedPassword);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }}

