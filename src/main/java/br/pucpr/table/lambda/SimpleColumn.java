package br.pucpr.table.lambda;

import br.pucpr.table.model.ColumnData;
import java.util.function.Function;

public class SimpleColumn<T> implements ColumnData<T> {
  private final String header;
  private final Function<T, String> getter;

  public SimpleColumn(String header, Function<T, String> getter) {
    this.header = header;
    this.getter = getter;
  }

  @Override
  public String header() {
    return header;
  }

  @Override
  public String get(T object) {
    return getter.apply(object);
  }
}
