package school.thoughtworks.pos.resource;

import org.apache.ibatis.session.SqlSession;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import school.thoughtworks.pos.App;
import school.thoughtworks.pos.mapper.CategoryMapper;
import school.thoughtworks.pos.mapper.ItemMapper;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RootResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable(TestProperties.DUMP_ENTITY);

        SqlSession session = App.getSession();
        ItemMapper itemMapper = session.getMapper(ItemMapper.class);
        CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);

        return new ResourceConfig().register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(itemMapper).to(ItemMapper.class);
                bind(categoryMapper).to(CategoryMapper.class);
                bind(session).to(SqlSession.class);
            }
        }).packages("school.thoughtworks.pos.resource");
    }

    @Test
    public void root_path_should_return_items_uri() throws Exception {
        Response response = target("/").request().get();
        assertThat(response.getStatus(), is(200));

        Map result = response.readEntity(Map.class);
        assertThat(result.get("items"), is("/items"));
    }


}