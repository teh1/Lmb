package Operation;

import Common.Common;
import Common.DataBase;
import Common.Driver;

import static Common.Common.WorkDate;

import org.sikuli.script.*;
import org.sikuli.script.Button;

import java.net.URISyntaxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Random;

public class Zalog extends Driver {
    //private final float SEC = 0.5f;

    private Region ZalogRG;
    private Pattern ZalogPT;
    private int TalmbMaterials_ID;
    private int TalmbAlgorithm_ID = -1;
    private int GroupMaino_ID = -1; //заглушка, это покамесь не айди
    private  int IDZalog = -1;
    private String NumberZalog;
    //private int IDZalog = 0;

    public void SetDiscountCardNumber() {
        //нужно дописать зачем не знаю. рандомная карточка что ли...
        Region CardNumberRG = null;
        try {
            CardNumberRG = ZalogRG.find(new Pattern(path("Zalog\\E_DiscountCardNumber")));
            CardNumberRG.click();
            Common d = new Common();
            //CardNumberRG.type(d.toEnglish("Андрієць О"));
            CardNumberRG.type(",euftyrj");

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        ZalogRG.highlight(2);
    }

    public void SetDiscountCardNumber(String CardNumber) {
        if (CardNumber==null || CardNumber.isEmpty()) return;
        Region CardNumberRG, CardConfirmationRG;
        try {
            CardNumberRG = ZalogRG.find(new Pattern(path("Zalog\\E_DiscountCardNumber")));
            CardNumberRG.click();
            CardNumberRG.type(CardNumber);
            CardConfirmationRG = CardNumberRG.find(new Pattern(path("Zalog\\B_CardConfirmation")));
            CardConfirmationRG.click();

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

    }

    public Zalog() throws FindFailed, URISyntaxException {
        ZalogPT = new Pattern(path("Zalog\\f_Zalog"));
    }

    public void Init() {
        try {

            Region HeadZalogRG, NumberZalogRG;
            HeadZalogRG = getDriver().wait((new Pattern(path("Zalog\\F_HeadZalog"))).similar((float) 0.7), 20);
            HeadZalogRG = HeadZalogRG.find(new Pattern(path("Zalog\\T_TitleZalog")));
            HeadZalogRG.hover();
            HeadZalogRG.mouseDown(Button.LEFT);
            HeadZalogRG.mouseMove(-1 * HeadZalogRG.getX() + 17, -1 * HeadZalogRG.getY() + 90);
            HeadZalogRG.mouseUp(Button.LEFT);

            ZalogRG = getDriver().wait(ZalogPT.similar((float) 0.7), 5);

            NumberZalogRG = ZalogRG.find(new Pattern(path("Zalog\\E_NumberZalog")));

            NumberZalogRG = NumberZalogRG.right(NumberZalogRG.getW() + 30);
            NumberZalogRG.click();
            NumberZalog = Common.getEditValue(NumberZalogRG);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    //Observe
    public void stop() {
        System.out.println("1111111111111111");
        ZalogRG.stopObserver();
    }

    public void test() {
        ZalogRG.onChange(new ObserverCallBack() {
            @Override
            public void changed(ObserveEvent event) {
                System.out.println("qqqqqqqqqqqq = " + event.getType());
                List<Match> rrr = event.getChanges();
                rrr.get(0).highlight(1);
                // event.getRegion().highlight(1);
                //ZalogRG.wait(2.0);
            }
        });
        ZalogRG.observe(60);//observeInBackground(60);

       /* ObserverCallBack event = new ObserverCallBack(){
            @Override
            public void changed(ObserveEvent e) {
                System.out.println("Video playing");
                ZalogRG.wait(1.0);
            }
        };

        ZalogRG.onChange(50, event); // When there is min 50 pixel change in the region, call event
        ZalogRG.observe(); // Observe foreve
*/
    }

    public void SelectFIO() {
        Region fioRG;
        try {
            fioRG = ZalogRG.find(new Pattern(path("Zalog\\E_FIO")));

            fioRG = fioRG.right(fioRG.getW() * 4);
            fioRG.click();

            DataBase Base = new DataBase();
            Base.Connect();

            List<String[]> rs;

            rs = Base.Query("select ID, \"Fam\"||\' \'||\"Imja\"||\' \'||\"Otc\" from \"PrFizLicList\"(null, 451) order by \"Fam\",\"Imja\",\"Otc\"");
            Base.Closed();

            //Common с = new Common();
            Random random = new Random();
            fioRG.type(Common.toEnglish(rs.get(random.nextInt(rs.size()))[1]));
            fioRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectFIO(String like) {
        Region fioRG;
        DataBase Base;
        try {
            fioRG = ZalogRG.find(new Pattern(path("Zalog\\E_FIO")));

            fioRG = fioRG.right(fioRG.getW() * 4);
            fioRG.click();

            Base = new DataBase();
            Base.Connect();

            List<String[]> rs;

            rs = Base.Query("select first 1 ID, \"Fam\"||\' \'||\"Imja\"||\' \'||\"Otc\" from \"PrFizLicList\"(null, 451) where \"Fam\" like '" + like + "%' order by \"Fam\",\"Imja\",\"Otc\"");
            Base.Closed();
            fioRG.type(Common.toEnglish(rs.get(0)[1]));
            fioRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectAlgorithmKredit(String name) {
        if (TalmbAlgorithm_ID != -1) return;
        Region AlgorithmRG;
        List<String[]> rs;

        try {
            Pattern a = new Pattern(path("Zalog\\E_AlgorithmKredita"));
            AlgorithmRG = ZalogRG.find(a);

            AlgorithmRG = AlgorithmRG.right(AlgorithmRG.getW() * 5);
            AlgorithmRG.click();

            Common.ClearEdit(AlgorithmRG);

            DataBase b = new DataBase();
            b.Connect();
            rs = b.Query(" select  \"TalmbAlgorithm\".id, \"TalmbAlgorithm\".\"Name\" from \"TalmbAlgorithm\" " +
                    "      left join \"TalmbAlgorithmParam\" on \"TalmbAlgorithmParam\".\"IDTalmbAlgorithm\" = \"TalmbAlgorithm\".id " +
                    "      where \"TalmbAlgorithmParam\".\"IsUsed\" = 1 " +
                    "      and \"TalmbAlgorithm\".\"Name\" = '" + name + "'");

            //System.out.println("11111" + name);
            b.Closed();
            if (rs.size() >= 1) {
                TalmbAlgorithm_ID = Integer.parseInt(rs.get(0)[0]);
                AlgorithmRG.type(Common.toEnglish(rs.get(0)[1]));
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AlgorithmRG.type(Key.ENTER);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void setCountPeriod(int n) {
        Region KolPeriod;
        int i;

        try {
            KolPeriod = ZalogRG.find(new Pattern(path("Zalog\\E_KolPeriod")));
            i = KolPeriod.getW() / 2 + 1;

            KolPeriod.setX(KolPeriod.getX() + i);
            KolPeriod.setW(i);
            //переприсваиваем новый регион, без этого клик срабатывает по LastMatch - старые, не изменный регион.
            KolPeriod = new Region(KolPeriod);

            KolPeriod.click(KolPeriod);

            boolean on;
            //выкл/вкл NumLock так как коммбинация SHIFT +HOME не работает с включенным NumLock
            on = (Key.isLockOn('\ue03b'));
            if (on)
                KolPeriod.type(Key.NUM_LOCK);

            KolPeriod.type(Key.HOME, Key.SHIFT);
            KolPeriod.type(Key.BACKSPACE);
            KolPeriod.type(String.valueOf(n));

            if (on)
                KolPeriod.type(Key.NUM_LOCK);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void setDateReturn(String StrDateReturn) {
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

        n = (int) ((DateReturn.getTimeInMillis() - WorkDate.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        System.out.println(n);

        // setCountPeriod(n);

    }

    public void AddMaino() {
        try {
            ZalogRG.find(new Pattern(path("Zalog\\B_Add"))).click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectGroupMaino(String str) {
        Region Groups, GroupName = null;
        if (GroupMaino_ID != -1) return;
        try {
            Groups = getDriver().find(new Pattern(path("GroupMaino\\f_GroupMaino")));
            Groups = Groups.below(Groups.getH() * 1);

            if (str.equals("Техника")) {
                GroupName = Groups.find(new Pattern(path("GroupMaino\\Technics")));
                GroupMaino_ID = 0;
            }
            if (str.equals("Драг.Металл")) {
                GroupName = Groups.find(new Pattern(path("GroupMaino\\DragMetall")));
                GroupMaino_ID = 1;
            }
            GroupName.doubleClick();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectGroupMaino() {
        Region Groups, GroupName;
        if (GroupMaino_ID != -1) return;
        try {

            Groups = getDriver().find(new Pattern(path("GroupMaino\\f_GroupMaino")));
            Groups = Groups.below(Groups.getH() * 1);
            GroupName = Groups.find(new Pattern(path("GroupMaino\\DragMetall")));
            GroupMaino_ID = 1;
            GroupName.doubleClick();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void SelectMaino(String NameMaino) {
        ZalogRG.type(Common.toEnglish(NameMaino));
        ZalogRG.type(Key.ENTER);
    }

    public void SelectMaterial(String Name) {
        List<String[]> rs;
        DataBase b;

        Name = Name.toLowerCase();
        b = new DataBase();
        b.Connect();

        rs = b.Query("select \"TalmbMaterials\".id, \"TalmbMaterials\".\"MaterialName\" " +
                "from \"TalmbMaterials\" " +
                "where " +
                "lower(\"TalmbMaterials\".\"MaterialName\") like '" + Name + "%'");
        b.Closed();

        TalmbMaterials_ID = Integer.parseInt(rs.get(0)[0]);


        ZalogRG.type(Common.toEnglish(Name));
        ZalogRG.type(Key.ENTER);
    }

    public void SelectProba(String ProbaName, String Suffix) {
        Region SelectProbaRG, ProbaNameRG;
        List<String[]> rs;
        DataBase b;
        String likeSuffix = "";
        try {
            if (Suffix != "") likeSuffix = "and \"Suffix\" like '" + Suffix + "%'";

            b = new DataBase();
            b.Connect();
            rs = b.Query("select distinct \"ProbeName\", \"Suffix\" from \"ViADlmbMaterialPrice\"" +
                    " where \"TalmbMaterials_ID\"=" + TalmbMaterials_ID +
                    " and \"IDTalmbAlgorithm\"=" + TalmbAlgorithm_ID +
                    " and \"ProbeName\" like '" + ProbaName + "%' " + likeSuffix);
            b.Closed();
            System.out.println("asdsadsaasd==========" + rs.size());

            SelectProbaRG = getDriver().find(new Pattern(path("Zalog\\F_SelectProba")));
            ProbaNameRG = SelectProbaRG.find(new Pattern(path("Zalog\\E_Proba")));
            ProbaNameRG = ProbaNameRG.right(ProbaNameRG.getW() * 2);
            ProbaNameRG.click();
            ProbaNameRG.type(Common.toEnglish(ProbaName));

            ProbaNameRG.type(Key.TAB); //переход на суфикс

            if (Suffix != "") {
                ProbaNameRG.type(Common.toEnglish(Suffix));
                ProbaNameRG.type(Key.ENTER);
            }

            ProbaNameRG.type(Key.ENTER); // кнопка ОК

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void setAmount(int kol) {
        Region AmountRG;
        try {
            AmountRG = getDriver().find(new Pattern(path("Zalog\\F_Amount")));
            AmountRG = AmountRG.find(new Pattern(path("Zalog\\E_Amount")));
            AmountRG = AmountRG.right(AmountRG.getW());
            AmountRG.click();
            AmountRG.type(String.valueOf(kol));
            AmountRG.type(Key.ENTER);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public void setWeight(double weight) {
        Region WeightRG, NtWtRG;
        try {
            WeightRG = getDriver().find(new Pattern(path("Zalog\\F_Weight")));
            NtWtRG = WeightRG.find(new Pattern(path("Zalog\\E_NtWt")));
            NtWtRG = NtWtRG.right(NtWtRG.getW());
            NtWtRG.click();
            NtWtRG.type(Common.toEnglish(String.valueOf(weight)));
            NtWtRG.type(Key.ENTER);
            NtWtRG.type(Key.ENTER);

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

    public String getNumberZalog() {
        return NumberZalog;
    }

    public double getSummPercent() {
        Region SummPercentRG;
        try {
            SummPercentRG = ZalogRG.find(new Pattern(path("Zalog\\E_PercentSumm")));
            // SummPercentRG = SummPercentRG.right(SummPercentRG.getW()+2);
            //SummPercentRG.highlight(1);
            SummPercentRG.click();
            return Float.parseFloat(Common.getEditValue(SummPercentRG));

        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        return -1.0;
    }

    public double getSummPercentDB() { //factresult
        if (NumberZalog == null || NumberZalog.isEmpty() ) return -1;

        String[] InputText = NumberZalog.split(" ");

        DataBase Base = new DataBase();
        Base.Connect();

        List<String[]> rs;
        rs = Base.Query("select \"TalmbZalog\".\"SummaByUsingKredit\" from \"TalmbZalog\" " +
                "left join  \"TalmbNumberDog\" on \"TalmbZalog\".\"NumberDog\" = \"TalmbNumberDog\".id " +
                "where \"TalmbNumberDog\".\"DogNum\" ="+InputText[1]+" and \"TalmbNumberDog\".\"DogSeria\" = '"+InputText[0]+"'");
        Base.Closed();

        return Double.parseDouble(rs.get(0)[0]);
    }

    public int getIDZalog(){
         return IDZalog;
    }

    public void saveZalog() {
        Region ButtonSaveRG;
        try {
            ButtonSaveRG = ZalogRG.find(new Pattern(path("Zalog\\B_Save")));
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
        List<String[]> rs = b.Query("select first 1 \"TalmbZalog\".id from \"TalmbZalog\" order by \"TalmbZalog\".id desc");
        b.Closed();
        if (rs.size() >= 1) {
            IDZalog = Integer.parseInt(rs.get(0)[0]);
        }
    }

    public void saveIDZalogToBaseFactResult(int NumberOfTest) {
        DataBase b = new DataBase();
        b.Connect();
        b.QueryUpdate("update \"TestsList\" set \"ID_Zalog\" = "+IDZalog+" where \"NumberTest\" = "+NumberOfTest);
        b.Closed();

    }

    public void closeZalog() {
        Region ButtonCloseRG;
        try {
            ButtonCloseRG = ZalogRG.find(new Pattern(path("Zalog\\B_Closed")));
            ButtonCloseRG.click();
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }

}
