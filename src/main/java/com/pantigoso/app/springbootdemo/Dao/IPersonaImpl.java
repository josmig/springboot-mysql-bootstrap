package com.pantigoso.app.springbootdemo.Dao;

import com.pantigoso.app.springbootdemo.Model.Persona;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class IPersonaImpl implements IPersonDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Persona> findAll() {
        return em.createQuery("from persona").getResultList();
    }

    @Override
    public void save(Persona persona) {

    }

    @Override
    public Persona find(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
