package Services;

import Models.Bean.Document;
import Models.Bean.DocumentId;

import java.util.List;

public interface DocumentService {
    public void addDocument(Document d);
    public Document getDocumentById(DocumentId id);
}
