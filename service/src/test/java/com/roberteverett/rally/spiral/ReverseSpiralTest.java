package com.roberteverett.rally.spiral;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ReverseSpiralTest extends AbstractSpiralTest {

    private WebResource resource;

    @Before
    public void setUp() {
        this.resource = resource().path("spiral").path("reverse");
    }

    @Override
    protected WebResource tester() {
        return resource;
    }

    @Test
    public void getShouldReturnSpiralWithReverseOrderAndRightwardClockwiseSpinUpToEndGivenEndParameter() throws Exception {
        ClientResponse response = givenPartialParameters("8").get(ClientResponse.class);

        String[][] expected = { { "2", "1", "0" },
                                { "3", "8", "7" },
                                { "4", "5", "6" } };

        jsonResponseEquals(response, "8", expected, "RIGHT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithReverseOrderAndRightwardCounterClockwiseSpinGivenCounterClockwiseRotationParameter()
            throws Exception {
        ClientResponse response = givenFullParameters("8", "RIGHT", "COUNTER_CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "4", "5", "6" },
                                { "3", "8", "7" },
                                { "2", "1", "0" } };

        jsonResponseEquals(response, "8", expected, "RIGHT", "COUNTER_CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithReverseOrderAndLeftwardSpinGivenLeftDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters("8", "LEFT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "6", "5", "4" },
                                { "7", "8", "3" },
                                { "0", "1", "2" } };

        jsonResponseEquals(response, "8", expected, "LEFT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithReverseOrderAndUpwardSpinGivenUpDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters("8", "UP", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "0", "7", "6" },
                                { "1", "8", "5" },
                                { "2", "3", "4" } };

        jsonResponseEquals(response, "8", expected, "UP", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithReverseOrderAndDownwardSpinGivenDownDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters("8", "DOWN", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "4", "3", "2" },
                                { "5", "8", "1" },
                                { "6", "7", "0" } };

        jsonResponseEquals(response, "8", expected, "DOWN", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnPlainTextReverseSpiralGivenPlainTextHeader() throws Exception {
        ClientResponse response = givenPartialParameters("24").accept(MediaType.TEXT_PLAIN_TYPE).get(ClientResponse.class);

        final StringBuilder expected = new StringBuilder();
        expected.append(" 4  3  2  1  0").append("%n");
        expected.append(" 5 18 17 16 15").append("%n");
        expected.append(" 6 19 24 23 14").append("%n");
        expected.append(" 7 20 21 22 13").append("%n");
        expected.append(" 8  9 10 11 12");

        Assert.assertEquals(String.format(expected.toString()), response.getEntity(String.class));
    }

    @Test
    public void getShouldReturnSpiralWithCorrectTopLeftMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "RIGHT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "3", "2" },
                                { "0", "1" } };

        jsonResponseEquals(response, "3", expected, "RIGHT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithCorrectTopRightMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "DOWN", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "0", "3" },
                                { "1", "2" } };

        jsonResponseEquals(response, "3", expected, "DOWN", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithCorrectBottomLeftMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "UP", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "2", "1" },
                                { "3", "0" } };

        jsonResponseEquals(response, "3", expected, "UP", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithCorrectBottomRightMiddleWhenDimensionsAreEven() throws Exception {
        ClientResponse response = givenFullParameters("3", "LEFT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "1", "0" },
                                { "2", "3" } };

        jsonResponseEquals(response, "3", expected, "LEFT", "CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithoutEmptyRowsWhenDimensionsAreUneven() throws Exception {
        ClientResponse response = givenFullParameters("4", "RIGHT", "CLOCKWISE").get(ClientResponse.class);

        String[][] expected = { { "",  "4", "3" },
                                { "0", "1", "2" } };

        jsonResponseEquals(response, "4", expected, "RIGHT", "CLOCKWISE");
    }

}
