package com.rvn14.cores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostForm {
    @NotBlank(message = "Post content cannot be empty")
    @Size(max = 500, message = "Post cannot exceed 500 characters")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
