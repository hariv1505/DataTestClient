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
/*		// Create one order
		Order o = new Order("4", "Long Black", "3.20");
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
		
		*/
//		Order o = new Order("10", "Hello", "1.00");
//		ClientResponse response = service.path("rest").path("orders")
//				.path(o.getId()).accept(MediaType.APPLICATION_XML).header("Auth","abc123").put(ClientResponse.class, o);
//		System.out.println(response.getStatus());
		
		ClientResponse response1 = service.path("events").path("tsb-7eb31abc")
				.header("Auth", "abc123").put(ClientResponse.class);
		System.out.println(response1.getStatus());
		
		String response2 = service.path("events")
				.path("tsb-7eb31abc").path("trade").path("xml")
				.header("Auth", "abc123").get(String.class);
		System.out.println(response2);
		//System.out.println("Form response " + response1.getEntity(String.class));
		
	//	service.path("rest").path("orders/4").accept(
	//			MediaType.APPLICATION_XML).put(jaxbOrder);
				
		// Create a Order with FORM (POST - note the mapping between params and ...
/*		Form form = new Form();
		form.add("id", "6");
		form.add("coffeetype", "Latte");
		form.add("cost", "3.00");
		form.add("additions", "Hazelnut");
		ClientResponse response = service.path("rest").path("orders").type(MediaType.APPLICATION_FORM_URLENCODED)
								   .post(ClientResponse.class, form);
		System.out.println(response.getHeaders());
		System.out.println("Form response " + response.getEntity(String.class));
*/
		
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/DataService").build();
	}
}
