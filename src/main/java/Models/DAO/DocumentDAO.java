package Models.DAO;

import Models.Bean.Document;
import Models.Bean.DocumentId;

import java.util.List;

public interface DocumentDAO {
    public void addDocument(Document d);
    public Document getDocumentById(DocumentId id);
}
