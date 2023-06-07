package com.example.Member_Enrollment_at_POS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/")
public class controller {
	public Jackson2ObjectMapperBuilder job() {
		Jackson2ObjectMapperBuilder build= new Jackson2ObjectMapperBuilder();
		build.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return build;
		
	}
	String msg;
	private static Connection coonect() {
		String jdbcurl="jdbc:sqlite:C:\\Users\\ctany\\OneDrive\\Desktop\\SQLiteStudio/apiProject.db";
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(jdbcurl);
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return conn;
	}	
	Activation_key_genarator a=new Activation_key_genarator();
	PostReqForEnrollAs_Prospect ob=new PostReqForEnrollAs_Prospect();
	EnrollAsActiveReq act= new EnrollAsActiveReq();
	@PostMapping(path = "v1/enrol-prospect")
	public ResponseEntity<String> postapi1(@RequestBody String data) throws SQLException {
		
			JSONParser pars = new JSONParser();
			JSONObject reqObj = null;
			try {
			reqObj = (JSONObject) pars.parse(data);
			} catch (ParseException e) {
				e.getMessage();
			}
			
		            String first_name =(String) reqObj.get("first_name");
		           
		            String last_name = (String) reqObj.get("last_name");
		            String emaill=(String) reqObj.get("email");
		            String date_of_birth=(String) reqObj.get("date_of_birth");
		            String mobilee = (String)  reqObj.get("mobile");
		            String adressline1=(String) reqObj.get("adressline1");
		            String adressline2=(String) reqObj.get("adressline2");
		            String country=(String) reqObj.get("country");
		    			Connection con=coonect();
		    			ResultSet rs = null;
		    			try {
		    				 PreparedStatement pst1=con.prepareStatement("SELECT * from member_tbl WHERE email=\""+emaill+"\";");
		    				 rs = pst1.executeQuery();
		    				 if(rs.next()){
		    						msg = "{\r\n"
		    								+ "    \"error_code\":\"EMAIL_ID MUST BE UNIQUE\",\r\n"
		    								+ "    \"erro_message\":\"EMAIL_ID  ALREADY EXIST\"\r\n"
		    								+ "}";
		    						return new ResponseEntity<String>(msg, HttpStatus.NOT_ACCEPTABLE);
		    						
		    					}
		    				 PreparedStatement pst2=con.prepareStatement("SELECT * from member_tbl WHERE mobile=\""+mobilee+"\";");
		    				 rs = pst2.executeQuery();
		    				 if(rs.next()){
		    						msg = "{\r\n"
		    								+ "    \"error_code\":\" MOBILE NUMBER MUST BE UNIQUE\",\r\n"
		    								+ "    \"erro_message\":\"MOBILE NUMBER IS ALREADY EXIST\"\r\n"
		    								+ "}";
		    						return new ResponseEntity<String>(msg, HttpStatus.NOT_ACCEPTABLE);
		    				 }
		    				
		    				
		    				
		    				 con.close();
		    		}
		    		catch (SQLException ex) {
		    			ex.getMessage();
		    		}
		    		
		           
		            String bo="{\r\n"
					+ "  \"user\": {\r\n"
					+ "    \"email\": \""+emaill+"\",\r\n"
					+ "    \"first_name\": \""+first_name+"\",\r\n"
					+ "    \"last_name\": \""+last_name+"\"\r\n"
					+ "  },\r\n"
					+ "  \"profile_image\": null,\r\n"
					+ "  \"salutation\": null,\r\n"
					+ "  \"member_name\": null,\r\n"
					+ "  \"middle_name\": \"sdk\",\r\n"
					+ "  \"alias\": null,\r\n"
					+ "  \"date_of_birth\":\"1999-01-01\",\r\n"
					+ "  \"gender\": null,\r\n"
					+ "  \"marital_status\": null,\r\n"
					+ "  \"wedding_anniversary\": null,\r\n"
					+ "  \"number_of_children\": null,\r\n"
					+ "  \"mother_tongue\": null,\r\n"
					+ "  \"nationality\": null,\r\n"
					+ "  \"pin\": null,\r\n"
					+ "  \"passport_number\": null,\r\n"
					+ "  \"creditcard_number\": \"1111111111111111\",\r\n"
					+ "  \"mobile\": \""+mobilee+"\",\r\n"
					+ "  \"address_line1\": \""+adressline1+"\",\r\n"
					+ "  \"address_line2\": \""+adressline2+"\",\r\n"
					+ "  \"area\": null,\r\n"
					+ "  \"city\": null,\r\n"
					+ "  \"region\": null,\r\n"
					+ "  \"zipcode\": null,\r\n"
					+ "  \"country\": \""+country+"\",\r\n"
					+ "  \"ethnicity\": null,\r\n"
					+ "  \"annual_income\": null,\r\n"
					+ "  \"highest_education\": null,\r\n"
					+ "  \"company\": null,\r\n"
					+ "  \"job_title\": null,\r\n"
					+ "  \"favorite_store\": null,\r\n"
					+ "  \"favorite_restaurant\": null,\r\n"
					+ "  \"mode_of_communication\": null,\r\n"
					+ "  \"food_preference\": null,\r\n"
					+ "  \"favorite_drink\": null,\r\n"
					+ "  \"favorite_sport\": null,\r\n"
					+ "  \"favorite_food\": null,\r\n"
					+ "  \"hobbies\": null,\r\n"
					+ "  \"enrollment_touchpoint\": null,\r\n"
					+ "  \"enrolling_sponsor\": 1,\r\n"
					+ "  \"enrolling_location\": null,\r\n"
					+ "  \"preferred_location\": null,\r\n"
					+ "  \"associate_id\": null,\r\n"
					+ "  \"facebook_id\": null,\r\n"
					+ "  \"twitter_id\": null,\r\n"
					+ "  \"receive_offers\": true,\r\n"
					+ "  \"membership_tenure\": 0,\r\n"
					+ "  \"extra_data\": {}\r\n"
					+ "}";
		           
		String responseBody = ob.Enrollmemember(bo);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
		jsonObj = (JSONObject) parser.parse(responseBody);
		
		} catch (ParseException e) {
			e.getMessage();
		}
	            String member_id =(String) jsonObj.get("member_id");
	            String membership_stage = "Prospect";
	           
	          
	           Connection coon1=coonect();
	            PreparedStatement pst=coon1.prepareStatement("INSERT INTO member_tbl VALUES (?, ?, ?, ?, ?)");
	          
