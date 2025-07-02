package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.persistence.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Le dice a Spring que este repositorio se conecta con la BD
@Repository
public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //Me va a dar todos los productos de mi BD
    public List<Producto> getAll(){
        //Convirtiendo un iterable<t> a una lista de productos List<Producto>
        return (List<Producto>) productoCrudRepository.findAll();
    }

    //Obtiene los productos por categoria
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIDCategoriaOrderByNombreAsc(idCategoria);
    }

    //Obtiene estado del producto
    public Optional<List<Producto>> getBEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    //Obtiene un producto dado el id
    public Optional<Producto> findById(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    //Guarda un prducto
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    //Borrar un prodcuto
    public void delete(Producto producto){
        productoCrudRepository.delete(producto);
    }
}

