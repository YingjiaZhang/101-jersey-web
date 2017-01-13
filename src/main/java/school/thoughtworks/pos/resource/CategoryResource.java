package school.thoughtworks.pos.resource;

import org.apache.ibatis.session.SqlSession;
import school.thoughtworks.pos.bean.Category;
import school.thoughtworks.pos.mapper.CategoryMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/categorys")
public class CategoryResource {

    @Inject
    private SqlSession session;

    @Inject
    private CategoryMapper categoryMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCategory(Map data) {
        String name = (String) data.get("name");

        Category category = new Category();
        category.setName(name);
        categoryMapper.insertCategory(category);
        session.commit();

        Integer id = category.getId();
        Map result = new HashMap();
        result.put("categoryUri", "categoty/" + id);

        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategoryById(@PathParam("id") Integer id) {
        categoryMapper.deleteCategoryById(id);
        session.commit();

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(
            @PathParam("id") Integer id, @QueryParam("name") String name) {
        Category category = new Category();
        category.setName(name);
        category.setId(id);

        categoryMapper.updateCategory(category);
        session.commit();

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCategoryId(
            @PathParam("id") Integer id) {
        return Response.status(Response.Status.OK)
                .entity(categoryMapper.findCategoryById(id).toMap())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response findAll(){
        List<Category> originCategories = categoryMapper.findAll();

        List<Map>  categories = originCategories
                .stream()
                .map(category -> category.toMap())
                .collect(Collectors.toList());

        Map result  = new HashMap();
        result.put("items",categories);
        result.put("totalCount", categories.size());

        return Response.status(Response.Status.OK)
                .entity(result).build();
    }


}