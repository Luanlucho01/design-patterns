package br.pucpr.table;

import br.pucpr.table.model.ObservableTableData;
import br.pucpr.table.model.TableData;
import br.pucpr.table.model.TableDataListener;

/** Componente que redesenha a tabela sempre que seus dados mudam. */
public final class TableView implements TableDataListener {
  private final Table table;
  private final ObservableTableData data;

  public TableView(Table table) {
    if (table == null) {
      throw new IllegalArgumentException("Table cannot be null");
    }
    if (!(table.getData() instanceof ObservableTableData observable)) {
      throw new IllegalArgumentException("Table data must be observable");
    }
    this.table = table;
    this.data = observable;
    data.addListener(this);
  }

  public void render() {
    table.print();
  }

  public void detach() {
    data.removeListener(this);
  }

  @Override
  public void dataChanged(TableData source) {
    render();
  }
}
