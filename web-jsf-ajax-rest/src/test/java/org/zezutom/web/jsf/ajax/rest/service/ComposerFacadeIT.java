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
    public void shouldFindAllResults() {
        assertFullResults(composerFacade.findAll());
    }
    
    @Test
    public void shouldSupportFuzzySearch() {
        final int[] range = new int[] {0, 10};
        // Should find 'Vivaldi' in the last name
        List<Composer> composers = composerFacade.findRange(range, "VIV");
        assertPageOfResults(composers, 1);
        Assert.assertTrue(composers.contains(composerFacade.find(7L)));
        
        // Should find all composers falling into the classical genre category
        composers = composerFacade.findRange(range, "claSSIc");
        assertPageOfResults(composers, 3);
        Assert.assertTrue(composers.contains(composerFacade.find(8L)));
        Assert.assertTrue(composers.contains(composerFacade.find(9L)));
        Assert.assertTrue(composers.contains(composerFacade.find(10L)));
    }

    @Test
    public void shouldNotFilterIfSearchTextIsInvalid() {
        final int[] range = new int[] {0, 10};
        assertFullResults(composerFacade.findRange(range, "  "));
        assertFullResults(composerFacade.findRange(range, " a  "));
        assertFullResults(composerFacade.findRange(range, null));
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
    
    private void assertFullResults(List<Composer> composers) {
        Assert.assertNotNull(composers);
        Assert.assertTrue(composers.size() == composerFacade.count());
    }
}
