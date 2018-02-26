package sample_app.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import sample_app.resources.PumpsResource;

public class SampleAppApplication extends Application {


	public SampleAppApplication() {
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(MOXyJsonProvider.class);
		classes.add(PumpsResource.class);

		return classes;
	}
}
