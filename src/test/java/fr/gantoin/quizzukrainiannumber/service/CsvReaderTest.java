package fr.gantoin.quizzukrainiannumber.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;


public class CsvReaderTest {

    @Test
    public void testGetResource() throws IOException, URISyntaxException {
        CsvReader csvReader = new CsvReader();
        assertThat(csvReader.getNumbers()).isNotEmpty();
    }

}
