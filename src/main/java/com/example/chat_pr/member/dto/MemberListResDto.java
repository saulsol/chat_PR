package com.example.chat_pr.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberListResDto {

    private Long id;

    private String name;

    private String email;
}
