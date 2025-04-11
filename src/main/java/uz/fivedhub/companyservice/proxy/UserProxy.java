package uz.fivedhub.companyservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.fivedhub.companyservice.dto.CustomResponseEntity;
import uz.fivedhub.companyservice.entity.User;

import java.util.List;

@FeignClient(value = "fivedhub-user-service")
public interface UserProxy {
    @GetMapping("/user/{id}")
    CustomResponseEntity<User> getById(@PathVariable("id") Long id);

    @GetMapping("/user/all")
    CustomResponseEntity<List<User>> getAll();

}