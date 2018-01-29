package es.albarregas.dao;

import java.io.Serializable;
import java.util.List;


public interface IGenericoDAO<T> {
    
    public void insertUpdate(T objeto);
    public <T> List<T> selectAll(Class<T> claseEntidad);
    public <T> T select(Serializable pk, Class<T> claseEntidad);
    public void delete(T objeto);
    
    
}