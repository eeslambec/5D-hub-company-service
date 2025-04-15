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
    private Long companyId;
    @Transient
    private Company company;
}
