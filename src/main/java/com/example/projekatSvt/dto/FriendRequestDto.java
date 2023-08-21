package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.FriendRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class FriendRequestDto {

    private Long id;
    @NotBlank
    private Boolean approved;
    @NotBlank
    private LocalDateTime createdAt;
    @NotBlank
    private LocalDateTime at;

    public FriendRequestDto (FriendRequest createdFriendRequest) {
        this.id = createdFriendRequest.getId();
        this.approved = createdFriendRequest.getApproved();
        this.createdAt = createdFriendRequest.getCreatedAt();
        this.at = createdFriendRequest.getAt();
    }
}
