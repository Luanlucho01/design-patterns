package br.pucpr.table.reflection;

import br.pucpr.table.model.ColumnData;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectedColumn<T> implements ColumnData<T> {
  private final String header;
  private final Method getter;

  public ReflectedColumn(String header, Method getter) {
    this.header = header.isEmpty() ? getter.getName() : header;
    this.getter = getter;
  }

  @Override
  public String header() {
    return header;
  }

  @Override
  public String get(T object) {
    try {
      getter.setAccessible(true);
      return getter.invoke(object).toString();
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      return "?";
    }
  }
}
