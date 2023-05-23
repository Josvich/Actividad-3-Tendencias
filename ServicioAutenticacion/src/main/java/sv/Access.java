package sv;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.User;

@WebService(serviceName = "Access")
public class Access {

    private static ArrayList<User> users = new ArrayList<>();

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "login")
    public User login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {

        for (User us : users) {
            if (username.equals(us.getUsername()) && password.equals(us.getPassword())) {
                return us;
            }
        }
        return null;
    }

    @WebMethod(operationName = "SingUp")
    public boolean SingUp(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "balance") int balance) {
        for (User us : users) {
            if (username.equals(us.getUsername())) {
                return false;
            }
            
        }
        users.add(new User(username, password, balance));
        return true;
    }

    @WebMethod(operationName = "Update")
    public User Update(@WebParam(name = "username") String username, @WebParam(name = "value") int value, @WebParam(name = "ope") boolean ope) {
        for (User us : users) {
            if (username.equals(us.getUsername()) && ope == true) {
                int add = us.getBalance() + value;
                us.setBalance(add);
                return us;
            } else if (username.equals(us.getUsername()) && ope == false) {
                int ret = us.getBalance() - value;
                if (ret < 0) {
                    return null;
                } else {
                    us.setBalance(ret);
                    return us;
                }
            }
        }
        return null;
    }
}
