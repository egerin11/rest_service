package com.example.laba.test_controler;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.laba.controller.OwnerAndCatController;
import com.example.laba.model.Owner;
import com.example.laba.model.dto.OwnerDto;
import com.example.laba.service.OwnerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class OwnerAndCatControllerTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerAndCatController ownerAndCatController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerAndCatController).build();
    }

    @Test
    void testGetOwner() throws Exception {
        Long ownerId = 1L;
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setName("John");

        when(ownerService.getOwner(ownerId)).thenReturn(owner);

        mockMvc.perform(get("/owner/owner/{id}", ownerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ownerId))
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testGetOwners() throws Exception {
        List<OwnerDto> ownerDtos = new ArrayList<>();
        ownerDtos.add(new OwnerDto(1L, "John"));
        ownerDtos.add(new OwnerDto(2L, "Jane"));

        when(ownerService.getAllOwners()).thenReturn(ownerDtos);

        mockMvc.perform(get("/owner/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"));
    }

    @Test
    void testAddOwner() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("John");

        when(ownerService.addOwner(any(Owner.class))).thenReturn(owner);

        mockMvc.perform(post("/owner/add-owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"John\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        verify(ownerService).addOwner(any(Owner.class));
    }

    @Test
    void testUpdateOwner() throws Exception {
        Long ownerId = 1L;
        Owner updatedOwner = new Owner();
        updatedOwner.setId(ownerId);
        updatedOwner.setName("John Doe");

        when(ownerService.updateOwner(anyLong(), any(Owner.class))).thenReturn(updatedOwner);

        mockMvc.perform(put("/owner/update-owner/{id}", ownerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ownerId))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(ownerService).updateOwner(ownerId, updatedOwner);
    }
//
//    @Test
//    void testDeleteCatToOwner() throws Exception {
//        Long catId = 1L;
//        Long ownerId = 1L;
//        Owner owner = new Owner();
//        owner.setId(ownerId);
//        owner.setName("John");
////        when(ownerService.deleteCatToOwner(catId, ownerId)).thenReturn(owner);
//
//        mockMvc.perform(delete("/owner/cat/{catId}/owner/{ownerId}", catId, ownerId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(ownerId))
//                .andExpect(jsonPath("$.name").value("John"));
//
//        verify(ownerService).deleteCatToOwner(catId, ownerId);
//    }

    @Test
    void testDeleteOwner() throws Exception {
        Long ownerId = 1L;

        mockMvc.perform(delete("/owner/delete-owner/{id}", ownerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("delete"));

        verify(ownerService).removeOwner(ownerId);
    }
}