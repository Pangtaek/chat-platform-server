package com.pangtaek.chatplatform.domain.repository.entity;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 버그 예방
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long t_id;

    @Column(nullable = false)
    private String name;

    @Column
    private Timestamp created_at;

    // FetchType은 EAGER 값이다(User 객체를 불러올 때, Credentials 값 전부를 가져온다.)
    // LAZY 값으로 설명하면, 필요할 때 값을 가져온다(DB에서 데이터 값을 가져오지 않고 JPA 엔티티에서 프록시 값만 참조).
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserCredentials userCredentials;

    public void setCredentials(UserCredentials credentials) {
        this.userCredentials = credentials;
    }
}
