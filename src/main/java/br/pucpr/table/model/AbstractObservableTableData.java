package br.pucpr.table.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Base com o gerenciamento de observadores para implementações de {@link TableData}. */
public abstract class AbstractObservableTableData implements ObservableTableData {
  private final List<TableDataListener> listeners = new CopyOnWriteArrayList<>();

  @Override
  public void addListener(TableDataListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    listeners.add(listener);
  }

  @Override
  public void removeListener(TableDataListener listener) {
    listeners.remove(listener);
  }

  protected void fireDataChanged() {
    for (var listener : listeners) {
      listener.dataChanged(this);
    }
  }
}
