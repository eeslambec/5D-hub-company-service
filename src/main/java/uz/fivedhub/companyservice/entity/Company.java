package uz.fivedhub.companyservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal budget;

    @Column(name = "user_ids")
    private String userIdsStr;

    @Transient
    private List<Long> userIds;

    @Transient
    private List<User> users;

    public void setUserIds(List<Long> ids) {
        this.userIds = ids;
        this.userIdsStr = (ids != null && !ids.isEmpty())
                ? ids.stream().map(String::valueOf).collect(Collectors.joining(","))
                : null;
    }

    public List<Long> getUserIds() {
        if (userIds == null && userIdsStr != null && !userIdsStr.isEmpty()) {
            userIds = Arrays.stream(userIdsStr.split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
        }
        return userIds;
    }

    @PrePersist
    @PreUpdate
    private void updateUserIdsStr() {
        setUserIds(userIds);
    }
}