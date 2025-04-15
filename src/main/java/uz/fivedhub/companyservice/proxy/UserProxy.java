package uz.fivedhub.companyservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.fivedhub.companyservice.dto.CustomResponseEntity;
import uz.fivedhub.companyservice.entity.User;

import java.util.List;

@FeignClient(value = "fivedhub-user-service", url = "localhost:5070")
public interface UserProxy {
    @GetMapping("/user/id/{id}")
    CustomResponseEntity<User> getById(@PathVariable("id") Long id);

    @GetMapping("/user/all")
    CustomResponseEntity<List<User>> getAll();

    @GetMapping("/user/company-id/{id}")
    CustomResponseEntity<List<User>> getByCompanyId(@PathVariable("id") Long id);

    @GetMapping("/user/all-id")
    CustomResponseEntity<List<User>> getAllByIds(@RequestBody List<Long> ids);

    @PutMapping("/user/update")
    CustomResponseEntity<User> update(@RequestBody User user);
}