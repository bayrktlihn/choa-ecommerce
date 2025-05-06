package io.bayrktlihn.choaecommerce.controller.rest;

import io.bayrktlihn.choaecommerce.dto.command.AssignProductToCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.command.ChangeParentCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.command.CreateCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.response.AssignProductToCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.ChangeParentCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.CreateCategoryResponse;
import io.bayrktlihn.choaecommerce.service.application.CategoryApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryApplicationService categoryApplicationService;


    @PostMapping
    public CreateCategoryResponse create(@Valid @RequestBody CreateCategoryCommand createCategoryCommand) {
        return categoryApplicationService.create(createCategoryCommand);
    }

    @PostMapping("assign-product-to-category")
    public AssignProductToCategoryResponse assignProductToCategory(@Valid @RequestBody AssignProductToCategoryCommand assignProductToCategoryCommand) {
        return categoryApplicationService.assignProductToCategory(assignProductToCategoryCommand);
    }

    @PostMapping("change-parent-category")
    public ChangeParentCategoryResponse changeParentCategory(@Valid @RequestBody ChangeParentCategoryCommand changeParentCategoryCommand) {
        ChangeParentCategoryResponse changeParentCategoryResponse = categoryApplicationService.changeParentCategory(changeParentCategoryCommand);
        return changeParentCategoryResponse;
    }

}
