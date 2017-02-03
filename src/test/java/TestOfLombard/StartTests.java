package TestOfLombard;

import Operation.Vykup;
import Operation.Zalog;
import Operation.Perezalog;
import Menu.ShortCutBar;
import org.junit.Before;
import org.junit.Test;
import Common.Common;

import net.iryndin.jdbf.core.DbfMetadata;
import net.iryndin.jdbf.core.DbfRecord;
import net.iryndin.jdbf.reader.DbfReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;

import org.sikuli.script.FindFailed;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StartTests extends  Zalog {
   // Zalog z;
   private List<Map<String, String>> listOfTests = new ArrayList<Map<String, String>>();
          //List<HashMap<String, Object>> records = new ArrayList<HashMap<String, Object>>();

    public StartTests() throws FindFailed, URISyntaxException {
    }

    @Before
    public void setUp() throws InterruptedException, FindFailed, URISyntaxException {
        System.out.println("Before test");

        InputStream dbf;
        Map<String,String> test = new HashMap<String, String>();
        Map<String, Object> t;
        //Map<String, Object> t = new  LinkedHashMap<String, Object>();

        Charset stringCharset = Charset.forName("Cp1251");

        //dbf = new FileInputStream("d:\\Base\\Apostolovo\\TestLombard.dbf");
        //https://github.com/iryndin/jdbf - работа с дбф
        dbf = getClass().getClassLoader().getResourceAsStream("TestsDBF/TestsLombard.dbf");

        DbfRecord rec;
        try (DbfReader reader = new DbfReader(dbf)) {
            //DbfMetadata meta = reader.getMetadata();
            //System.out.println("Read DBF Metadata: " + meta);

            //region Описание тестов/Параметры тестов, операций залога
            while ((rec = reader.read()) != null) {
                rec.setStringCharset(stringCharset);
                t = rec.toMap();

                test.put("NumberTest",String.valueOf(t.get("NUMBERTEST"))); //номер теста
                test.put("Run",String.valueOf(t.get("RUN")));        //1-запускать тест, 0 - не запускать тест
                test.put("FIO",String.valueOf(t.get("FIO")));        //ФИО клиента, Путота - рандомный выбор из базы
                test.put("DateZalogOffset",String.valueOf(t.get("DATEZALOG"))); //дата залога, указывается смещении в днях относиельно текущей даты
                test.put("Algorithm",String.valueOf(t.get("ALGORITHM"))); //Алгоритм залога
                test.put("DiscountCardNumber",String.valueOf(t.get("DISCOUNT"))); //Диск.карточка
                test.put("CountPeriodZalog",String.valueOf(t.get("Z_PERIOD")));         //количество дней(периодов)залога
                test.put("GroupMaino",String.valueOf(t.get("GROUPMAINO")));
                test.put("СreatePerezalog",String.valueOf(t.get("PEREZALOG"))); //0 - перезалог не создается
                test.put("DatePerezalogOffset",String.valueOf(t.get("DATEPEREZ"))); //дата перезалога, указывается смещении в днях относиельно текущей даты >=0
                test.put("CountPeriodPerezalog",String.valueOf(t.get("P_PERIOD")));
                test.put("СreateVykup",String.valueOf(t.get("VYKUP"))); //0-выкуп не создается, 1 - выкуп создается
                test.put("DateVykupOffset",String.valueOf(t.get("DATEVYKUP")));
                //test.put("Comment",String.valueOf(t.get("COMMENT")));
                if (test.get("Run").equals("0")) continue;
                listOfTests.add(new HashMap<String, String>(test)); //Добавление в список тестов
                //System.out.println("Record #" + rec.getRecordNumber() + ": " + rec.toMap());
            }
            //endregion Описание тестов/Параметры тестов, операций залога
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

        //Запуск всех тестов в списке
        //Запуск в цикле
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
//             Assert.assertEquals(85.68,Z.getSummPercent(),0.00001);

            Z.saveZalog();
            Z.closeZalog();

           //Z.getSummPercentDB(); проценты из базы

           //region Продление
           if (!t.get("СreatePerezalog").equals("0")){
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

               System.out.println("qqqq="+P.getSumPersentForUseDB());

           }
           //endregion
           //region Выкуп

          if(!t.get("СreateVykup").equals("0")) {
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
