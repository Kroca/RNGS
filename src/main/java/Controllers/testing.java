package Controllers;

import javafx.util.Pair;
import org.json.JSONObject;

import javax.persistence.Tuple;

public class
testing {
    public static void main(String[] args) {
        String json = new JSONObject().put("messageName","generateRs")
                .put("context",new JSONObject().put("uniqNum","123")).toString();

        JSONObject obj = new JSONObject(json);

        System.out.println(obj);
        System.out.println(obj.get("messageName"));
        System.out.println(obj.getJSONObject("context").getInt("uniqNum"));

    }
    public static void test(Tuple ... s){
        for (int i=0;i<s.length;i++){
            System.out.println(s[i]);
        }

    }
}
