package com.mithilesh.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int categoryId;

    @NotEmpty
    @Size(min = 3,message = "Title must be minimum length of 4")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 3,message = "Description must be minimum length of 4")
    private String categoryDescription;
}
