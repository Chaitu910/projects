package com.example.Member_Enrollment_at_POS;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
public class PostReqForEnrollAs_Prospect {
	public String Enrollmemember(String body) {
		LoginToken lg=new LoginToken();
		String token=null;
		try {
			token=lg.Gettoken();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	HttpRequest loginRequest = null;
	try {
		 loginRequest = HttpRequest.newBuilder()
				 .uri(new URI("https://api.gravtee.com/v1/members/"))
				 .headers("Authorization",token,"x-api-key", "JgxrFqZvkD4fcTX7vgMOX1L1i58TkM65qkLfijj0")
				 .POST(BodyPublishers.ofString(body))
				 .build();
	}
	catch (URISyntaxException e1) {
		e1.printStackTrace();
	}
	HttpResponse<String> membercreate = null;
	HttpClient httpclient = HttpClient.newHttpClient();
	
		try {
			membercreate = httpclient.send(loginRequest, BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	catch (IOException e1) {
		e1.printStackTrace();
	}
	return membercreate.body();
	}
}
