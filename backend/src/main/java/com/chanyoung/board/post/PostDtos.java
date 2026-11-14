package com.chanyoung.board.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public final class PostDtos {

    private PostDtos() {
    }

    public record CreateRequest(
            @NotBlank @Size(max = 100) String title,
            @NotBlank @Size(max = 2000) String content,
            @NotBlank @Size(max = 30) String author
    ) {
    }

    public record UpdateRequest(
            @NotBlank @Size(max = 100) String title,
            @NotBlank @Size(max = 2000) String content
    ) {
    }

    public record Response(
            Long id,
            String title,
            String content,
            String author,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        static Response from(Post post) {
            return new Response(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getAuthor(),
                    post.getCreatedAt(),
                    post.getUpdatedAt()
            );
        }
    }
}
