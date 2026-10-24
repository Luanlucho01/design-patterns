package br.pucpr.table.model;

/** Observador notificado sempre que os dados de um {@link ObservableTableData} mudam. */
@FunctionalInterface
public interface TableDataListener {
  void dataChanged(TableData source);
}
