package sample_app.resources;

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import sample_app.dao.PumpDao;
import sample_app.dto.PumpDto;
import sample_app.dto.PumpDto2;
import sample_app.dto.PumpsDto;
import sample_app.model.Pump;

@Path("pumps")
public class PumpsResource {

	private PumpDao pumpDao = new PumpDao();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public PumpsDto getPumps() {
		final List<Pump> findAll = pumpDao.findAll();

		Type targetListType = new TypeToken<List<PumpDto>>() {
		}.getType();
		List<PumpDto> pumpsDto = new ModelMapper().map(findAll, targetListType);

		PumpsDto result = new PumpsDto();
		result.setPumps(pumpsDto);

		return result;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createPump(PumpDto pumpDto) {
		Pump pump = new Pump();
		pump.setNumber(pumpDto.getNumber());

		pumpDao.create(pump);
	}

	@GET
	@Path("{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public PumpDto getPump(@PathParam(value = "number") String number) {
		Pump findByNumber = pumpDao.findByNumber(number);

		Type targetListType = new TypeToken<PumpDto>() {
		}.getType();
		PumpDto pumpDto = new ModelMapper().map(findByNumber, targetListType);

		return pumpDto;
	}

	@DELETE
	@Path("/delete/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletePump(@PathParam(value = "number") String number) {
		pumpDao.delete(number);
	}

	@GET
	@Path("/pump2/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPump2(@PathParam(value = "number") String number) {
		Pump pump2 = pumpDao.findByNumber(number);
		return Response.status(Response.Status.OK).entity(new PumpDto2(pump2)).build();
	}
}
