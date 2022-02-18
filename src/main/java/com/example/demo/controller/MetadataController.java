package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Metadata;
import com.example.demo.pojo.AccessToken;
import com.example.demo.pojo.Root;
import com.example.demo.service.MetadataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/codechallenge")
@Validated
public class MetadataController {

	@Autowired
	MetadataService metaService;
	
	@Value("${clientId}")
	private String clientID;

	@Value("${clientSecret}")
	private String clientSecret;
	
	@Value("${accessTokenUrl}")
	private String accessTokenUrl;
	
	@Value("${trackUrl}")
	private String trackUrl;

	@PostMapping("/createTrack")
	public String createTrack(@Valid @NotBlank @RequestParam(name = "isrc") String value) throws ClientProtocolException, IOException {
		String accessToken = getAccessToken();
		//Spotify URL
		String url = trackUrl + "" + value + "&type=track";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		request.addHeader("Authorization", "Bearer " + accessToken);
		request.addHeader("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpResponse response = client.execute(request);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() == 401) {
			String error = "The server did not respond properly";
			throw new RuntimeException(error);
		} else {
			if (response.getStatusLine().getStatusCode() != 200)
				throw new RuntimeException("Something went wrong.");
		}
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		Root root = new ObjectMapper().readValue(result.toString(), Root.class);
		metaService.storeMetadata(root);
		return result.toString();
	}

	@GetMapping("/getTrack")
	public String getTrack(@Valid @NotBlank @RequestParam(name = "isrc") String value) throws JsonProcessingException {
		Metadata meta = metaService.getMetaData(value);
		ObjectMapper objectMapper = new ObjectMapper();
		if (null != meta) {
			String json = objectMapper.writeValueAsString(meta);
			return json;
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "No data found");
			jsonObject.put("isrc", value);
			String jsonString = jsonObject.toString();
			return jsonString;
		}
	}

	private String getAccessToken() throws ClientProtocolException, IOException {
		RestTemplate restTemplate = new RestTemplate();

		//Decoding client secret
		byte[] valueDecoded = Base64.decodeBase64(clientSecret);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials");
		map.add("client_id", clientID);
		map.add("client_secret", new String(valueDecoded));
		map.add("scope", "user-read-private");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<AccessToken> response = restTemplate.postForEntity(accessTokenUrl, request, AccessToken.class);
		return response.getBody().getAccess_token();
	}

}
