package br.pucpr.table;

import br.pucpr.table.model.PagedTableData;
import br.pucpr.table.model.TableData;
import java.util.Collection;

public class TableBuilder {
  public interface TableDataConfig<T> {
    ColumnsBuilder<T> setup(ColumnsBuilder<T> b);
  }

  private TableData data;
  private Theme theme = Theme.NORMAL;
  private boolean alignRight = false;
  private int pageSize = 0;
  private int page = 1;

  public TableBuilder withTheme(Theme theme) {
    this.theme = theme;
    return this;
  }

  public TableBuilder light() {
    return withTheme(Theme.LIGHT);
  }

  public TableBuilder dark() {
    return withTheme(Theme.DARK);
  }

  public TableBuilder rightAligned() {
    this.alignRight = true;
    return this;
  }

  public TableBuilder paged(int pageSize, int page) {
    this.pageSize = pageSize;
    this.page = page;
    return this;
  }

  public TableBuilder paged(int pageSize) {
    return paged(pageSize, 1);
  }

  public Table withData(TableData data) {
    this.data = data;
    return build();
  }

  public <T> Table withData(Collection<T> data, TableDataConfig<T> cfg) {
    var builder = new ColumnsBuilder<T>();
    this.data = cfg.setup(builder).build(data);
    return build();
  }

  private Table build() {
    if (this.data == null) {
      throw new IllegalStateException("Call a data setting method first!");
    }
    final var tableData = pageSize > 0 ? new PagedTableData(data, pageSize, page) : data;
    return new Table(tableData, theme, alignRight);
  }
}
