package com.pantigoso.app.springbootdemo.Dao;

import com.pantigoso.app.springbootdemo.Model.Persona;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IPersonDao {

    List<Persona>findAll();
    void save(Persona persona);
    Persona find(Long id);
    void delete(Long id);
}
