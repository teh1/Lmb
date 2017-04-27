package Operation;

import Common.Common;
import Common.DataBase;
import org.sikuli.script.*;

import Common.Driver;

import java.util.List;


public class Perezalog extends Driver {

    private Region PerezalogRG;
    private String NumberZalog = "";
    private int IDPerezalog = -1;
    //private String NumberPerezalog;

    public void Init(){
        try {

            Region HeadPerezalogRG, NumberPerezalogRG;
            Pattern PerezalogPT;

            HeadPerezalogRG =getDriver().wait((new Pattern(path("Perezalog\\F_HeadPerezalog"))).similar((float) 0.7),20);
            HeadPerezalogRG = HeadPerezalogRG.find(new Pattern(path("Perezalog\\T_TitlePerezalog")));
            HeadPerezalogRG.hover();
            HeadPerezalogRG.mouseDown(Button.LEFT);
            HeadPerezalogRG.mouseMove(-1*HeadPerezalogRG.getX()+17,-1*HeadPerezalogRG.getY()+90);
            HeadPerezalogRG.mouseUp(Button.LEFT);

            PerezalogPT = new Pattern(path("Perezalog\\F_Perezalog"));
            PerezalogRG = getDriver().wait(PerezalogPT.similar((float) 0.65),5);

            /*NumberPerezalogRG = PerezalogRG.find(new Pattern(path("Perezalog\\E_NumberZalog")));

            NumberPerezalogRG = NumberPerezalogRG.right(NumberPerezalogRG.getW() + 30);
            NumberPerezalogRG.click();
            NumberPerezalog = Common.getEditValue(NumberPerezalogRG);*/

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void Open(String NumberZalog) throws FindFailed {
        if (NumberZalog == null || NumberZalog.isEmpty() ) return;
        this.NumberZalog = NumberZalog;
        Pattern InputNumberZalogPT;
        Region InputNumberZalogRG;

        InputNumberZalogPT = new Pattern(path("Perezalog\\F_InputNumberZalog"));
        InputNumberZalogRG = getDriver().wait(InputNumberZalogPT.similar((float) 0.7),20);

        String[] InputText = NumberZalog.split(" ");

        InputNumberZalogRG.type(Common.toEnglish(InputText[0]));
        InputNumberZalogRG.type(Key.TAB);
        InputNumberZalogRG.type(Common.toEnglish(InputText[1]));
        InputNumberZalogRG.type(Key.ENTER);
    }


    public void setCountPeriods(Integer count){
        Region SumsRG, CountProlongation;
        Common.setClipboardContents(String.valueOf(count)); // в буфер
        try {
            SumsRG = PerezalogRG.find(new Pattern(path("Perezalog\\F_Sums")));
            CountProlongation = new Region(SumsRG.getTopRight().x-20, SumsRG.getTopRight().y-20, 20, 15);
            CountProlongation.click();
            Common.ClearEdit(CountProlongation);
            Common.Paste(CountProlongation);
            //CountProlongation.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        /* subSessiaRG.setRaster(2, 1);
            return subSessiaRG.getCell(num, 0);*/
    }

    public void setDateReturn(String aDate){
        Region DatesRG, DateReturnRG;
        Common.setClipboardContents(aDate);
        try {
            DatesRG = PerezalogRG.find(new Pattern(path("Perezalog\\F_Dates")));
            DatesRG.setRaster(4,1);
            DateReturnRG = DatesRG.getCell(3,0);
            DateReturnRG.setRaster(1,2);
            DateReturnRG = DateReturnRG.getCell(0,1);
            DateReturnRG.click();
            Common.ClearEdit(DateReturnRG);
            Common.Paste(DateReturnRG);
            DateReturnRG.type(Key.ENTER);


        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        /* subSessiaRG.setRaster(2, 1);
            return subSessiaRG.getCell(num, 0);*/
    }

    public double getSumPersentForUse(){
        Region SumsRG, SumPersentUseRG;
        try {

            //Если оплата процентов в конце
            SumsRG = PerezalogRG.find(new Pattern(path("Perezalog\\F_Sums")));
            SumsRG.setRaster(4,1);
            SumPersentUseRG = SumsRG.getCell(3,0);
            SumPersentUseRG.click();
            return Double.parseDouble(Common.getEditValue(SumPersentUseRG));
                    //Float.parseFloat(Common.getEditValue(SumPersentUseRG));
            //если оплата сразу
            //на форме это другое поле

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return -1;
    }

    public double getSumPersentForUseDB(){
        String[] InputText = NumberZalog.split(" ");

        DataBase Base = new DataBase();
        Base.Connect();

        List<String[]> rs;
        rs = Base.Query("select \"TalmbPerezlog\".\"SummaByProdlenie\"\n" +
                "  from \"TalmbZalog\"\n" +
                "  left join \"TalmbNumberDog\" on \"TalmbZalog\".\"NumberDog\" = \"TalmbNumberDog\".ID\n" +
                "  left join \"TalmbPerezlog\" on \"TalmbPerezlog\".\"TalmbZalog_ID\" = \"TalmbZalog\".id\n" +
                "  WHERE \"TalmbNumberDog\".\"DogNum\" ="+InputText[1]+" and \"TalmbNumberDog\".\"DogSeria\" = '"+InputText[0]+"'");
        Base.Closed();

        return Double.parseDouble(rs.get(0)[0]);

    }

    public void savePerezalog() {
        Region ButtonSaveRG;
        try {
            ButtonSaveRG = PerezalogRG.find(new Pattern(path("Perezalog\\B_Save")));
            ButtonSaveRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataBase b = new DataBase();
        b.Connect();
        List<String[]> rs = b.Query("select first 1 \"TalmbPerezlog\".id from \"TalmbPerezlog\" order by \"TalmbPerezlog\".id desc");
        b.Closed();
        if (rs.size() >= 1) {
            IDPerezalog = Integer.parseInt(rs.get(0)[0]);
        }
    }

    public void saveIDPerezalogToBaseFactResult(int NumberOfTest) {
        DataBase b = new DataBase();
        b.Connect();
        b.QueryUpdate("update \"TestsList\" set \"ID_Perezalog\" = "+IDPerezalog+" where \"NumberTest\" = "+NumberOfTest);
        b.Closed();
    }

    public void closePerezalog() {
        Region ButtonCloseRG;
        try {
            ButtonCloseRG = PerezalogRG.find(new Pattern(path("Perezalog\\B_Closed")));
            ButtonCloseRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

   /* public void setDateReturn(String StrDateReturn)  {
        int n;
        if (StrDateReturn == null) StrDateReturn = "currentdate";

        //SetWorkDate();
        // Calendar calendar = new GregorianCalendar(2016,Calendar.FEBRUARY,28);
        Calendar DateReturn = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); //, Locale.JAPAN
        try {
            DateReturn.setTime(sdf.parse(StrDateReturn));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        n = (int)((DateReturn.getTimeInMillis() - WorkDate.getTimeInMillis())/(1000*60*60*24));
        System.out.println(n);

        // setCountPeriod(n);
    }
    }*/

}
