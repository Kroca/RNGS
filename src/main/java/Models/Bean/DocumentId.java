package Models.Bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DocumentId implements Serializable {
    @Column(name = "id")
    private int id;
    @Column(name = "number")
    private int number;

    public DocumentId(){

    }

    public DocumentId(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof DocumentId)) return false;
        DocumentId temp = (DocumentId)obj;
        return Objects.equals(getId(),temp.getId()) && Objects.equals(getNumber(),temp.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getNumber());
    }
}
