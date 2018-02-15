package reseptish;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import reseptish.db.Database;
import reseptish.db.ReseptiRaakaaineDao;
import reseptish.db.SQLiteDatabase;
import reseptish.pojo.ReseptiRaakaaine;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.OpiskelijaDao;

public class Main {

    public static void main(String[] args) throws Exception {
        // asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        
        Database db = new SQLiteDatabase(new File("reseptish.db"));
        db.init();
        
        ReseptiRaakaaineDao dao = new ReseptiRaakaaineDao(db);
        for (ReseptiRaakaaine resepti : dao.findAll()) {
            System.out.println(resepti.getYksikko());
        }
        
        /*Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/resepti/:id", (req, res) -> {
            Map map = new HashMap<>();
            
            return new ModelAndView(map, "resepti");
        }, new ThymeleafTemplateEngine());*/
        
        /*Spark.get("/opiskelijat", (req, res) -> {
            HashMap map = new HashMap<>();
//            map.put("opiskelijat", opiskelijaDao.findAll());

            return new ModelAndView(map, "opiskelijat");
        }, new ThymeleafTemplateEngine());

        Spark.get("/opiskelijat/:id", (req, res) -> {
            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());*/
    }
}
