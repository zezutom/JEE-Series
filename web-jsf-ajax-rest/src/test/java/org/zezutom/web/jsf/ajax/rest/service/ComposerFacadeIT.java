package org.zezutom.web.jsf.ajax.rest.service;

import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.zezutom.web.jsf.ajax.rest.model.Composer;

@RunWith(Arquillian.class)
public class ComposerFacadeIT {

    @EJB
    private ComposerFacade composerFacade;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap
                .create(JavaArchive.class, "web-jsf-ajax-rest.jar")
                .addPackage(ComposerFacade.class.getPackage())
                .addPackage(Composer.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsResource("test-data.sql", "META-INF/data.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }    
    
    @Test
    public void shouldCreateDbAndLoadData() {
        Assert.assertTrue(composerFacade.count() == 1);
    }
}
