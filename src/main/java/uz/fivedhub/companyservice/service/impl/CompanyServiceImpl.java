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

import java.util.Collections;
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
        Company company = companyRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Company"));
        List<User> byCompanyId = userProxy.getByCompanyId(company.getId()).getBody();
        company.setUsers(byCompanyId);
        return company;
    }

    @Override
    public Company getById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company"));
        List<User> users = userProxy.getByCompanyId(id).getBody();
        if (users.isEmpty()) throw new NotFoundException("Users by company id");
        company.setUsers(users);
        return company;
    }

    @Override
    public List<Company> getAll() {
        List<Company> all = companyRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException("Companies");
        }

        for (Company company : all) {
            List<Long> userIds = company.getUserIds();
            if (userIds != null && !userIds.isEmpty()) {
                CustomResponseEntity<List<User>> response = userProxy.getAllByIds(userIds);
                if (response != null && response.getBody() != null) {
                    List<User> users = response.getBody();
                    company.setUsers(users);
                } else {
                    company.setUsers(Collections.emptyList());
                }
            } else {
                company.setUsers(Collections.emptyList());
            }
        }

        return all;
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
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company"));
        List<User> body = userProxy.getAllByIds(company.getUserIds()).getBody();
        if (body.isEmpty()) throw new NotFoundException("Users by company id");
        for (User user : body) {
            user.setCompanyId(null);
            user.setCompany(null);
            userProxy.update(user);
        }
        companyRepository.deleteById(id);
    }
}
