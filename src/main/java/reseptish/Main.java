package reseptish;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reseptish.db.Database;
import reseptish.db.KategoriaDao;
import reseptish.db.RaakaaineDao;
import reseptish.db.ReseptiDao;
import reseptish.db.ReseptiRaakaaineDao;
import reseptish.db.SQLiteDatabase;
import reseptish.pojo.Raakaaine;
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
        RaakaaineDao raakaaineDao = new RaakaaineDao(db);
        KategoriaDao kategoriaDao = new KategoriaDao(db);

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

        //Uusi
        Spark.get("/uusi", (req, res) -> {
            return new ModelAndView(Collections.emptyMap(), "uusi");
        }, new ThymeleafTemplateEngine());

        Spark.post("/uusi", (req, res) -> {

            Resepti resepti = new Resepti(null, req.params("nimi"), req.params("ohje"),
                    req.params("tekija"), req.attribute("valmistusaika"));
            kategoriaDao.add(req.params("kategoria"));

            //  palauttaa reseptin Id:n (ainakin teoriassa)
            resepti.setReseptiId(reseptiDao.add(resepti));

            for (int i = 1; i < 16; i++) {
                String raakaaine = req.params("raaka-aine" + i);
                String maara = req.params("maara" + i);
                String yksikko = req.params("yksikko" + i);

                if (!raakaaine.isEmpty() && !(maara.isEmpty()) && !(yksikko.isEmpty())) {

                    raakaaineDao.add(raakaaine);

                    ReseptiRaakaaine uusi = new ReseptiRaakaaine(resepti,
                            raakaaineDao.search(raakaaine), maara, raakaaine, i, raakaaine) //  ei vielä valmis
                            //                  reseptiRaakaaineDao.add(resepti.getReseptiId, raakaaine.getRaakaaineId);   
                }
            }

            res.redirect("/resepti/" + resepti.getReseptiId());
            return new ModelAndView(Collections.emptyMap(), "index");
        }, new ThymeleafTemplateEngine());

        //Tilastot
        Spark.get("/tilasto", (req, res) -> {
            Map kokonaismaara = new HashMap<>();

            kokonaismaara.put("maara", reseptiDao.count());

            //TODO: tilastojen hakeminen tietokannasta
            return new ModelAndView(kokonaismaara, "tilasto");
        }, new ThymeleafTemplateEngine());


        /*Spark.get("/opiskelijat/:id", (req, res) -> {
            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());*/
    }
}
