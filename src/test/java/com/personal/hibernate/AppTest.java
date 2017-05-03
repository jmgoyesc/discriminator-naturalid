package com.personal.hibernate;

import com.personal.hibernate.model.Category;
import com.personal.hibernate.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertNotNull;

/**
 * Run hibernate testing natural-id and discriminator
 * Created by juan.goyes on 2017-05-02.
 */
public class AppTest {

	private SessionFactory sessionFactory;

	@Before
	public void setup(){
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
		createCategories();
		createEvents();
	}

	private void createCategories() {
		Session session = sessionFactory.openSession();

		IntStream.range(1, 20)
				.mapToObj(Category::new)
				.forEach(session::save);

		session.close();
	}

	private void createEvents() {
		Session session = sessionFactory.openSession();

		IntStream.range(1, 20)
				.mapToObj(Event::new)
				.forEach(session::save);

		session.close();
	}

	@Test
	public void test() {
		Session session = sessionFactory.openSession();

		Event event = session.byNaturalId(Event.class)
				.using("code", "1")
				.load();
		assertNotNull(event);
	}
}
