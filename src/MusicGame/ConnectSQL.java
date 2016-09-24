package MusicGame;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.*;

public class ConnectSQL {
	
	String connectionString =	   
			"jdbc:sqlserver://musicgame.database.windows.net:1433;"
	        + "database=MusicGame;"
	        + "user=gameadmin@musicgame;"
	        + "password=AUT12345!;"
	        + "encrypt=true;"
	        + "trustServerCertificate=false;"
	        + "hostNameInCertificate=*.database.windows.net;"
	        + "loginTimeout=30;";
    Connection conn;
    Statement statement;
    ResultSet rs;
    
    
    public ConnectSQL() throws SQLException{
    	conn = DriverManager.getConnection(connectionString);
    	statement = conn.createStatement();
    }

    public boolean setTable() throws SQLException{
    	try{
    	
        String command = "CREATE TABLE ChallengeStore(PLAYER_NAME VARCHAR(20), TIME VARCHAR(20),CHALLENGE VARCHAR(20))";
        statement.executeUpdate(command);
        statement.executeUpdate("INSERT INTO ChallengeStore VALUES ('weiweima','12345','good')");
        statement.executeUpdate("INSERT INTO ChallengeStore VALUES ('renzhichu','12345','nice')");
        
    	}
    	catch(Exception e){
    		return false;
    	}
    	return true;
    }
    
    public String getChallenge(String name) throws SQLException{
    	
        rs = statement.executeQuery("Select * FROM ChallengeStore");
        String x = "";

        while (rs.next()) {

            x = rs.getString("PLAYER_NAME");
            if(x.equals(name)){
            	return rs.getString(1)+rs.getString(2)+rs.getString(3);
            }
        }
		return null;
    	
    }
    
    public int add(String name,String time,String challenge) throws SQLException{

    	return statement.executeUpdate("INSERT INTO ChallengeStore VALUES ('"+name+"','"+time+"','"+challenge+"')");

    	}
    
	
	 public static void main(String[] args) throws SQLException {
		 
		 ConnectSQL connsql=new ConnectSQL();
		 connsql.setTable();
		 String str=connsql.getChallenge("weiweima");
		 System.out.println(str);
			int a=new ConnectSQL().add("1", "2", "3");
			System.out.println(a);

	}



}
