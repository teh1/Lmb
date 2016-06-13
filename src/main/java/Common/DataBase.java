package Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void Connect() {

        try {
            //Загрузка драйвера
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            // установка соединения
            String strDatabasePath= "d:/Base/Novozavodskoe/20160523/SPDB_20160519_march.GDB";
            //String strDatabasePath= "d:\\Base\\Novozavodskoe\\20160523\\SPDB_20160519_march.GDB";
            conn = DriverManager.getConnection("jdbc:firebirdsql:local:"+strDatabasePath , "SYSDBA", "masterkey");
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.logError(DataBase.class.getName()+"\n"+ex.getMessage());
            System.out.println("conn eror");
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException ex) {
               // Logger.logError(DataBase.class.getName()+"\n"+ex.getMessage());
                System.out.println("conn 22eror");
            }

        }
    }

    public void Query()  throws SQLException{
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select * from \"TaKGP\"");

        while(rs.next()){
            String userid = rs.getString("Name");
            String username = rs.getString("NameFull");

            System.out.println("userid : " + userid);
            System.out.println("username : " + username);
        }
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