	            String activation_key=a.randomString(6);
	            pst.setString(1,member_id);
				pst.setString(2,emaill);
				pst.setString(3,mobilee);
				pst.setString(4,activation_key);
				pst.setString(5,membership_stage);
				pst.executeUpdate();
				
				msg="{\r\n"
						+ " “member_id”:\""+member_id+"\" -Gravity Member ID\r\n"
						+ " “status”:\""+membership_stage+"\" -ProspectStage\r\n"
						+ "  “activation_key”:\""+activation_key+"\" //– random 6 digit Number\r\n"
						+ "}";	
				pst.close();
				coon1.close();
				
				 return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@PostMapping(path = "v1/activate-prospect/")
	public ResponseEntity<String> postapi2(@RequestBody String data) throws SQLException {
		
		JSONParser parser = new JSONParser();
		JSONObject reqObj = null;
		try {
		reqObj = (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			e.getMessage();
		}
		String member_id =(String) reqObj.get("member_id");
		String active_key =(String) reqObj.get("activation_key");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00");
		LocalDateTime now = LocalDateTime.now().minusHours(5).minusMinutes(30);
		String currenttime=df.format(now);
		System.out.println(currenttime);
		Connection con=coonect();
		ResultSet rs = null;
		try {
			
			 PreparedStatement pst1=con.prepareStatement("SELECT * from member_tbl WHERE member_id=\""+member_id+"\";");
			 rs = pst1.executeQuery();
			 if(!rs.next()){
					msg = "{\r\n"
							+ "    \"error_code\":\"INVALID MEMBER_ID\",\r\n"
							+ "    \"erro_message\":\"PLEASE ENTER THE CORRECT MEMBER_ID\"\r\n"
							+ "}";
					return new ResponseEntity<String>(msg, HttpStatus.NOT_ACCEPTABLE);
			 }
			 PreparedStatement pst2=con.prepareStatement("SELECT * from member_tbl WHERE activation_key=\""+active_key+"\";");
			 rs = pst2.executeQuery();
			 if(!rs.next()){
					msg = "{\r\n"
							+ "    \"error_code\":\"INVALID ACTIVATION_KEY\",\r\n"
							+ "    \"erro_message\":\"PLEASE ENTER THE CORRECT ACTIVATION_KEY\"\r\n"
							+ "}";
					return new ResponseEntity<String>(msg, HttpStatus.NOT_ACCEPTABLE);
			 }
					
		}
		catch (SQLException e) {
			e.getMessage();
		}
		try {
			String sendBody = "{\r\n"
					+ "    \"h_membership_stage_id\":\"1\",\r\n"
					+ "    \"h_comment\":\"\",\r\n"
					+ "    \"h_program_id\":260,\r\n"
					+ "    \"h_bit_date\":\""+currenttime+"\",\r\n"
					+ "    \"h_member_id\":\""+member_id+"\",\r\n"
					+ "    \"h_bit_category\":\"SERVICE\",\r\n"
					+ "    \"h_bit_type\":\"MEMBERSHIP_CHANGE\",\r\n"
					+ "    \"h_sponsor_id\":1\r\n"
					+ "}";
			System.out.println(sendBody);
			PreparedStatement pst=con.prepareStatement("SELECT * from member_tbl WHERE member_id=\""+member_id+"\";");
			 rs = pst.executeQuery();
			
			 String key = rs.getString("activation_key");
			 System.out.println(key);
			
			
			 if(rs.next()){
				 if(rs.getString("status").equals("Prospect")) {
					
					if(key.equals(active_key)) {
						try {
						PreparedStatement pstmt=con.prepareStatement("UPDATE member_tbl SET status=\"Active\" WHERE member_id=\""+member_id+"\";" );
			
						act.EnrollmememberAsActive(sendBody);
						System.out.println(sendBody);
						pstmt.executeUpdate();
						
						msg="{\r\n"
								+ "  \"member_id\" : \""+member_id+ "\",\r\n"
								+ "  \"status\":\"ACTIVE\r\n"
								+ "}\r\n"
								+ "";
						
						con.close();
						
						return new ResponseEntity<String>(msg,HttpStatus.OK);
						}
						catch (SQLException e1) {
							e1.getMessage();
							// TODO: handle exception
						}
						
					}
					else {
						msg="{\r\n"
								+ "  “error_code”:”INVALID_KEY”,\r\n"
								+ "  “error_message”: “ENTERED KEY IS NOT A VALID ONE”\r\n"
								+ "}";
						return new ResponseEntity<String>(msg,HttpStatus.NOT_ACCEPTABLE);
					}
					
				}
				 else {
					 msg="{\r\n"
					 		+ "  “error_code”:”MEMBER ALREADY EXIST”,\r\n"
					 		+ "  “error_message”: “MEMBER IS ALREADY IN ACTIVE STAGE”\r\n"
					 		+ "}";
					 return new ResponseEntity<String>(msg,HttpStatus.NOT_ACCEPTABLE);
				 }
		}
	}
	
	catch (SQLException ex) {
		ex.getMessage();
	}
	
		msg="{\r\n"
		 		+ "  “error_code”:”ALL THE DETAILS ARE MANDATORY”,\r\n"
		 		+ "  “error_message”: “DETAILS ARE MISSING”\r\n"
		 		+ "}";
		return new ResponseEntity<String>(msg,HttpStatus.NOT_ACCEPTABLE);
	}
}