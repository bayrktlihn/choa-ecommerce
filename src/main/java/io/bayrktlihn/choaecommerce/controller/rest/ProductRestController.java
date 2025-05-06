package io.bayrktlihn.choaecommerce.controller.rest;

import io.bayrktlihn.choaecommerce.dto.command.ChangeProductStockCommand;
import io.bayrktlihn.choaecommerce.dto.command.CreateProductWithDetailsCommand;
import io.bayrktlihn.choaecommerce.dto.command.RemoveCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.command.UpdateProductCommand;
import io.bayrktlihn.choaecommerce.dto.response.ChangeProductStockResponse;
import io.bayrktlihn.choaecommerce.dto.response.CreateProductWithDetailsResponse;
import io.bayrktlihn.choaecommerce.dto.response.GenericRestResponse;
import io.bayrktlihn.choaecommerce.dto.response.RemoveCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.UpdateProductResponse;
import io.bayrktlihn.choaecommerce.service.application.ProductApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductApplicationService productApplicationService;


    @PostMapping
    public CreateProductWithDetailsResponse createProduct(@Valid @RequestBody CreateProductWithDetailsCommand createProductWithDetailsCommand) {
        return productApplicationService.createProductWithDetails(createProductWithDetailsCommand);
    }

    @PostMapping("remove-category")
    public GenericRestResponse<RemoveCategoryResponse> removeCategory(@Valid @RequestBody RemoveCategoryCommand removeCategoryCommand) {
        RemoveCategoryResponse removeCategoryResponse = productApplicationService.removeCategory(removeCategoryCommand);
        return GenericRestResponse.success(removeCategoryResponse);
    }

    @PatchMapping
    public GenericRestResponse<UpdateProductResponse> updateProduct(@Valid @RequestBody UpdateProductCommand updateProductCommand) {
        UpdateProductResponse updateProductResponse = productApplicationService.updateProduct(updateProductCommand);
        return GenericRestResponse.success(updateProductResponse);
    }

    @PatchMapping("change-product-stock")
    public GenericRestResponse<ChangeProductStockResponse> changeProductStock(@Valid @RequestBody ChangeProductStockCommand changeProductStockCommand) {
        ChangeProductStockResponse changeProductStockResponse = productApplicationService.changeProductStock(changeProductStockCommand);
        return GenericRestResponse.success(changeProductStockResponse);
    }

}
