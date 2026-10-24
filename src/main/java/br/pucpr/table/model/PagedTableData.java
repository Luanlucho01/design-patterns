package br.pucpr.table.model;

/** Decorator que expõe apenas as linhas da página atual de qualquer {@link TableData}. */
public final class PagedTableData extends AbstractObservableTableData {
  private final TableData data;
  private final int pageSize;
  private int page;

  public PagedTableData(TableData data, int pageSize, int page) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be positive");
    }
    this.data = data;
    this.pageSize = pageSize;
    setPage(page);
    if (data instanceof ObservableTableData observable) {
      observable.addListener(source -> onSourceChanged());
    }
  }

  public PagedTableData(TableData data, int pageSize) {
    this(data, pageSize, 1);
  }

  public int getPageSize() {
    return pageSize;
  }

  /** Página atual, começando em 1. */
  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    if (page < 1 || page > pageCount()) {
      throw new IllegalArgumentException("Page must be between 1 and " + pageCount());
    }
    if (this.page != page) {
      this.page = page;
      fireDataChanged();
    }
  }

  private void onSourceChanged() {
    page = Math.min(page, pageCount());
    fireDataChanged();
  }

  public int pageCount() {
    return Math.max(1, (data.rowCount() + pageSize - 1) / pageSize);
  }

  public boolean hasNext() {
    return page < pageCount();
  }

  public boolean hasPrevious() {
    return page > 1;
  }

  public PagedTableData next() {
    setPage(page + 1);
    return this;
  }

  public PagedTableData previous() {
    setPage(page - 1);
    return this;
  }

  private int offset() {
    return (page - 1) * pageSize;
  }

  @Override
  public int rowCount() {
    return Math.max(0, Math.min(pageSize, data.rowCount() - offset()));
  }

  @Override
  public int colCount() {
    return data.colCount();
  }

  @Override
  public String header(int col) {
    return data.header(col);
  }

  @Override
  public String get(int row, int col) {
    if (row < 0 || row >= rowCount()) {
      throw new IndexOutOfBoundsException("Row " + row + " is outside the current page");
    }
    return data.get(offset() + row, col);
  }
}
