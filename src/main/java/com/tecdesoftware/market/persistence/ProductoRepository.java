package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.repository.ProductRepository;
import com.tecdesoftware.market.persistence.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistence.entity.Producto;
import com.tecdesoftware.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Le dice a Spring que este repositorio se conecta con la BD
@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        return productMapper.toProducts((List<Producto>) productoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        return Optional.of(
                productMapper.toProducts(
                        productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId)
                )
        );
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(productMapper::toProducts);

    }





    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository
                .findById(productId)
                .map(productMapper::toProduct);
    }

    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}


