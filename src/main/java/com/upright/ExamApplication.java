package com.upright;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@RestController
@SpringBootApplication
public class ExamApplication {
//	private ConnectInfo user1 = new ConnectInfo(73231, "086c18a83580d802ae4483ace0b468b9");
//	private ConnectInfo user2 = new ConnectInfo(2891, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvZGVkQHVwcmlnaHRwb3NlLmNvbSIsImlzcyI6IkZsZXhpQ29yZSIsImlhdCI6MTUyOTMxNTkzMywiZXhwIjoyNDc2MDg3MTMyfQ.dvVQ7hy3n3U-7gVKGedQXd-bQQlZpw863esx9wZYlZlIsmNBPdggaTFugsi2z4ucG0dswlgkho_pAtg88XK5oA");

	@RequestMapping(value = "/")
	public String available(@RequestParam String userId, @RequestParam String userToken) throws IOException {
		String response = RequestToUpright(userId, userToken);
		Result result = ParseJsonStringResponseToResultObject(response);

		ArrayList<Interval> filteredIntervals = new ArrayList<>();
		for (Interval interval : result.getIntervals().getIntervals()) {
			if(!filteredIntervals.contains(interval)){
				filteredIntervals.add(interval);
			}
		}

		String toResponse = "Before filter:"+result.getIntervals().getIntervals().size()+"\nAfter filtered:"+filteredIntervals.size();

		return toResponse;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ExamApplication.class, args);
	}

	private static Result ParseJsonStringResponseToResultObject(String response) {
		Gson gson = new Gson();
		Result result = gson.fromJson(response.toString(), Result.class);
		return result;
	}

	private static String RequestToUpright(String userId, String userToken) throws IOException {
		URL obj = new URL("https://qa.upright.cloud/UpRightServices/api/GetIntervalsGo");

		//TODO: Use serialization by ConnectInfo entity

		//Doent work! why?
		JSONObject connectInfo = new JSONObject();
		connectInfo.put("userId", userId);
		connectInfo.put("userToken", userToken);
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("connectInfo", connectInfo);

		String params = jsonParams.toJSONString();

		String params2 = "{\"connectInfo\":{" +
				"\"userId\":\""+73231+"\", " +
				"\"userToken\":\""+"086c18a83580d802ae4483ace0b468b9"+"\"}" +
			"}";

		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);

		OutputStream os = postConnection.getOutputStream();
		os.write(params2.getBytes());
		os.flush();
		os.close();

		System.out.println("POST Response Code :  " + postConnection.getResponseCode());
		System.out.println("POST Response Message : " + postConnection.getResponseMessage());

		BufferedReader in = new BufferedReader(new InputStreamReader(
				postConnection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in .readLine()) != null) {
			response.append(inputLine);
		} in .close();

		return response.toString();
	}

}
