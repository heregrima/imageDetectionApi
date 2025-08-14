package com.heb.imageScanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.heb.imageScanner.utils.JsonValueExtractor;

@SpringBootTest
public class JsonValueExtractorTests {
    String test_json;
    
    public JsonValueExtractorTests() {
        test_json = """
        {
  \"result\": {
    \"tags\": [
      {
        \"confidence\": 61.4116096496582,
        \"tag\": {
          \"en\": \"mountain\"
        }
      },
      {
        \"confidence\": 54.3507270812988,
        \"tag\": {
          \"en\": \"landscape\"
        }
      },
      {
        \"confidence\": 50.969783782959,
        \"tag\": {
          \"en\": \"mountains\"
        }
      },
      {
        \"confidence\": 46.1385192871094,
        \"tag\": {
          \"en\": \"wall\"
        }
      }
    ]
  },
  \"status\": {
    \"text\": \"\",
    \"type\": \"success\"
  }
}""";

    }

    @Test
    public void testGetAllValuesForKey() {
      JSONObject jsonImageResponse = new JSONObject(test_json);
      List<Object> objectsDetected = JsonValueExtractor.getAllValuesForKey(jsonImageResponse, "en");
      List<String> expectedList = Arrays.asList("mountain", "landscape", "mountains", "wall");
      assertTrue(objectsDetected.containsAll(expectedList));
    }

}