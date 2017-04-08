package org.zezutom.web.jsf.ajax.rest.service;

import java.util.List;
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
import org.zezutom.web.jsf.ajax.rest.model.Composition;

@RunWith(Arquillian.class)
public class ComposerFacadeIT {

    @EJB
    private ComposerFacade composerFacade;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap
                .create(JavaArchive.class, "test.jar")
                .addPackage(ComposerFacade.class.getPackage())
                .addPackage(Composer.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsResource("test-data.sql", "META-INF/test-data.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }    
    
    @Test
    public void shouldCreateDbAndLoadData() {
        Assert.assertTrue(composerFacade.count() == 10);
        composerFacade.findAll().stream().forEach(composer -> {
            List<Composition> compositions = composer.getCompositions();
            Assert.assertNotNull(compositions);
            Assert.assertFalse(compositions.isEmpty());
        });
    }
    
    @Test
    public void shouldServePageOfResults() {
        List<Composer> firstPage = composerFacade.findRange(new int[] {0, 3});
        List<Composer> secondPage = composerFacade.findRange(new int[] {4, 7});
        List<Composer> thirdPage = composerFacade.findRange(new int[] {8, 11});

        assertPageOfResults(firstPage, 4);
        assertPageOfResults(secondPage, 4);
        assertPageOfResults(thirdPage, 2);               
    }
    
    @Test
    public void shouldOrderBySpecifiedFields() {
        // Top 3 by genre and year of birth
        List<Composer> composers = composerFacade.findAll("genre", "born").subList(0, 3);
        assertComposer(composers.get(0), "Baroque", 1653, "Corelli");
        assertComposer(composers.get(1), "Baroque", 1659, "Purcell");
        assertComposer(composers.get(2), "Baroque", 1678, "Vivaldi");
    }
    
    private void assertComposer(Composer composer, String genre, int born, String lastName) {
        Assert.assertNotNull(composer);        
        Assert.assertTrue(composer.getBorn() == born);
        Assert.assertEquals(genre, composer.getGenre());
        Assert.assertEquals(lastName, composer.getLastName());
    }
    
    private void assertPageOfResults(List<Composer> pageOfResults, int expectedSize) {
        Assert.assertNotNull(pageOfResults);
        Assert.assertTrue(pageOfResults.size() == expectedSize);
    }
}
