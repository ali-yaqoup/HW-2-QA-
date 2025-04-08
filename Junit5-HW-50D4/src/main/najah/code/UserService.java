package main.najah.code;

public class UserService {
    public boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }
}
