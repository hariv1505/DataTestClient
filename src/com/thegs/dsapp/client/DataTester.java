package com.thegs.dsapp.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.thegs.coffeeapp.model.Order;


public class DataTester {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		ClientResponse response1 = service.path("events").path("tsb-7eb31abc")
				.header("Auth", "abc123").put(ClientResponse.class);
		System.out.println(response1.getStatus());
		
		String response2 = service.path("events")
				.path("tsb-7eb31abc").path("trade").path("totalprice")
				.header("Auth", "abc123").get(String.class);
		System.out.println(response2);
		
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/DataService").build();
	}
}
