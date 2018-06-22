package com.daningo.service;

/**
 * Created by naing on 6/21/18.
 */

import java.util.List;
import java.util.Map;

public interface UIService<T> {

    public List<T> getAll(T model);
    public List<T> getAll(int id);
    public List<T> getAll();
    public T add(T model);
    public void update(T model);
    public T get(T model);

}
