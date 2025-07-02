package com.tecdesoftware.market.persistence.crud;

import com.tecdesoftware.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Query Methods
    //SQL: SELECT *
    //FROM Categoria
    //WHERE id_categoria = x
    //ORDER BY Nombre ASC
    List<Producto> findByIDCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStockIsLessThan, Boolean estado);
}
