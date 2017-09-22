package hibernate;

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
public class Currencypairs implements Serializable{
    private long id;
    private String pair;
    private Double values;
    private Timestamp dateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public Double getValues() {
        return values;
    }

    public void setValues(Double values) {
        this.values = values;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currencypairs that = (Currencypairs) o;

        if (id != that.id) return false;
        if (pair != null ? !pair.equals(that.pair) : that.pair != null) return false;
        if (values != null ? !values.equals(that.values) : that.values != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (pair != null ? pair.hashCode() : 0);
        result = 31 * result + (values != null ? values.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }
}
