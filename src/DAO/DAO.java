package DAO;

import models.IdOwner;

import java.util.ArrayList;
import java.util.List;

public abstract  class DAO<E extends IdOwner> {
    protected List<E> list = new ArrayList<E>();

    DAO(){
        list = loadFromDB();
    }

    public E findByID(int id){
        if(list==null || list.size()==0) return null;
        return list.stream().filter(e->e.getId()==id).findFirst().orElse(null);
    }

    public List<E> getAll(){
        return list;
    }

    protected abstract List<E> loadFromDB();
    protected abstract void saveToDB(List<E> list);
    abstract void create(E e);
    abstract E update(E e);
    abstract void delete(E e);
}
