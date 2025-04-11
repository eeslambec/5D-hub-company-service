package uz.fivedhub.companyservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class CompanyUpdateDto {
    private Long id;
    private String name;
    private BigDecimal budget;
    private List<Long> userIds;
}
