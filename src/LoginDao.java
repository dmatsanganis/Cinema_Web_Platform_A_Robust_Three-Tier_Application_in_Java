import java.sql.*;

public class LoginDao
{
	public static boolean validate(String username,String password,String role)
	{  //Validate boolean with two string type object initialize.

		boolean status=false;  //Initially boolean type variable, status is not enabled.

		try
		{  //A try statement.

		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/cinema","postgres","12345");  //Connection with the postgresql database with following credentials.

		if(role.equals("contentadmins")) {
			
			String salt=null;
			PreparedStatement ps1=con.prepareStatement("select salt from contentadmins where username=?;");
			ps1.setString(1,username);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())  //A while statement.
			{
				salt = rs1.getString(1);
			}
			rs1.close();
			ps1.close();  //Close the Prepared Statement variable, ps.
			
			String hashedpassword = Encryption.getHashMD5(password,salt);
			
			PreparedStatement ps=con.prepareStatement(
					"select * from contentadmins where username=? and hashedpassword=?");
					//Prepared Statement variable, ps selects all the content from the database, the "contentadmins" table and columns "username" and "password".
					ps.setString(1,username);  //Prepared Statement variable, ps sets the first string.
					ps.setString(2,hashedpassword);	 //Prepared Statement variable, ps sets the second string.
					ResultSet rs=ps.executeQuery();  //ResultSet valiable, rs gets the result from the execution of the "ps" query.
					status=rs.next(); //Boolean type variable, status checks if rs variable has any content.
		}
		else if(role.equals("admins")) {
			String salt=null;
			PreparedStatement ps1=con.prepareStatement("select salt from admins where username=?;");
			ps1.setString(1,username);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())  //A while statement.
			{
				salt = rs1.getString(1);
			}
			rs1.close();
			ps1.close();  //Close the Prepared Statement variable, ps.
			
			String hashedpassword = Encryption.getHashMD5(password,salt);
			PreparedStatement ps=con.prepareStatement(
					"select * from admins where username=? and hashedpassword=?");
					//Prepared Statement variable, ps selects all the content from the database, the "admins" table and columns "username" and "password".
					ps.setString(1,username);  //Prepared Statement variable, ps sets the first string.
					ps.setString(2,hashedpassword);	 //Prepared Statement variable, ps sets the second string.

					ResultSet rs=ps.executeQuery();  //ResultSet valiable, rs gets the result from the execution of the "ps" query.
					status=rs.next(); //Boolean type variable, status checks if rs variable has any content.
		}
		else {
			String salt=null;
			PreparedStatement ps1=con.prepareStatement("select salt from clients where username=?;");
			ps1.setString(1,username);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())  //A while statement.
			{
				salt = rs1.getString(1);
			}
			rs1.close();
			ps1.close();  //Close the Prepared Statement variable, ps.
			
			String hashedpassword = Encryption.getHashMD5(password,salt);
			PreparedStatement ps=con.prepareStatement(
					"select * from clients where username=? and hashedpassword=?");
					//Prepared Statement variable, ps selects all the content from the database, the "clients" table and columns "username" and "password".
					ps.setString(1,username);  //Prepared Statement variable, ps sets the first string.
					ps.setString(2,hashedpassword);	 //Prepared Statement variable, ps sets the second string.

					ResultSet rs=ps.executeQuery();  //ResultSet valiable, rs gets the result from the execution of the "ps" query.
					status=rs.next(); //Boolean type variable, status checks if rs variable has any content.
		}
		}
		catch(Exception e)
		{ //Catch statement.
			System.out.println(e); //Exception.
		}

		return status; //Returns boolean type variable, status.
		}
}
