package uz.fivedhub.companyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CompanyCreateDto {
    private String name;
    private BigDecimal budget;
    private List<Long> userIds;
}
