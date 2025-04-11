package uz.fivedhub.companyservice.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
