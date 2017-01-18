package school.thoughtworks.pos.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CategoryResourceTest extends RootResourceTest {

    String basePath = "/categories";

    @Test
    public void should_return_all_categories() throws Exception {

        Response response = target(basePath).request().get();
        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);
        Gson gson = new GsonBuilder().create();

        String jsonStr = gson.toJson(result);
        assertThat(jsonStr, is("{\"totalCount\":5,\"items\":[{\"categoryUri\":\"categories/1\",\"name\":\"all\",\"id\":1},{\"categoryUri\":\"categories/2\",\"name\":\"fruits\",\"id\":2},{\"categoryUri\":\"categories/3\",\"name\":\"tools\",\"id\":3},{\"categoryUri\":\"categories/4\",\"name\":\"all-test\",\"id\":4},{\"categoryUri\":\"categories/5\",\"name\":\"success\",\"id\":5}]}"));
    }

    @Test
    public void should_return_category_by_Id_success() throws Exception {

        Response response = target(basePath + "/2").request().get();
        Map result = response.readEntity(Map.class);

        assertThat(response.getStatus(), is(200));

        assertThat(result.get("id"), is(2));
        assertThat(result.get("name"), is("fruits"));
        assertThat(result.get("categoryUri"), is("categories/2"));
    }

    @Test
    public void should_return_category_by_Id_null() throws Exception {
        Response response = target(basePath + "/3").request().get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void should_insert_category_success() throws Exception {
        Map data = new HashMap();
        data.put("name", "success");
        Entity entity = Entity.entity(data, MediaType.APPLICATION_JSON_TYPE);
        Response response = target(basePath).request().post(entity);
        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_delete_category_success() throws Exception {
        Response response = target(basePath + "/6").request().delete();
        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void should_update_category_success() throws Exception {
        Map data = new HashMap();
        Entity entity = Entity.entity(data, MediaType.APPLICATION_JSON_TYPE);
        Response response = target(basePath + "/4").queryParam("name","all-test").request().put(entity);
        assertThat(response.getStatus(), is(204));
    }
}