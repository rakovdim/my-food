package com.myfood.reconciliation.utils;

import com.myfood.reconciliation.model.dto.IngredientQuantityDTO;
import com.myfood.reconciliation.model.dto.TagDTO;
import java.util.List;
import java.util.function.Function;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by rakov on 14.08.2019.
 */
@RunWith(SpringRunner.class)
public class StringDataParserTest {

    private StringDataParser stringDataParser;


    @Before
    public void setUp() throws Exception {
        this.stringDataParser = new StringDataParser();
    }


    @Test
    public void parseTags_shouldReturn3TagDTO_whenTagsStringContains3Tags() throws Exception {
        List<TagDTO> tags = stringDataParser.parseTags("spicy;fish;meat");

        assertNotNull(tags);
        assertEquals(3, tags.size());
        assertEquals("spicy", tags.get(0).getName());
        assertEquals("fish", tags.get(1).getName());
        assertEquals("meat", tags.get(2).getName());
    }


    @Test
    public void parseTags_shouldReturnOneTagDTO_whenTagsStringContains1Tag() {
        List<TagDTO> tags = stringDataParser.parseTags("spicy");

        assertNotNull(tags);
        assertEquals(1, tags.size());
        assertEquals("spicy", tags.get(0).getName());
    }


    @Test
    public void parseTags_shouldReturnEmptyList_whenNoTagsInString() {
        List<TagDTO> tags = stringDataParser.parseTags("");

        assertNotNull(tags);
        assertTrue(tags.isEmpty());
    }


    @Test
    public void parseTags_shouldReturnEmptyList_whenTagsStringIsNull() {
        List<TagDTO> tags = stringDataParser.parseTags(null);

        assertNotNull(tags);
        assertTrue(tags.isEmpty());
    }


    @Test
    public void parseBulletsString_shouldReturnTwoStrings_whenTwoBulletsInOneLineExists() throws Exception {
        String textWithBullets = "11. Wash the products   \n22)Then cut it";

        List<String> result = stringDataParser.parseBulletsString(textWithBullets);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Wash the products", result.get(0));
        assertEquals("Then cut it", result.get(1));
    }


    @Test
    public void parseBulletsString_shouldReturnIngredientQuantity_whenStringIsCorrect() throws Exception {
        String textWithBullets = "Молоко - 3кг";

        IngredientQuantityDTO result = stringDataParser.parseIngredientQuantity(textWithBullets);

        assertNotNull(result);
    }

}