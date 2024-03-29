package com.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostPatchResponse {
    private Long postId;
    private String email;
    private String title;
    private String content;
    private String message;
}
