package com.thegs.coffeeapp.client;

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


public class OrdersTester {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Create one order
		Order o = new Order("3", "Long Black", "3.20");
		ClientResponse response = service.path("rest").path("orders")
				.path(o.getId()).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, o);
		// Return code should be 201 == created resource
		System.out.println(response.getStatus());

		// Try various methods for testing the service using this client.
		
		//Get orders/1
		System.out.println(service.path("rest").path("orders/1").accept(
				MediaType.APPLICATION_XML).get(String.class));
		// Delete orders/3
		service.path("rest").path("orders/3").delete();
		
		// Get the all orders
		System.out.println(service.path("rest").path("orders").accept(
				MediaType.APPLICATION_XML).get(String.class));
		
		
		// Create a Order with FORM (POST - note the mapping between params and ...
		Form form = new Form();
		form.add("id", "4");
		form.add("coffeetype", "Espresso");
		form.add("cost", "2.90");
		response = service.path("rest").path("orders").type(MediaType.APPLICATION_FORM_URLENCODED)
								   .post(ClientResponse.class, form);
		System.out.println("Form response " + response.getEntity(String.class));

		
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/CoffeeService").build();
	}
}
