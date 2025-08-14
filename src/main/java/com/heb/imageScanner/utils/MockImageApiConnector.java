package com.heb.imageScanner.utils;

import java.net.URL;

import com.heb.imageScanner.contracts.ImageApiConnectorInterface;

public class MockImageApiConnector implements ImageApiConnectorInterface {
    public String getImageDetails(URL urlObject) throws Exception {
        String test_json = """
        {
  \"result\": {
    \"tags\": [
      {
        \"confidence\": 61.4116096496582,
        \"tag\": {
          \"en\": \"confetti\"
        }
      },
      {
        \"confidence\": 54.3507270812988,
        \"tag\": {
          \"en\": \"holiday\"
        }
      },
      {
        \"confidence\": 50.969783782959,
        \"tag\": {
          \"en\": \"paper\"
        }
      },
      {
        \"confidence\": 46.1385192871094,
        \"tag\": {
          \"en\": \"design\"
        }
      }
    ]
  },
  \"status\": {
    \"text\": \"\",
    \"type\": \"success\"
  }
}""";

        return test_json;
        
    }
}
