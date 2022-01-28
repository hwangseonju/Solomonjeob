package com.ssafy.solomon.api.dto;

import com.ssafy.solomon.db.entity.EmailAuthEntity;
import com.ssafy.solomon.db.entity.MemberEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EmailAuthDto {

    private Long id;
    private String email;
    private String authToken;
    private Boolean expired;
    private LocalDateTime expireDate;

}
