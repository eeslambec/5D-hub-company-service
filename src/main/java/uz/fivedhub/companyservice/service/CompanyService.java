package uz.fivedhub.companyservice.service;

import org.springframework.stereotype.Service;
import uz.fivedhub.companyservice.dto.CompanyCreateDto;
import uz.fivedhub.companyservice.dto.CompanyUpdateDto;
import uz.fivedhub.companyservice.entity.Company;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyService {
    Company create(CompanyCreateDto companyCreateDto);
    Company getByName(String name);
    Company getById(Long id);
    List<Company> getAll();
    Company update(CompanyUpdateDto companyUpdateDto);
    void deleteById(Long id);
}
