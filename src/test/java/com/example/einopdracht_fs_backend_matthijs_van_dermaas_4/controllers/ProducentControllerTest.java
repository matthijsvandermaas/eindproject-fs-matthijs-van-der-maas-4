package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.controllers;

import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Dtos.ProducentDto;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security.JwtService;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Producent;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProducentRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.ProductRepository;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ProducentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.services.ProducentService.producentRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducentController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class ProducentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProducentService producentService;

    @MockBean
    ProducentRepository producentRepository;

    @MockBean
    JwtService jwtService;

    @Test
    <List>
    void getAllProducten() throws Exception {

        ProducentDto pdto = new ProducentDto();
        pdto.setFirstName("Lusiana");
        pdto.setLastName("van der Maas");
        pdto. setOwner("Matthijs van der Maas");
        pdto. setNameBrewery("Brouwerij van der Maas");
        pdto. setSaleLocation("Lidl Amsterdam, Albert Heijn Amsterdam, Jumbo Amsterdam");
        pdto.setStreet("Kerkstraat 1");
        pdto.setHouseNumber("4a");
        pdto. setZipcode("1234AB");
        pdto. setCity("Amsterdam");
        pdto. setBrands("Bier");
        pdto.setEmail("Matthijsbier@matthijsbier.com");
        pdto. setUserName("Matthijs-bier");
        pdto. setPassword("Matthijs-bier");
        pdto. setRole(Collections.singletonList("ROLE_BREWER"));



        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/producenten"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())


        ProducentDto pdto2 = new ProducentDto();
             pdto2.setFirstName("Lusiana");
                pdto2.setLastName("van der Maas");
                pdto2. setOwner("Matthijs van der Maas");
                pdto2. setNameBrewery("Brouwerij van der Maas");
                pdto2. setSaleLocation("Lidl Amsterdam, Albert Heijn Amsterdam, Jumbo Amsterdam");
                 pdto2.setStreet("Kerkstraat ");
                 pdto2.setHouseNumber("4a");
                pdto2. setZipcode("1234AB");
                pdto2. setCity("Amsterdam");
                pdto2. setBrands("Bier");
                pdto2.setEmail("Matthijsbier@matthijsbier.com");
                pdto2. setUserName("Matthijs-bier");
                pdto2. setPassword("Matthijs-bier");
                pdto2. setRole(Collections.singletonList("ROLE_BREWER"));






        Mockito.when(producentService.getAllProducenten()).thenReturn(producentDtoList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/producten"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andReturn(MockMvcResultMatchers.firstName().value("Lusiana"))
                .andReturn(MockMvcResultMatchers.lastName().value("van der Maas"))
                .andReturn(MockMvcResultMatchers.owner().value("Matthijs van der Maas"))
                .andReturn(MockMvcResultMatchers.nameBrewery().value("Brouwerij van der Maas"))
                .andReturn(MockMvcResultMatchers.saleLocation().value("Lidl Amsterdam, Albert Heijn Amsterdam, Jumbo Amsterdam"))
                .andReturn(MockMvcResultMatchers.street().value("Kerkstraat "))
                .andReturn(MockMvcResultMatchers.houseNumber().value("4a"))
                .andReturn(MockMvcResultMatchers.zipcode().value("1234AB"))
                .andReturn(MockMvcResultMatchers.city().value("Amsterdam"))
                .andReturn(MockMvcResultMatchers.brands().value("Bier"))
                .andReturn(MockMvcResultMatchers.email().value("Matthijsbier@matthijsbier.com")
                .andReturn(MockMvcResultMatchers.userName().value("Matthijs-bier"))
                .andReturn(MockMvcResultMatchers.password().value("Matthijs-bier"))
                .andReturn(MockMvcResultMatchers.role().value(Collections.singletonList("ROLE_BREWER"))));



//

    }

    @Test
    void createProducent() throws Exception {

        Producent producent = new Producent();
        producent.setFirstName("Lusiana");
        producent.setLastName("van der Maas");
        producent. setOwner("Matthijs van der Maas");
        producent. setNameBrewery("Brouwerij van der Maas");
        producent. setSaleLocation("Lidl Amsterdam, Albert Heijn Amsterdam, Jumbo Amsterdam");
        producent.setStreet("Kerkstraat ");
        producent.setHouseNumber("4a");
        producent. setZipcode("1234AB");
        producent. setCity("Amsterdam");
        producent. setBrands("Bier");
        producent.setEmail("Matthijsbier@matthijsbier.com");
        producent. setUserName("Matthijs-bier");
        producent. setPassword("Matthijs-bier");
        producent. setRole(Collections.singletonList("ROLE_BREWER"));

        producentRepository.save(producent);

        ProducentDto pdto2 = new ProducentDto();
        pdto2.setFirstName("Lusiana");
        pdto2.setLastName("van der Maas");
        pdto2. setOwner("Matthijs van der Maas");
        pdto2. setNameBrewery("Brouwerij van der Maas");
        pdto2. setSaleLocation("Lidl Amsterdam, Albert Heijn Amsterdam, Jumbo Amsterdam");
        pdto2.setStreet("Kerkstraat ");
        pdto2.setHouseNumber("4a");
        pdto2. setZipcode("1234AB");
        pdto2. setCity("Amsterdam");
        pdto2. setBrands("Bier");
        pdto2.setEmail("Matthijsbier@matthijsbier.com");
        pdto2. setUserName("Matthijs-bier");
        pdto2. setPassword("Matthijs-bier");
        pdto2. setRole(Collections.singletonList("ROLE_BREWER"));



        Mockito.when(producentService.createProducent(Mockito.any(ProducentDto.class))).thenReturn(pdto2);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/producenten")
                        .content(new ObjectMapper().writeValueAsString(pdto2))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Lusiana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Lastname").value("van der Maas"));
    }//TODO: fix this test
}}