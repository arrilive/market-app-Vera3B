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

    //Inyectado automáticamente: Spring crea el objeto por ti
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;


    @Override
    //Me va a dar todos los productos de mi BD
    public List<Product> getAll(){
        //Convirtiendo un iterable<t> a una lista de productos List<Producto>
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    //Obtiene los productos por categoria
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    //Obtiene estado del producto
    public Optional<List<Product>> getScarseProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        //No hay un mapper que convierta una lista de opcionales, se tiene que usar map
         return productos.map(prods-> productMapper.toProducts(prods));
    }

    @Override
    //Obtiene un producto dado el id
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId).map (producto -> productMapper.toProduct(producto));
    }

    @Override
    //Guarda un prducto
    public Product save(Product product){
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    //Borrar un prodcuto
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}

