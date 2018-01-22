package hibernate;

public class Shop {
    private String pairname;
    private Double value;
    private Double valuetosell;
    private Integer quantity;
    private Double total;

    public String getPairname() {
        return pairname;
    }

    public void setPairname(String pairname) {
        this.pairname = pairname;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValuetosell() {
        return valuetosell;
    }

    public void setValuetosell(Double valuetosell) {
        this.valuetosell = valuetosell;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (pairname != null ? !pairname.equals(shop.pairname) : shop.pairname != null) return false;
        if (value != null ? !value.equals(shop.value) : shop.value != null) return false;
        if (valuetosell != null ? !valuetosell.equals(shop.valuetosell) : shop.valuetosell != null) return false;
        if (quantity != null ? !quantity.equals(shop.quantity) : shop.quantity != null) return false;
        if (total != null ? !total.equals(shop.total) : shop.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pairname != null ? pairname.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (valuetosell != null ? valuetosell.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
