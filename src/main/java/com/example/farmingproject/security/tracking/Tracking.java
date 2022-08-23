package com.example.farmingproject.security.tracking;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "track")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    private Integer id;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "role")
    @NonNull
    private String role;

    @Column(name = "datetime")
    @NonNull
    private LocalDateTime localDateTime;
}
