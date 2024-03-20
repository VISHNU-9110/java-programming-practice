import java.io.*;
import java.sql.*;

class Dser {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db?characterEncoding=utf8","root","");
			String sql = "SELECT serialized_object FROM serialisation WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,1);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				byte[] serObj = rs.getBytes("serialized_object");
				ByteArrayInputStream bais = new ByteArrayInputStream(serObj); 
				ObjectInputStream in = new ObjectInputStream(bais);
				Object deserializedObject = in.readObject();

				if(deserializedObject instanceof Student) {
					Student s = (Student) deserializedObject;
					System.out.println("Deserialisation succefull");
					s.disp();
				}
				else {
					System.out.println("Error: Object retrieved from database is not of expected type.");

				}
			}else {
                System.out.println("No serialized object found in the database.");
            }
			
			pstmt.close();
			con.close();
			
		}
		catch(Exception e) {
			System.out.println("Error : " + e);
		}
		
	}
}