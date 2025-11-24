package org.acme.todo.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {

    @NotBlank(message = "Title cannot be blank")
    public String title;

    public boolean completed;

}
