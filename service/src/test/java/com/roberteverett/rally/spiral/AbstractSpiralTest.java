package com.roberteverett.rally.spiral;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public abstract class AbstractSpiralTest extends GuiceTest {

    protected static final String END = "end";
    protected static final String DIRECTION = "direction";
    protected static final String ROTATION = "rotation";
    protected static final String TABLE = "table";
    protected static final String ROWS = "rows";
    protected static final String COLUMNS = "columns";
    protected static final String VALUE = "value";

    @Test
    public void getShouldReturnOkGivenPartialParameters() throws Exception {
        ClientResponse response = givenPartialParameters(defaultEndValue()).get(ClientResponse.class);

        shouldReturnOk(response);
    }

    @Test
    public void getShouldReturnOkGivenFullParameters() throws Exception {
        ClientResponse response = givenFullParameters(defaultEndValue(), "LEFT", "COUNTER_CLOCKWISE").get(
                ClientResponse.class);

        shouldReturnOk(response);
    }

    @Test
    public void getShouldReturnOkGivenNoParameters() throws Exception {
        ClientResponse response = givenNoParameters().get(ClientResponse.class);

        shouldReturnOk(response);
    }

    @Test
    public void getShouldReturnOkGivenInvalidParameters() throws Exception {
        ClientResponse response = givenInvalidParameters().get(ClientResponse.class);

        shouldReturnOk(response);
    }

    @Test
    public void getShouldReturnPlainTextGivenPlainTextAcceptHeader() {
        ClientResponse response = givenDefaultParameters().accept(MediaType.TEXT_PLAIN_TYPE).get(ClientResponse.class);

        shouldReturnPlainText(response);
    }

    @Test
    public void getShouldReturnJsonGivenJsonAcceptHeader() {
        ClientResponse response = givenDefaultParameters().accept(MediaType.APPLICATION_JSON_TYPE).get(
                ClientResponse.class);

        shouldReturnJson(response);
    }

    @Test
    public void getShouldReturnUnacceptableGivenInvalidAcceptHeader() {
        ClientResponse response = givenDefaultParameters().accept(MediaType.TEXT_HTML_TYPE).get(ClientResponse.class);

        shouldReturnUnacceptable(response);
    }

    @Test
    public void getShouldReturnRightGivenNoDirectionParameter() throws Exception {
        ClientResponse response = givenDefaultParameters().get(ClientResponse.class);

        shouldReturnDirection(response, "RIGHT");
    }

    @Test
    public void getShouldReturnRightGivenInvalidDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters(defaultEndValue(), "invalid", "CLOCKWISE").get(
                ClientResponse.class);

        shouldReturnDirection(response, "RIGHT");
    }

    @Test
    public void getShouldReturnDirectionGivenLowerCaseDirectionParameter() throws Exception {
        ClientResponse response = givenFullParameters(defaultEndValue(), "left", "CLOCKWISE").get(ClientResponse.class);

        shouldReturnDirection(response, "LEFT");
    }

    @Test
    public void getShouldReturnClockwiseGivenNoRotationParameter() throws Exception {
        ClientResponse response = givenDefaultParameters().get(ClientResponse.class);

        shouldReturnRotation(response, "CLOCKWISE");
    }

    @Test
    public void getShouldReturnClockwiseGivenInvalidRotationParameter() throws Exception {
        ClientResponse response = givenFullParameters(defaultEndValue(), "RIGHT", "invalid").get(ClientResponse.class);

        shouldReturnRotation(response, "CLOCKWISE");
    }

    @Test
    public void getShouldReturnRotationGivenLowerCaseRotationParameter() throws Exception {
        ClientResponse response = givenFullParameters(defaultEndValue(), "RIGHT", "counter_clockwise").get(
                ClientResponse.class);

        shouldReturnRotation(response, "COUNTER_CLOCKWISE");
    }

    @Test
    public void getShouldReturnSpiralWithOneNumberGivenNoEndParameter() throws Exception {
        ClientResponse response = givenNoParameters().get(ClientResponse.class);

        Assert.assertEquals("0", json(response).getString(END));
    }

    @Test
    public void getShouldReturnSpiralWithOneNumberGivenNonNumbericEndParameter() throws Exception {
        ClientResponse response = givenPartialParameters("a").get(ClientResponse.class);

        Assert.assertEquals("0", json(response).getString(END));
    }

    @Test
    public void getShouldReturnSpiralWithOneNumberGivenNegativeEndParameter() throws Exception {
        ClientResponse response = givenPartialParameters("-1").get(ClientResponse.class);

        Assert.assertEquals("0", json(response).getString(END));
    }

    @Test
    public void postShouldReturnNotAllowedWithAllowHeader() {
        ClientResponse response = givenDefaultParameters().post(ClientResponse.class);

        shouldReturnNotAllowedWithGetAllowHeader(response, HttpMethod.POST);
    }

    @Test
    public void putShouldReturnNotAllowedWithAllowHeader() {
        ClientResponse response = givenDefaultParameters().put(ClientResponse.class);

        shouldReturnNotAllowedWithGetAllowHeader(response, HttpMethod.PUT);
    }

    @Test
    public void deleteShouldReturnNotAllowedWithAllowHeader() {
        ClientResponse response = givenDefaultParameters().delete(ClientResponse.class);

        shouldReturnNotAllowedWithGetAllowHeader(response, HttpMethod.DELETE);
    }

    @Test
    public void getShouldAllowCrossDomainRequests() {
        ClientResponse response = givenDefaultParameters().get(ClientResponse.class);

        shouldReturnHeaderWith(response, "Access-Control-Allow-Origin", "*");
        shouldReturnHeaderWith(response, "Access-Control-Allow-Methods", "GET,OPTIONS,HEAD");
    }

    protected WebResource givenFullParameters(String end, String direction, String rotation) {
        return tester().queryParam(END, end).queryParam(DIRECTION, direction).queryParam(ROTATION, rotation);
    }

    protected WebResource givenPartialParameters(String end) {
        return tester().queryParam(END, end);
    }

    private WebResource givenInvalidParameters() {
        return tester().queryParam(END, "invalid").queryParam(DIRECTION, "invalid").queryParam(ROTATION, "invalid");
    }

    private WebResource givenDefaultParameters() {
        return tester().queryParam(END, defaultEndValue());
    }

    private WebResource givenNoParameters() {
        return tester();
    }

    private String defaultEndValue() {
        return "24";
    }

    private JSONObject json(ClientResponse response) {
        return response.getEntity(JSONObject.class);
    }

    private String jsonFor(ClientResponse response, String key) throws Exception {
        return json(response).getString(key);
    }

    private void shouldReturnDirection(ClientResponse response, String direction) throws Exception {
        Assert.assertEquals(direction, jsonFor(response, DIRECTION));
    }

    private void shouldReturnRotation(ClientResponse response, String rotation) throws Exception {
        Assert.assertEquals(rotation, jsonFor(response, ROTATION));
    }

    private void shouldReturnJson(ClientResponse response) {
        Assert.assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getType());
    }

    private void shouldReturnPlainText(ClientResponse response) {
        Assert.assertEquals(MediaType.TEXT_PLAIN_TYPE, response.getType());
    }

    private void shouldReturnOk(ClientResponse response) {
        Assert.assertEquals(200, response.getStatus());
    }

    private void shouldReturnUnacceptable(ClientResponse response) {
        Assert.assertEquals(406, response.getStatus());
    }

    private void shouldReturnHeaderWith(ClientResponse response, String headerKey, String headerValue) {
        String header = response.getHeaders().getFirst(headerKey);
        Assert.assertNotNull(header);
        Assert.assertEquals(headerValue, header);
    }

    private void shouldReturnNotAllowedWithGetAllowHeader(ClientResponse response, String notAllowedMethod) {
        Assert.assertEquals(405, response.getStatus());
        Assert.assertFalse(response.getAllow().contains(notAllowedMethod));
        Assert.assertTrue(response.getAllow().contains(HttpMethod.GET));
    }

    protected void jsonResponseEquals(ClientResponse response, String end, String[][] values, String direction,
            String rotation) throws Exception {
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getType());

        JSONObject json = json(response);

        Assert.assertTrue(json.has(END));
        Assert.assertEquals(end, json.getString(END));

        Assert.assertTrue(json.has(DIRECTION));
        Assert.assertEquals(direction, json.getString(DIRECTION));

        Assert.assertTrue(json.has(ROTATION));
        Assert.assertEquals(rotation, json.getString(ROTATION));

        Assert.assertTrue(json.has(TABLE));

        JSONObject table = (JSONObject) json.get(TABLE);
        Assert.assertTrue(table.has(ROWS));

        JSONArray rows = (JSONArray) table.get(ROWS);
        for (int i = 0; i < rows.length(); i++) {
            JSONObject row = (JSONObject) rows.get(i);
            Assert.assertTrue(row.has(COLUMNS));

            JSONArray columns = (JSONArray) row.get(COLUMNS);
            for (int j = 0; j < columns.length(); j++) {
                JSONObject column = (JSONObject) columns.get(j);
                Assert.assertTrue(column.has(VALUE));

                String expectedValue = values[i][j];
                if (expectedValue.isEmpty()) {
                    expectedValue = "null"; // JSON stores nulls as strings
                }

                Assert.assertEquals(expectedValue, column.getString(VALUE));
            }
        }
    }

    /**
     * Implement this method to provide the resource to under test.
     */
    protected abstract WebResource tester();

}
