package com.training.ecommerce.config;

import com.training.ecommerce.model.Country;
import com.training.ecommerce.model.Product;
import com.training.ecommerce.model.ProductCategory;
import com.training.ecommerce.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        disableHttpSideEffectMethods(config, Product.class);
        disableHttpSideEffectMethods(config, ProductCategory.class);
        disableHttpSideEffectMethods(config, Country.class);
        disableHttpSideEffectMethods(config, State.class);
    }

    private void disableHttpSideEffectMethods(RepositoryRestConfiguration config, Class<?> classType) {
        HttpMethod[] httpSideEffectMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        config.getExposureConfiguration()
                .forDomainType(classType)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(httpSideEffectMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(httpSideEffectMethods));

        exposeEntityIds(config);
    }

    private void exposeEntityIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();
        for (EntityType entityType : entities) {
            entityClasses.add(entityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
