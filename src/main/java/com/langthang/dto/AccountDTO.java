package com.langthang.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.langthang.model.entity.Account;
import com.langthang.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    private int accountId;

    private String name;

    private String email;

    private int postCount;

    private int followCount;

    private int bookmarkOnOwnPostCount;

    private int commentOnOwnPostCount;

    private String fbLink;

    private String instagramLink;

    private String avatarLink;

    private String about;

    private String occupation;

    private Role role = null;

    public static AccountDTO toBasicAccount(Account entity) {
        return AccountDTO.builder()
                .accountId(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .avatarLink(entity.getAvatarLink())
                .fbLink(entity.getFbLink())
                .instagramLink(entity.getInstagramLink())
                .about(entity.getAbout())
                .occupation(entity.getOccupation())
                .build();
    }
}