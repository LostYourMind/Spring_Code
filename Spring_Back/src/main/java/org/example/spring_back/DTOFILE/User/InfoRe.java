package org.example.spring_back.DTOFILE.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class InfoRe {
    private String loginId;
    private String userName;
}
