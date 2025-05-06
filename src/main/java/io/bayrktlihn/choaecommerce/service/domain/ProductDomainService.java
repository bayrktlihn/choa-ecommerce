package io.bayrktlihn.choaecommerce.service.domain;

import io.bayrktlihn.choaecommerce.enums.ProductStatus;
import io.bayrktlihn.choaecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

// çok karmaşık iş kurallarım olmadığı için pek method eklemedim.
// Domain objeleri karmaşık olmaya başlarsa application katmanınındaki business logicleri buraya taşı
// service annotasyonu yapmaya gerek yok. Bean şeklindede oluşturabilirim.
// Springden bağımsız olabilir böylelikle.
@Service
@RequiredArgsConstructor
public class ProductDomainService {

    public void initialize(Product product) {
//        product.setCreationDate(Instant.now());
        product.setStatus(ProductStatus.ACTIVE);
    }


}
