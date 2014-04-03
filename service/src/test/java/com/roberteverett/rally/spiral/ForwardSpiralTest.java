package com.roberteverett.rally.spiral;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ForwardSpiralTest extends AbstractSpiralTest {

    private WebResource resource;

    @Before
    public void setUp() {
        this.resource = resource().path("spiral").path("forward");
    }

    @Override
    protected WebResource tester() {
        return resource;
    }

    @Test
    public void getShouldReturnSpiralWithForwardOrderAndRightwardClockwiseSpinUpToEndGivenEndParameter() throws Exception {
        ClientResponse response = givenPartialParameters("24").get(ClientResponse.class);

        String[][] expected = { { "20", "21", "22", "23", "24" },
                                { "19",  "6",  "7",  "8",  "9" },
                                { "18",  "5",  "0",  "1", "10" },
                                { "17",  "4",  "3",  "2", "11" },
                                { "16", "15", "14", "13", "12" } };

        jsonResponseEquals(response, "24", expected, "RIGHT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithForwardOrderAndRightwardCounterClockwiseSpinGivenCounterClockwiseRotationParameter() throws Exception {
        ClientResponse response = givenFullParameters("8", "RIGHT", "COUNTER_CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "4", "3", "2" },
                                { "5", "0", "1" },
                                { "6", "7", "8" } };

        jsonResponseEquals(response, "8", expected, "RIGHT", "COUNTER_CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithForwardOrderAndLeftwardSpinGivenLeftDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters("8", "LEFT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "2", "3", "4" },
                                { "1", "0", "5" },
                                { "8", "7", "6" } };

        jsonResponseEquals(response, "8", expected, "LEFT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithForwardOrderAndUpwardSpinGivenUpDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters("8", "UP", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "8", "1", "2" },
                                { "7", "0", "3" },
                                { "6", "5", "4" } };

        jsonResponseEquals(response, "8", expected, "UP", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithForwardOrderAndDownwardSpinGivenDownDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters("8", "DOWN", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "4", "5", "6" },
                                { "3", "0", "7" },
                                { "2", "1", "8" } };

        jsonResponseEquals(response, "8", expected, "DOWN", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnPlainTextSpiralGivenPlainTextHeader() throws Exception {
        ClientResponse response = givenPartialParameters("24").accept(MediaType.TEXT_PLAIN_TYPE).get(ClientResponse.class);

        final StringBuilder expected = new StringBuilder();
        expected.append("20 21 22 23 24").append("%n");
        expected.append("19  6  7  8  9").append("%n");
        expected.append("18  5  0  1 10").append("%n");
        expected.append("17  4  3  2 11").append("%n");
        expected.append("16 15 14 13 12");

        Assert.assertEquals(String.format(expected.toString()), response.getEntity(String.class));
    }

    @Test
    public void getShouldReturnSpiralWithCorrectTopLeftMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "RIGHT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "0", "1" },
                                { "3", "2" } };

        jsonResponseEquals(response, "3", expected, "RIGHT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithCorrectTopRightMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "DOWN", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "3", "0" },
                                { "2", "1" } };

        jsonResponseEquals(response, "3", expected, "DOWN", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithCorrectBottomLeftMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "UP", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "1", "2" },
                                { "0", "3" } };

        jsonResponseEquals(response, "3", expected, "UP", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithCorrectBottomRightMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "LEFT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "2", "3" },
                                { "1", "0" } };

        jsonResponseEquals(response, "3", expected, "LEFT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithoutEmptyRowsWhenDimensionsAreUneven() throws Exception {
        ClientResponse response = givenFullParameters("4", "RIGHT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "",  "0", "1" },
                                { "4", "3", "2" } };

        jsonResponseEquals(response, "4", expected, "RIGHT", "CLOCKWISE");
    }

}
