package com.timsmeet.persistance.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class CustomUserTypesIntegrator implements Integrator {
	public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		// Register the custom user type mapping(s) with Hibernate.
		UtcTimestampType utcTimestampType = new UtcTimestampType();
		configuration.registerTypeOverride(utcTimestampType);
	}

	public void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
		// Nothing to do here.
	}

	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		// Nothing to do here.
	}
}
