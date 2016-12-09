package TestCreateZalog;

import Operation.Vykup;
import Operation.Zalog;
import Operation.Perezalog;
import Menu.ShortCutBar;
import org.junit.Before;
import org.junit.Test;
import Common.Common;

import org.sikuli.script.FindFailed;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NewZalog  extends  Zalog {
   // Zalog z;

    public NewZalog() throws FindFailed, URISyntaxException {
    }

    @Before
    public void setUp() throws InterruptedException, FindFailed, URISyntaxException {
        System.out.println("Before test");


      //openSpotify();
      //app = run();
    }



    @Test
    public void CreateZalog() throws FindFailed, URISyntaxException {
        System.out.println("test");
        Common Common = new Common();
        ShortCutBar bar;
        bar = new ShortCutBar();

        Calendar tmpDate;
        tmpDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");



        //List<HashMap<String, Object>> records = new ArrayList<HashMap<String, Object>>();
        List<Map<String, String>> listOfTests = new ArrayList<Map<String, String>>();
        Map<String,String> tests = new HashMap<String, String>();

        //region Описание тестов
        tests.put("NumberTest","1");
        tests.put("Run","1");
        tests.put("FIO","");
        tests.put("DateZalogOffset","-10"); //дата залога, указывается смещении в днях относиельно текущей даты
        tests.put("Algorithm","День");
        tests.put("DiscountCardNumber","11000003");
        tests.put("CountPeriodZalog","10");
        tests.put("GroupMaino","Драг.Металл");
        tests.put("createPerezalog","0"); //0 - перезалог не создается
        tests.put("DatePerezalogOffset","0"); //дата перезалога, указывается смещении в днях относиельно текущей даты
        tests.put("CountPeriodPerezalog","11");
        tests.put("createVykup","1");
        tests.put("DateVykupOffset","1");
        tests.put("Comment","");
        listOfTests.add(new HashMap<String, String>(tests));

        //region Описание тест2
        tests.put("NumberTest","2");
        tests.put("Run","0");
        tests.put("FIO","");
        tests.put("DateZalogOffset","-10"); //дата залога, указывается смещении в лнях относиельно текущей даты
        tests.put("Algorithm","День");
        tests.put("DiscountCardNumber","11000003");
        tests.put("CountPeriodZalog","10");
        tests.put("GroupMaino","Драг.Металл");
        tests.put("createPerezalog","0"); //0 - перезалог не создается
        tests.put("DatePerezalogOffset","0"); //дата перезалога, указывается смещении в днях относиельно текущей даты
        tests.put("CountPeriodPerezalog","0");
        tests.put("createVykup","0"); //0 - перезалог не создается
        tests.put("DateVykupOffset","1");
        tests.put("Comment","");
        listOfTests.add(new HashMap<String, String>(tests));
        //endregion Описание тест2

        //endregion



        //tests.put("DateZalog","13.01.2016");
        //listOfTests.add(new HashMap<String, String>(tests));


        //System.out.println(String.valueOf(cal1.getTime()));


       for (Map<String, String> t : listOfTests) {
           if (t.get("Run").equals("0")) continue;

           tmpDate = Calendar.getInstance();
           tmpDate.add(Calendar.DATE, Integer.valueOf(t.get("DateZalogOffset"))); //вычисляем дату залога
           Common.ChangeWorkDate(dateFormat.format(tmpDate.getTime()));

            bar.ChoiceZalog();

            Zalog Z = new Zalog();
            Z.Init();

            Z.SelectFIO();
            Z.SelectAlgorithmKredit(t.get("Algorithm"));
            Z.SetDiscountCardNumber(t.get("DiscountCardNumber"));
            Z.setCountPeriod(Integer.valueOf(t.get("CountPeriodZalog")));
            Z.AddMaino();
            Z.SelectGroupMaino(t.get("GroupMaino"));
            Z.SelectMaino("Запонки");
            Z.SelectMaterial("золото");
            Z.SelectProba("583","");
            Z.setAmount(1);
            Z.setWeight(1.2);

            Z.AddMaino();
            Z.SelectMaino("Брошка");
            Z.SelectMaterial("золото");
            Z.SelectProba("583","");
            Z.setAmount(2);
            Z.setWeight(1.3);
            // Assert.assertEquals(85.68,Z.getSummPercent(),0.00001);

            Z.saveZalog();
            Z.closeZalog();

           //region Продление
           if (!t.get("createPerezalog").equals("0")){
               tmpDate = Calendar.getInstance();
               tmpDate.add(Calendar.DATE, Integer.valueOf(t.get("DatePerezalogOffset"))); //вычисляем дату продлениея
               Common.ChangeWorkDate(dateFormat.format(tmpDate.getTime()));

               bar.ChoicePerezalog();

               Perezalog P = new Perezalog();

               P.Open(Z.getNumberZalog());
               //P.Open("ам 65846");
               P.Init();
               //P.setDateReturn("23.11.2016");
               P.setCountPeriods(Integer.valueOf(t.get("CountPeriodPerezalog")));
               P.savePerezalog();
               P.closePerezalog();

           }
           //endregion
           //region Выкуп

           if(!t.get("createVykup").equals("0")) {
               tmpDate = Calendar.getInstance();
               tmpDate.add(Calendar.DATE, Integer.valueOf(t.get("DateVykupOffset"))); //вычисляем дату выкупа
               Common.ChangeWorkDate(dateFormat.format(tmpDate.getTime()));
               bar.ChoiceVykup();

               Vykup V = new Vykup();
               V.Open(Z.getNumberZalog());
               V.Init();
               V.saveVykup();
               V.closeVykup();
           }
           //endregion Выкуп


        }


/*
        Common.ChangeWorkDate("01.03.2016");

        bar.ChoicePerezalog();

        Perezalog P = new Perezalog();

        P.Open(Z.getNumberZalog());
        //P.Open("ам 65846");
        P.Init();
        //P.setDateReturn("23.11.2016");
        P.setCountPeriods(11);
        P.savePerezalog();
        P.closePerezalog();
    */
       // MainMenu menu = new MainMenu();
        //menu.mSessia().click();
        //menu.subSessia(0).click();
    }
}
