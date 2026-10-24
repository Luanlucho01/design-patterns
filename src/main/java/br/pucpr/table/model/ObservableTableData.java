package br.pucpr.table.model;

/** {@link TableData} que avisa seus observadores quando seus dados mudam. */
public interface ObservableTableData extends TableData {
  void addListener(TableDataListener listener);

  void removeListener(TableDataListener listener);
}
