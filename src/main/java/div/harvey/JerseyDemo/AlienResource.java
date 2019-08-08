package div.harvey.JerseyDemo;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

	AlienRepository repo = new AlienRepository();

//	@GET
//	@Path("/id")
//	@Produces(MediaType.APPLICATION_ATOM_XML)
	/* http://localhost:8080/JerseyDemo/webapi/aliens/id?id=102 */
	/* http://shinko15.blogspot.com/2012/09/jersey-queryparam-matrixparam.html */
//	public Alien getAlien(@QueryParam("id") int id) {
//		return repo.getAlien(id);
//	}

	@GET
	@Path("alien/{id}") // http://localhost:8080/JerseyDemo/webapi/aliens/alien/101
	@Produces({ MediaType.APPLICATION_JSON/* , MediaType.APPLICATION_XML */ })
	public Alien getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Alien> getAliens() {
		System.out.println("getAlien called...");
//		Alien a1 = new Alien();
//		a1.setName("Navin");
//		a1.setPoint(60);
//
//		Alien a2 = new Alien();
//		a2.setName("Arati");
//		a2.setPoint(70);
//
//		List<Alien> aliens = Arrays.asList(a1, a2);
//		return aliens;
		/**/
		return repo.getAliens();
		/**/
	}

	@POST
	@Path("alien")
//	@Consumes(MediaType.APPLICATION_XML) // 描述只接受XML或JSON
	// @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a1) {
		System.out.println(a1);
		repo.create(a1);
		return a1;
	}

	@POST
	@Path("aliens") // http://localhost:8080/JerseyDemo/webapi/aliens/aliens
	// @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> createAlien(List<Alien> al) {
//		List<Alien> aliens;
		for (Alien a : al) {
			System.out.println(a);
			repo.create(a);
		}
		return al;
	}

	@PUT
	@Path("alien")
	public Alien updateAlien(Alien a1) {
		System.out.println(a1);
		/* 如果修改的資料沒有就新增一筆，可以這樣做，但是如何驗證getAlien是null是個問題 */
//		if (repo.getAlien(a1.getId()).getId() == 0) {
//			repo.create(a1);
//		} else {
//			repo.update(a1);
//		}
		repo.update(a1);
		return a1;
	}

	@DELETE
	@Path("alien/{id}")
	public Alien deleteAlien(@PathParam("id") int id) {
		Alien a = repo.getAlien(id);
		if (a.getId() != 0)
			repo.delete(id);
		return a;
	}
}
