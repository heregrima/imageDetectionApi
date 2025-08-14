package com.heb.imageScanner.contracts;

import java.net.URL;

public interface ImageApiConnectorInterface {
    public String getImageDetails(URL urlObject) throws Exception;
}
