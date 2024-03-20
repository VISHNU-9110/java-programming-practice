import java.io.*;
import java.sql.*;

class ser {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?characterEncoding=utf8","root","");

			Student s1 = new Student (101 , "vishnu");
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(s1);
			byte[] serObj = baos.toByteArray();
			
			String sql = "INSERT INTO serialisation (serialized_object) VALUES (?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setBytes(1,serObj);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
			System.out.println("Object serialized and stored in the database successfully.");
		}
		catch(Exception e) {
			System.out.println("Error : " + e);
		}
		
	}
}