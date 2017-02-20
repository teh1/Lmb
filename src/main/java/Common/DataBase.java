package Common;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    private java.sql.Connection conn = null;
    private java.sql.Statement stmt = null;
    private java.sql.ResultSet rs = null;
    private java.sql.Driver driver = null;

    public void Connect() {

        try {
            //Загрузка драйвера
            Class.forName("org.firebirdsql.jdbc.FBDriver");//.newInstance();

            //String strDatabasePath= "d:/Base/Lombard/22Kr/22_12.06.2016.GDB"; //добавить в комоон статик
            String strDatabasePath="d:\\Base\\Lombard\\TestBase\\Base.GDB";
            //String strDatabasePath="d:/Base/Lombard/51/server/test_VER12.GDB";
            String strConnect = "jdbc:firebirdsql://127.0.0.1:3050/"+strDatabasePath+"?encoding=WIN1251";

            // driver = DriverManager.getDriver(strConnect);
            // установка соединения
            conn = DriverManager.getConnection(strConnect, "SYSDBA", "masterkey");
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.logError(DataBase.class.getName()+"\n"+ex.getMessage());
            System.out.println(DataBase.class.getName()+"\n"+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException ex) {
               // Logger.logError(DataBase.class.getName()+"\n"+ex.getMessage());
                System.out.println(DataBase.class.getName()+"\n"+ex.getMessage());
            }
        }
    }

    public List<String[]> Query(String query) {
       // conn.setAutoCommit(false);
        ResultSetMetaData MD;
        int colmn = 0;
        //список масивов, элемент списка = масиву - одной строчке рекзульата запроса
        List<String[]> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            if (query == null) return list;
            rs = stmt.executeQuery(query);
            //полученние метаданных
            MD = rs.getMetaData();
            //Количество колонок в результате запроса, для создания массива
            colmn = MD.getColumnCount();
            int i=0;
            while(rs.next()){
                list.add(new String[colmn]); //добавляем новые элемент(масив) в список
                for(int j=0; j<colmn; j++)
                    list.get(i)[j] = rs.getString(j+1);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //rs.getFetchSize() не работает с процедурами.., только таблици и то не факт
        //rs.last() не работает на фаерберде
        return list;
    }


    public List<Map<String, String>> QueryMD(String query) {
        // conn.setAutoCommit(false);
        ResultSetMetaData MD;
        int colmn = 0;
        //список масивов, элемент списка = масиву - одной строчке рекзульата запроса
        //List<String[]> list = new ArrayList<>();
        Map<String,String> rows = new HashMap<String, String>();
        List<Map<String, String>> ListOfRows = new ArrayList<Map<String, String>>();
        try {
            stmt = conn.createStatement();
            if (query == null) return null;
            rs = stmt.executeQuery(query);
            //полученние метаданных
            MD = rs.getMetaData();
            //Количество колонок в результате запроса, для создания массива
            colmn = MD.getColumnCount();
            int i=0;
            while(rs.next()){

                for(int j=1; j<=colmn; j++)
                    rows.put(MD.getColumnName(j),rs.getString(j));

                ListOfRows.add(new HashMap<String, String>(rows));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //rs.getFetchSize() не работает с процедурами.., только таблици и то не факт
        //rs.last() не работает на фаерберде
        return ListOfRows;
    }

    public int QueryUpdate(String query) {
        // conn.setAutoCommit(false);
        int res = -1;

        try {
            stmt = conn.createStatement();
            if (query == null) return -1;
            res = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void Closed() {
        try{
            if (conn != null) conn.close();
            if (stmt != null) stmt.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


