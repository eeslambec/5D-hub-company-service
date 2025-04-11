package uz.fivedhub.companyservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fivedhub.companyservice.dto.CompanyCreateDto;
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

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return
    }
}
