package uz.fivedhub.companyservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal budget;
    @ElementCollection
    @CollectionTable(name = "company_user_ids", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "user_id")
    private List<Long> userIds;
}
