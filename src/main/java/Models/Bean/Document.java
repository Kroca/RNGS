package Models.Bean;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "documents")
@Table(name = "documents")
public class Document {
    @EmbeddedId
    private DocumentId id;

    @Column(name = "date")
    private Date date;

    public Document() {
    }

    public Document(DocumentId id, Date date) {
        this.id = id;
        this.date = date;
    }

    public DocumentId getId() {
        return id;
    }

    public void setId(DocumentId id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
