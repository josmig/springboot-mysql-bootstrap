package com.pantigoso.app.springbootdemo.Dao;

import com.pantigoso.app.springbootdemo.Model.Persona;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class IPersonaImpl implements IPersonDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return em.createQuery("from Persona").getResultList();
    }

    @Override
    @Transactional // sin el readonly ya que es de escritura
    public void save(Persona persona) {
        em.persist(persona);
    }

    @Override
    public Persona find(Long id) {
        return em.find(Persona.class,id);
    }

    @Override
    public void delete(Long id) {
        em.remove(id);
    }
}
