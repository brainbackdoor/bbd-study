package core.di.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.google.common.util.concurrent.Service;

import core.annotation.Controller;

public class BeanScanner {
	private Reflections reflections = new Reflections("next.controller");

	public BeanScanner() {

	}

	public BeanScanner(Object packages) {
		this.reflections = new Reflections(packages);
	}

	public Map<Class<?>, Object> getController() {
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
		return instantiateControllers(annotated);
	}

	private Map<Class<?>, Object> instantiateControllers(Set<Class<?>> annotated) {
		Map<Class<?>, Object> mapper = new HashMap<>();
		annotated.stream().forEach(
				clazz -> Arrays.stream(clazz.getDeclaredMethods()).forEach(m -> wrappedMethodForPuttingClass(mapper, clazz)));
		return mapper;
	}

	private void wrappedMethodForPuttingClass(Map<Class<?>, Object> mapper, Class<?> clazz) {
		try {
			mapper.put(clazz, clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
