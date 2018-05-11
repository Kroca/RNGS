package Services;

import Models.Bean.Document;
import Models.Bean.DocumentId;
import Models.DAO.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    public void setDocumentDAO(DocumentDAO documentDAO){
        this.documentDAO = documentDAO;
    }


    @Override
    @Transactional
    public void addDocument(Document d) {
        documentDAO.addDocument(d);
    }

    @Override
    @Transactional
    public Document getDocumentById(DocumentId id) {
        return documentDAO.getDocumentById(id);
    }
}
