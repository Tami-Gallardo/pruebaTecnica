package com.pruebaTecnica.service;

import com.pruebaTecnica.domain.entities.PeopleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarWarsServiceTest {
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @InjectMocks
    private StarWarsService starWarsService;

    @BeforeEach
    void setUp() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
    }

    @Test
    void getAllPeople_ShouldReturnPeopleList() {
        String jsonResponse = "{ \"results\": [ { \"uid\": \"1\", \"name\": \"Luke Skywalker\" }, { \"uid\": \"2\", \"name\": \"Darth Vader\" } ], \"totalPages\": 1, \"totalRecords\": 2 }";

        // Mockeamos la respuesta de la API
        PeopleResponse mockResponse = new PeopleResponse();
        List<PeopleResponse.People> people = new ArrayList<>();
        people.add(new PeopleResponse.People("1", "Luke Skywalker"));
        people.add(new PeopleResponse.People("2", "Darth Vader"));
        mockResponse.setResults(people);
        mockResponse.setTotalPages(1);
        mockResponse.setTotalRecords(2);

        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(jsonResponse));

        System.out.println("JSON Response: " + jsonResponse);

        // Ejecutamos el método
        PeopleResponse result = starWarsService.getAllPeople(1, null, null, null);
        for (PeopleResponse.People p : result.getPeople()) {
            System.out.println("UID: " + p.getUid() + ", Name: " + p.getName());
        }

        List<PeopleResponse.People> peopleResponseList = new ArrayList<>(result.getPeople());

        // Verificamos los resultados
        assertNotNull(result);
        assertEquals(2, peopleResponseList.size());
        assertEquals("Luke Skywalker", peopleResponseList.get(0).getName());
        assertEquals("Darth Vader", peopleResponseList.get(1).getName());
    }
}
