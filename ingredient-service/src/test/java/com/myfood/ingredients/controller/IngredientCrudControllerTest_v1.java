package com.myfood.ingredients.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myfood.ingredients.dto.IngredientDTO;
import com.myfood.ingredients.dto.mapping.IngredientMapper;
import com.myfood.ingredients.service.IngredientService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(IngredientCrudController.class)
@RunWith(SpringRunner.class)
public class IngredientCrudControllerTest_v1 {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IngredientService ingredientService;
    @MockBean
    private IngredientMapper ingredientMapper;

    private JacksonTester<IngredientDTO> json;


    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    public void testCreateIngredient_shouldReturnIngredient_whenAllParamsValid() {
    }

}
