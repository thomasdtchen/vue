package thomas.spring;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @GetMapping("/users")
    public List<User> users(){
        List<User> users = new ArrayList<>();
        users.add(new User(LocalDate.now(), "Tom", "No. 189, Grove St, Los Angeles"));
        users.add(new User(LocalDate.now(), "Lily", "No. 190, Grove St, Los Angeles"));
        users.add(new User(LocalDate.now(), "Lucy", "No. 191, Grove St, Los Angeles"));
        users.add(new User(LocalDate.now(), "Andy", "No. 192, Grove St, Los Angeles"));
        users.add(new User(LocalDate.now(), "Li", "No. 193, Grove St, Los Angeles"));
        users.add(new User(LocalDate.now(), "Wu", "No. 194, Grove St, Los Angeles"));
/*        for(int i=0; i< 1000000; i++){
            users.add(new User(LocalDate.now(), "Wu", "No. 194, Grove St, Los Angeles"));
        }*/
        return users;
    }


}
