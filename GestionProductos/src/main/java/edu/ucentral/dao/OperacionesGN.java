package edu.ucentral.dao;

import java.util.List;

public interface OperacionesGN<T> {
    public void crear(T entidad);
    public List<T> obtenerTodos();
}
