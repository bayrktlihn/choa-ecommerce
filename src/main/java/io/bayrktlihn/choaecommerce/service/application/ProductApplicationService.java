package io.bayrktlihn.choaecommerce.service.application;

import io.bayrktlihn.choaecommerce.dto.command.ChangeProductStockCommand;
import io.bayrktlihn.choaecommerce.dto.command.CreateProductWithDetailsCommand;
import io.bayrktlihn.choaecommerce.dto.command.RemoveCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.command.UpdateProductCommand;
import io.bayrktlihn.choaecommerce.dto.response.ChangeProductStockResponse;
import io.bayrktlihn.choaecommerce.dto.response.CreateProductWithDetailsResponse;
import io.bayrktlihn.choaecommerce.dto.response.RemoveCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.UpdateProductResponse;
import io.bayrktlihn.choaecommerce.exception.I18nSupportedException;
import io.bayrktlihn.choaecommerce.mapper.ProductMapper;
import io.bayrktlihn.choaecommerce.matcher.ProductMatcher;
import io.bayrktlihn.choaecommerce.model.Product;
import io.bayrktlihn.choaecommerce.repository.CategoryRepository;
import io.bayrktlihn.choaecommerce.repository.ProductRepository;
import io.bayrktlihn.choaecommerce.service.domain.ProductDomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductMapper productMapper;
    private final ProductDomainService productDomainService;
    private final ProductRepository productRepository;
    private final ProductMatcher productMatcher;


    private final CategoryRepository categoryRepository;

    // transaction burada başlatılır application katmanında domainde olmaz!!
    @Transactional
    public CreateProductWithDetailsResponse createProductWithDetails(CreateProductWithDetailsCommand createProductWithDetailsCommand) {
        Product product = productMapper.createProductWithDetailsCommandToProduct(createProductWithDetailsCommand);

        // bu method içindekiler buraya alınabilir. aşağıdaki bir sonraki yorum gibi
        productDomainService.initialize(product);
//        product.setCreationDate(Instant.now());
//        product.setStatus(ProductStatus.ACTIVE);

        product = productRepository.create(product);

        return productMapper.productToCreateProductWithDetailsResponse(product);
    }

    @Transactional
    public RemoveCategoryResponse removeCategory(RemoveCategoryCommand removeCategoryCommand) {

        Long productId = removeCategoryCommand.getProductId();
        Long categoryId = removeCategoryCommand.getCategoryId();

        Product product = productRepository.findById(productId).orElseThrow(() -> I18nSupportedException.createWithDefaultMessage("Product not found"));

//        bu domaine ait olmayan repoyu doğrudan kullanmak mantıklımı yoksa category application service üzerindenmi çağırmak lazım
//        application servisin application servisi çağırması olabilirmi. Olursa güzel olurmu
//        örnek olarak product application servicesin category application service barındırması
        boolean existsCategory = categoryRepository.existsById(categoryId);
        if (!existsCategory) {
            throw I18nSupportedException.createWithDefaultMessage("Category not found");
        }

        product.removeCategoryId(categoryId);

        product = productRepository.update(product);

        return productMapper.productToRemoveCategoryResponse(product);
    }

    @Transactional
    public UpdateProductResponse updateProduct(UpdateProductCommand updateProductCommand) {

        Long productId = updateProductCommand.getId();

        Product product = productRepository.findById(productId).orElseThrow(() -> I18nSupportedException.createWithDefaultMessage("Product not found"));

        checkCategories(updateProductCommand);

        productMatcher.matchUpdateProductCommandAndProduct(updateProductCommand, product);

        product = productRepository.update(product);


        return productMapper.productToUpdateProductResponse(product);
    }

    private void checkCategories(UpdateProductCommand updateProductCommand) {
        updateProductCommand.getCategoryIds().stream().forEach(categoryId -> {
            if (!categoryRepository.existsById(categoryId)) {
                throw I18nSupportedException.createWithDefaultMessage("Category not found");
            }
        });
    }

    @Transactional
    public ChangeProductStockResponse changeProductStock(ChangeProductStockCommand changeProductStockCommand) {

        // ortamı hazırla -> Arrange aslında
        Product product = productRepository.findById(changeProductStockCommand.getProductId()).orElseThrow(() -> I18nSupportedException.createWithDefaultMessage("Product not found"));

        // oynat -> act aslında
        product.changeStockQuantity(changeProductStockCommand.getStockQuantity());

        // şuanki durumu kaydet -> output port
        product = productRepository.update(product);

        // sanki aaa Arrange, Act, Assert gibi burada Assert yerine output port bir yere yazma gibi

        return productMapper.productToChangeProductStockResponse(product);
    }
}
