package reseptish;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reseptish.db.Database;
import reseptish.db.ReseptiDao;
import reseptish.db.ReseptiRaakaaineDao;
import reseptish.db.SQLiteDatabase;
import reseptish.pojo.Resepti;
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
        
        ReseptiRaakaaineDao reseptiRaakaaineDao = new ReseptiRaakaaineDao(db);
        ReseptiDao reseptiDao = new ReseptiDao(db);
        
        
        //Etusivu
        Spark.get("/", (req, res) -> {
            return new ModelAndView(Collections.emptyMap(), "index");
        }, new ThymeleafTemplateEngine());

        //Yksittäinen resepti
        Spark.get("/resepti/:id", (req, res) -> {
            Map map = new HashMap<>();
            
            List<ReseptiRaakaaine> reseptiJaRaakaaineet = reseptiRaakaaineDao.findAllForResepti(Integer.parseInt(req.params("id")));
            Resepti resepti = reseptiJaRaakaaineet.get(0).getReseptiId();

            map.put("resepti", resepti);
            map.put("raakaaineet", reseptiJaRaakaaineet);
            
            return new ModelAndView(map, "soloresepti");
        }, new ThymeleafTemplateEngine());
        
        //Kaikki reseptit
        Spark.get("/reseptit", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("resepti", reseptiDao.findAll());

            return new ModelAndView(map, "reseptit");
        }, new ThymeleafTemplateEngine());
        
        //Haku
        Spark.get("/haku", (req, res) -> {
            return new ModelAndView(Collections.emptyMap(), "haku");
        }, new ThymeleafTemplateEngine());
        
        //Haku
        Spark.get("/uusi", (req, res) -> {
            return new ModelAndView(Collections.emptyMap(), "uusi");
        }, new ThymeleafTemplateEngine());
        
        //Tilastot
        Spark.get("/tilastot", (req, res) -> {
            Map map = new HashMap<>();
            
            //TODO: tilastojen hakeminen tietokannasta
            
            return new ModelAndView(map, "tilastot");
        }, new ThymeleafTemplateEngine());


        /*Spark.get("/opiskelijat/:id", (req, res) -> {
            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());*/
    }
}
