package com.myfood.dishes.dto.mappers;

import com.myfood.dishes.dto.DishDTO;
import com.myfood.dishes.model.dish.Dish;
import com.myfood.dishes.service.crud.DishModelFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DishMapper.class)
public class DishMapperTest {
    @MockBean
    private CategoryMapper categoryMapper;
    @MockBean
    private DishModelFactory dishModelFactory;

    @Autowired
    private DishMapper dishMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testMapDTONameAndImageOnly_shouldMapOnlyThem_descriptionShouldBeNull() {
        UUID id = getId();
        String name = "omelet";
        String description = "Very good dish";
        String imageId = "/pics/omlet.jpg";

        Dish testDish = new Dish(id);

        testDish.setName(name);
        testDish.setDescription(description);
        testDish.setImageId(imageId);

        when(dishModelFactory.createEmptyDish()).thenReturn(testDish);

        DishDTO flatDTO = dishMapper.mapIdNameImageOnly(testDish);

        assertEquals(id, flatDTO.getId());
        assertEquals(name, flatDTO.getName());
        assertEquals(imageId, flatDTO.getImageId());
        assertTrue(StringUtils.isEmpty(flatDTO.getDescription()));

    }

    @Test
    public void testMapDTOAllData_shouldMapAllFields_fromDOtoDTO(){

    }

    private UUID getId() {
        return UUID.randomUUID();
    }
}