package io.bayrktlihn.choaecommerce.service.application;

import io.bayrktlihn.choaecommerce.dto.command.AssignProductToCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.command.ChangeParenCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.command.CreateCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.response.AssignProductToCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.ChangeParentCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.CreateCategoryResponse;
import io.bayrktlihn.choaecommerce.exception.I18nSupportedException;
import io.bayrktlihn.choaecommerce.mapper.CategoryMapper;
import io.bayrktlihn.choaecommerce.mapper.ProductMapper;
import io.bayrktlihn.choaecommerce.model.Category;
import io.bayrktlihn.choaecommerce.model.Product;
import io.bayrktlihn.choaecommerce.repository.CategoryRepository;
import io.bayrktlihn.choaecommerce.repository.ProductRepository;
import io.bayrktlihn.choaecommerce.service.domain.CategoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryApplicationService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryDomainService categoryDomainService;
    private final ProductMapper productMapper;

    @Transactional
    public CreateCategoryResponse create(CreateCategoryCommand createCategoryCommand) {

        Category category = categoryMapper.createCategoryCommandToCategory(createCategoryCommand);

        category = categoryRepository.create(category);

        return categoryMapper.categoryToCreateCategoryResponse(category);

    }

    @Transactional
    public AssignProductToCategoryResponse assignProductToCategory(AssignProductToCategoryCommand assignProductToCategoryCommand) {
        Long productId = assignProductToCategoryCommand.getProductId();
        Long categoryId = assignProductToCategoryCommand.getCategoryId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> I18nSupportedException.createWithDefaultMessage("Product not found"));


        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> I18nSupportedException.createWithDefaultMessage("Category not found"));


//        add Category yapıp categorymı geçeyim ama product category ids barındırıyor.
//        bunu productdomain içinde yapmaya gerek varmı zaten tek iş ve bu işi yapan method entity üzerinde var.
//        product ve category ikiside ayrı domain şeklinde yaptım. category product subdomainimi
//        yada product category subdomainimi şeklinde yapmak lazım bilemedim.
        product.addCategoryId(category.getId());
        category.addProductId(product.getId());

        product = productRepository.update(product);
        category = categoryRepository.update(category);

        return productMapper.productToAssignProductToCategoryResponse(product);

    }

    @Transactional
    public ChangeParentCategoryResponse changeParentCategory(ChangeParenCategoryCommand changeParenCategoryCommand) {

        Long categoryId = changeParenCategoryCommand.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> I18nSupportedException.createWithDefaultMessage("Category not found"));

//        parent id objenin idsi ile aynı olamaz. changeParentCategoryId kontrolu yapıyor. hata atabilir aynı ise
//        yoksa kontrolu burda yapıp changeParentCategoryId mı yapmak lazım. ama sonuçta business kuralı bu entity
//        içinde yapmak iyi olabilir
        Long parentCategoryId = changeParenCategoryCommand.getParentCategoryId();
        category.changeParentCategoryId(parentCategoryId);

        category = categoryRepository.update(category);

        return categoryMapper.categoryToChangeParentCategoryResponse(category);
    }
}
