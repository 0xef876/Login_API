package login_api.login_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {
    @Value("${db.url}")
    private String url;
    @Value("${db.server_id}")
    private String server_id;
    @Value("${db.server_pw}")
    private String server_pw;
    
    @GetMapping("/login")
    public User login(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String password) {
        User user = new User();
        user.login_check(username, password,url,server_id,server_pw);
        
        return user;
    }
    @PostMapping("/signup")
    public Signup signup(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String password, @RequestParam(value = "phone", defaultValue = "") String phone, @RequestParam(value = "name", defaultValue = "") String name) {
        Signup signup = new Signup();
        signup.Sign_up(username, password, phone, name,url,server_id,server_pw);
        return signup;
    }
}
