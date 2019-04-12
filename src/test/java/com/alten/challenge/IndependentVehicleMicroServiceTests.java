package com.alten.challenge;

import com.alten.challenge.db.entity.Vehicle;
import com.alten.challenge.db.repo.VehicleRepo;
import com.alten.challenge.service.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndependentVehicleMicroServiceTests {

	private final static String BASE_URI = "http://localhost:9090";

	@Autowired
	private VehicleRepo vehicleRepo;


	@Test
	public void contextLoads() {

	}


	/***
	 * Simulate one connected vehicle
	 * Choose the vehicle randomly
	 */
	@Test
	public void vehiclePingTest() throws JsonProcessingException {

		Iterable<Vehicle> vehicleIterable = vehicleRepo.findAll();
		List<Vehicle> vehicleList = Lists.newArrayList(vehicleIterable);
		int size = vehicleList.size();
		int connectedVehicleIndex = (new Random()).nextInt(size);
		Vehicle vehicle = vehicleList.get(connectedVehicleIndex);

		System.out.println("Ping from vehicle: " + vehicle.getVin() + ", index:" + vehicle.getId());

		final String uri = BASE_URI + "/vehicle/ping";
		RestTemplate restTemplate = new RestTemplate();

		VehiclePingRequest vehiclePingRequest = new VehiclePingRequest();
		vehiclePingRequest.setVin(vehicle.getVin());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<VehiclePingRequest> httpEntity = new HttpEntity<>(vehiclePingRequest, httpHeaders);

		ResponseEntity<VehiclePingResponse> response =
				restTemplate.exchange(uri, HttpMethod.POST, httpEntity, VehiclePingResponse.class);

		Assert.notNull(response, "response must not be null");
		Assert.state(response.getStatusCodeValue() == HttpStatus.OK.value(),
				"HTTP status code: " + String.valueOf(response.getStatusCodeValue()));
		Assert.notNull(response.getBody(), "HTTP body must not be null");

		VehiclePingResponse vehiclePingResponse = response.getBody();

		Assert.state(vehiclePingResponse.isSuccess() == true, vehiclePingResponse.getResponseMessage());
		Assert.state(vehiclePingResponse.getResponseCode() == 0, vehiclePingResponse.getResponseMessage());

		ObjectMapper objectMapper = new ObjectMapper();
		String vehiclePingResponseJson = objectMapper.writeValueAsString(vehiclePingResponse);
		System.out.println("vehiclePingResponseJson: " + vehiclePingResponseJson);
	}


	/***
	 * Get the display result as JSON
	 * If connected = true, returns connected vehicles
	 * If connected = false, returns disconnected vehicles
	 * If connected = null, returns all
	 */
	public void getVehiclesStatus(Boolean connected) {

		final String uri = BASE_URI + "/display/vehicles/status";
		RestTemplate restTemplate = new RestTemplate();

		VehiclesStatusDisplayRequest vehiclesStatusDisplayRequest = new VehiclesStatusDisplayRequest();
		vehiclesStatusDisplayRequest.setConnected(connected);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<VehiclesStatusDisplayRequest> httpEntity = new HttpEntity<>(vehiclesStatusDisplayRequest, httpHeaders);

		ResponseEntity<VehiclesStatusDisplayResponse> response =
				restTemplate.exchange(uri, HttpMethod.POST, httpEntity, VehiclesStatusDisplayResponse.class);

		Assert.notNull(response, "response must not be null");
		Assert.state(response.getStatusCodeValue() == HttpStatus.OK.value(),
				"HTTP status code: " + String.valueOf(response.getStatusCodeValue()));
		Assert.notNull(response.getBody(), "HTTP body must not be null");

		VehiclesStatusDisplayResponse vehiclesStatusDisplayResponse = response.getBody();

		Assert.state(vehiclesStatusDisplayResponse.isSuccess() == true, vehiclesStatusDisplayResponse.getResponseMessage());
		Assert.state(vehiclesStatusDisplayResponse.getResponseCode() == 0, vehiclesStatusDisplayResponse.getResponseMessage());

		List<VehiclesStatusList> vehiclesStatusList = vehiclesStatusDisplayResponse.getVehiclesStatusList();

		Assert.notEmpty(vehiclesStatusList, "vehiclesStatusList is empty");

		System.out.println("------------------------------\r\nShowing Vehicles Status:\r\n------------------------------");
		for (VehiclesStatusList v: vehiclesStatusList) {
			System.out.println(v.getVehicleVin() + ": " + v.getVehicleIsConnected() + ", " + v.getVehicleLastPing() +
					", " + v.getCustomerName() + ", " + v.getCustomerAddress());
		}
	}


	@Test
	public void pingOneVehicleAndGetConnectedVehicles() throws JsonProcessingException {
		vehiclePingTest();
		getVehiclesStatus(true);
	}


	@Test
	public void pingOneVehicleAndGetDisconnectedVehicles() throws JsonProcessingException {
		vehiclePingTest();
		getVehiclesStatus(false);
	}


	@Test
	public void pingOneVehicleAndGetAllVehicles() throws JsonProcessingException {
		vehiclePingTest();
		getVehiclesStatus(null);
	}
}
