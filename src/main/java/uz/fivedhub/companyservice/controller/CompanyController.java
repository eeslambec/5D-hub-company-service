package uz.fivedhub.companyservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fivedhub.companyservice.dto.CompanyCreateDto;
import uz.fivedhub.companyservice.dto.CompanyUpdateDto;
import uz.fivedhub.companyservice.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CompanyCreateDto companyCreateDto) {
        return ResponseEntity.ok(companyService.create(companyCreateDto));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(companyService.getByName(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CompanyUpdateDto companyUpdateDto) {
        return ResponseEntity.ok(companyService.update(companyUpdateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.ok("Company successfully deleted");
    }

}
