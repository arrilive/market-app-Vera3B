package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.persistence.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //Me va a dar todos los productos de mi BD
    public List<Producto> getAll(){
        //Convirtiendo un iterable<t> a una lista de productos List<Producto>
        return (List<Producto>) productoCrudRepository.findAll();
    }
}

