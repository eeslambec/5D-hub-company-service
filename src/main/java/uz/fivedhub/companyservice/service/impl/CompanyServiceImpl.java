package uz.fivedhub.companyservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fivedhub.companyservice.dto.CompanyCreateDto;
import uz.fivedhub.companyservice.dto.CompanyUpdateDto;
import uz.fivedhub.companyservice.dto.CustomResponseEntity;
import uz.fivedhub.companyservice.entity.Company;
import uz.fivedhub.companyservice.entity.User;
import uz.fivedhub.companyservice.exception.NotFoundException;
import uz.fivedhub.companyservice.proxy.UserProxy;
import uz.fivedhub.companyservice.repository.CompanyRepository;
import uz.fivedhub.companyservice.service.CompanyService;
import uz.fivedhub.companyservice.util.Validation;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserProxy userProxy;

    @Override
    public Company create(CompanyCreateDto companyCreateDto) {
        return companyRepository.save(
                Company.builder()
                        .name(companyCreateDto.getName())
                        .budget(companyCreateDto.getBudget())
                        .userIds(companyCreateDto.getUserIds())
                        .build()
        );
    }

    @Override
    public Company getByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Company"));
    }

    @Override
    public Company getById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company"));
        List<User> allUsers = userProxy.getAll().getBody();
        List<User> usersById()
        for (User user : users) {
            if (company.getUserIds().contains(user.getId())) {

            }
        }
        return
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }



    @Override
    public Company update(CompanyUpdateDto companyUpdateDto) {
        Company byId = companyRepository.findById(companyUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException("Company"));
        if (!Objects.equals(byId.getUserIds(), companyUpdateDto.getUserIds())){
            for (Long userId : companyUpdateDto.getUserIds()) {
                if (byId.getUserIds().contains(userId)) byId.getUserIds().add(userId);
            }
        }
        return companyRepository.save(Company.builder()
                    .id(companyUpdateDto.getId())
                    .name(Validation.requireNonNullElse(companyUpdateDto.getName(), byId.getName()))
                    .budget(Validation.requireNonNullElse(companyUpdateDto.getBudget(), byId.getBudget()))
                    .userIds(byId.getUserIds())
                    .build());
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company"));
        companyRepository.deleteById(id);
    }
}
