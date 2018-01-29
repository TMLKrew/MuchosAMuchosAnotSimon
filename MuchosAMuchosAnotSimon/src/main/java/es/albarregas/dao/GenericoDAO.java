package es.albarregas.dao;


import es.albarregas.persistencia.HibernateUtil;
import java.io.Serializable;

import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;


public class GenericoDAO<T> implements IGenericoDAO<T> {
    
    private Session sesion;
    
    private void startTransaction(){
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }
    
    private void endTransaction(){
        if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)){
        sesion.getTransaction().commit();
        }
        sesion.close();
    }
    
    private void handleExcepcion(HibernateException he) throws HibernateException {
        sesion.getTransaction().rollback();
        throw he;
    } 

    @Override
    public void insertUpdate(T objeto) {
        try {
            startTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.flush();
            
        } catch (HibernateException he){
            handleExcepcion(he);
        } finally {
            endTransaction();
        }
    }

    @Override
    public <T> List<T> selectAll(Class<T> claseEntidad) {
        List<T> listadoResultados = null;
        try {
            startTransaction();
            listadoResultados = sesion.createQuery(" from " + claseEntidad.getSimpleName()).list();
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return listadoResultados;
    }

    @Override
    public <T> T select(Serializable pk, Class<T> claseEntidad) {
        T objetoRecuperado = null;
        
        try {
            startTransaction();
            objetoRecuperado = (T) sesion.get(claseEntidad, pk);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        
        return objetoRecuperado;
    }

    @Override
    public void delete(T objeto) {
        try {
            startTransaction();
            sesion.delete(objeto);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
    }
    
 }
