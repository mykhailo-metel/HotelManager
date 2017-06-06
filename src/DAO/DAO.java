package DAO;

import models.BaseModel;
import java.util.List;

public interface DAO<E extends BaseModel> {
    void create(E e);
    E update(E e);
    void delete(E e);
    E findByID(int id);
    List<E> getAll();
}
