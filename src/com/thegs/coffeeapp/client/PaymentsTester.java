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
import com.thegs.coffeeapp.model.Payment;


public class PaymentsTester {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Create one payment
		Payment p = new Payment("2", "Card", "4.50");
        p.setCardDetails("123456789");
		ClientResponse response = service.path("rest").path("payments")
				.path(p.getId()).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, p);
		// Return code should be 201 == created resource
		System.out.println(response.getStatus());

		// Try various methods for testing the service using this client.
		
		//Get payments/1
		System.out.println(service.path("rest").path("payments/1").accept(
				MediaType.APPLICATION_XML).get(String.class));
		// Delete payments/3
		service.path("rest").path("payments/3").delete();
		
		// Get the all payments
		System.out.println(service.path("rest").path("payments").accept(
				MediaType.APPLICATION_XML).get(String.class));
		
		
		// Create a Payment with FORM (POST - note the mapping between params and ...
		Form form = new Form();
		form.add("id", "4");
		form.add("paytype", "cash");
		form.add("amount", "2.90");
		response = service.path("rest").path("payments").type(MediaType.APPLICATION_FORM_URLENCODED)
								   .post(ClientResponse.class, form);
		System.out.println("Form response " + response.getEntity(String.class));

		form.add("id", "1");
		form.add("paytype", "card");
		form.add("amount", "3.70");
		form.add("carddetails", "150589");
		response = service.path("rest").path("payments").type(MediaType.APPLICATION_FORM_URLENCODED)
								   .post(ClientResponse.class, form);
		System.out.println("Form response " + response.getEntity(String.class));
		
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/CoffeeService").build();
	}
}
