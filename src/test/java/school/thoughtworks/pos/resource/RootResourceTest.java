package school.thoughtworks.pos.resource;

import org.apache.ibatis.session.SqlSession;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import school.thoughtworks.pos.App;
import school.thoughtworks.pos.mapper.CategoryMapper;
import school.thoughtworks.pos.mapper.ItemMapper;

import javax.ws.rs.core.Application;

public class RootResourceTest extends JerseyTest {
//
//    protected SqlSessionManager sqlSessionManager = mock(SqlSessionManager.class);
//    protected ItemMapper itemMapper = mock(ItemMapper.class);
//    protected CategoryMapper categoryMapper = mock(CategoryMapper.class);

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


}