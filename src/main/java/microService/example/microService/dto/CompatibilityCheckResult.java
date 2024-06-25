package microService.example.microService.dto;

import microService.example.microService.Entity.ProductList;

import java.util.List;

public class CompatibilityCheckResult {
    private boolean isCompatible;
    private List<ProductList> productsWithGreaterId;

    public CompatibilityCheckResult(boolean isCompatible, List<ProductList> productsWithGreaterId) {
        this.isCompatible = isCompatible;
        this.productsWithGreaterId = productsWithGreaterId;
    }

    public boolean isCompatible() {
        return isCompatible;
    }

    public List<ProductList> getProductsWithGreaterId() {
        return productsWithGreaterId;
    }
}
