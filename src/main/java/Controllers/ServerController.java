package Controllers;

import Models.Bean.Document;
import Models.Bean.DocumentId;
import Services.DocumentService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class ServerController {

    private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/")
    public ModelAndView mainPage(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/getNumber",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> getNumber(@RequestBody String json){
        int docId;

        try {
            JSONObject context = new JSONObject(json).getJSONObject("context");
            docId = Integer.parseInt(context.getString("documentId"));
        }catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put("error","Couldn't handle the input").toString(),HttpStatus.BAD_REQUEST);
        }
        logger.info("Получен запрос на генерацию числа для докумнта" + docId);

        int randomNum = generateRandomNumber();
        DocumentId id = new DocumentId(docId,randomNum);

        //check if combination of docid and number already exists
        while (documentService.getDocumentById(id)!=null){
            randomNum = generateRandomNumber();
            id.setNumber(randomNum);
        }

        documentService.addDocument(new Document(id,new Date()));
        logger.info("Документу "+docId+"присвоен номер "+randomNum);


        String responseJson = new JSONObject().put("messageName","generateRs")
                .put("context",new JSONObject().put("uniqNum",id.getNumber())).toString();

        return new ResponseEntity<>(responseJson,HttpStatus.OK);
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public ResponseEntity<String> chekc(@RequestBody String json){

        int docId;
        int generatedNumber;
        try {
            JSONObject context = new JSONObject(json).getJSONObject("context");
            docId = Integer.parseInt(context.getString("documentId"));
            generatedNumber = Integer.parseInt(context.getString("uniqNum"));
        }catch (Exception e){
            return new ResponseEntity<>(new JSONObject().put("error","Couldn't handle the input").toString(),HttpStatus.BAD_REQUEST);
        }
        logger.info("Получен запрос на проверку числа " +generatedNumber+" для докумнта " + docId);
        DocumentId id = new DocumentId(docId,generatedNumber);
        JSONObject responseJson = new JSONObject().put("messageName","checkRs");
        JSONObject context = new JSONObject();
        Document document = documentService.getDocumentById(id);
        if(document!=null){
            context.put("generateDate",document.getDate());
            context.put("Result","true");
            logger.info("Проверка прошла успешно");
        }else {
            context.put("Result","false");
            logger.info("Проверка не прошла");
        }
        responseJson.put("context",context);

        return new ResponseEntity<>(responseJson.toString(),HttpStatus.OK);
    }
    public int generateRandomNumber(){
        return ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
    }
}
