package com.project.AIPoweredBRD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class EmbeddingService {

//	@Autowired
//	private WebClient HFWebclient;
	
	@Autowired
	private WebClient OlamaWebclient;
	
	public String GenerateEmbedding(String text) {
		
		try {
		
		JSONObject requestBody= new JSONObject();
	//	JSONObject inputArray= new JSONObject();
		
		
		
		requestBody.put("model", "nomic-embed-text");
		requestBody.put("prompt", text);
		//requestBody.put("task", "feature-extraction"); 
		
		
		String responseBody=OlamaWebclient.post()
				.uri("/api/embeddings") 
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(requestBody.toString())
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		
	  //  System.out.println("response is : "+ responseBody);
		
		//JSONArray jsonArray= new JSONArray(responseBody);
		
		
		
		JSONObject firstObj = new JSONObject(responseBody);
		JSONArray jsonArray=firstObj.getJSONArray("embedding");
	//	System.out.println(jsonArray);
		
		
		String embedding =jsonArray.toString();

//		float[] embedding = new float[jsonArray.length()];
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            embedding[i] = (float) jsonArray.getDouble(i);
//        }
//        System.out.println(embedding.toString());
		return embedding;
		
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return null;
		
				
	}

	public Object cosineSimilarity(float[] a, float[] b) {
		// TODO Auto-generated method stub
		
		System.out.println("entered cosine check");
		
		double dot = 0.0, normA = 0.0, normB = 0.0;
	    for (int i = 0; i < a.length; i++) {
	        dot += a[i] * b[i];
	        normA += a[i] * a[i];
	        normB += b[i] * b[i];
	    }
	    return dot / (Math.sqrt(normA) * Math.sqrt(normB));
	   
	    
	}

	public float[] parseEmbedding(String embedding) {
		// TODO Auto-generated method stub
		
		System.out.println("entered parse check");
		
		if(embedding==null || embedding.isEmpty())
				return new float[0];
		
		JSONArray arr= new JSONArray(embedding);
		float[] emb= new float[arr.length()];
		
		for(int i=0;i<arr.length();i++) {
			
			emb[i]= (float)arr.getDouble(i);
		}
		
		return emb;
	}
	
	
	
	
	
	
	
	
}
