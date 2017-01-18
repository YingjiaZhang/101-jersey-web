package school.thoughtworks.pos.resource;

import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CategoryResourceTest extends RootResourceTest {

    @Test
    public void root_path_should_return_items_uri() throws Exception {
        Response response = target("/").request().get();
        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("items"), is("/items"));
    }

    @Test
    public void should_return_item_by_Id_success() throws Exception {
        Response response = target("/items/2").request().get();
        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("id"), is(2));
        assertThat(result.get("name"), is("apple"));
        assertThat(result.get("price"), is(10.0));
    }

    @Test
    public void should_return_item_by_Id_failure() throws Exception {
        Response response = target("/items/0").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_insert_item_success() throws Exception {
        Map data  = new HashMap();
        data.put("name","success");
        data.put("price",13.1);
        Entity entity = Entity.entity(data, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/items").request().post(entity);
        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_delete_item_success() throws Exception {
        Response response = target("/items/3").request().delete();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void should_update_item_success() throws Exception {
        Map data  = new HashMap();
        data.put("name","update");
        data.put("price",13.1);

        Entity entity = Entity.entity(data, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/items/1").request().put(entity);
        assertThat(response.getStatus(), is(204));
    }


}