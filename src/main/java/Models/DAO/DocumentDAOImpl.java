package Models.DAO;

import Models.Bean.Document;
import Models.Bean.DocumentId;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {
    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(DocumentDAOImpl.class);

    public void setSessionFactory(SessionFactory sf){
        sessionFactory = sf;
    }

    @Override
    public void addDocument(Document d) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(d);
        }catch (HibernateException ex){
            logger.error(ex.getMessage());
            return;
        }
        logger.info("Новый документ успешно добавлен");
    }


    @Override
    public Document getDocumentById(DocumentId id) {

        Document document= null;
        try {
            Session session = sessionFactory.getCurrentSession();
            document = (Document) session.get(Document.class,id);
        }catch (HibernateException ex){
            logger.error(ex.getMessage());
            return null;
        }
        logger.info("Документ успешно загружен");
        return document;
    }

}
