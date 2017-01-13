package school.thoughtworks.pos;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import school.thoughtworks.pos.mapper.ItemMapper;

import javax.ws.rs.ApplicationPath;
import java.io.InputStream;

@ApplicationPath("resources")
public class App extends ResourceConfig {

    public App() {
        SqlSession session = getSession();

        final ItemMapper itemMapper = session.getMapper(ItemMapper.class);

        packages("school.thoughtworks.pos.resource")
                .register(new AbstractBinder() {

                    @Override
                    protected void configure() {
                        bind(itemMapper).to(ItemMapper.class);
                        bind(session).to(SqlSession.class);
                    }
                });
    }

    public static SqlSession getSession() {
        String resource = "mybites/mybatis-config.xml";
        SqlSession session = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return session;
    }
}
