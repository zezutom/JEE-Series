package org.zezutom.web.jsf.ajax.rest.service;

import java.util.Arrays;
import java.util.Iterator;
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
import org.zezutom.web.jsf.ajax.rest.model.OrderRule;

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
        // Should find 'Vivaldi' in the last name        
        List<Composer> composers = composerFacade.findRange(getRange(), "VIV");
        assertPageOfResults(composers, 1);
        Assert.assertTrue(composers.contains(composerFacade.find(7L)));
        
        // Should find all composers falling into the classical genre category
        composers = composerFacade.findRange(getRange(), "claSSIc");
        assertPageOfResults(composers, 3);
        Assert.assertTrue(composers.contains(composerFacade.find(8L)));
        Assert.assertTrue(composers.contains(composerFacade.find(9L)));
        Assert.assertTrue(composers.contains(composerFacade.find(10L)));
    }

    @Test
    public void shouldSupportFuzzySearchWithCustomSorting() {               
        // by lastname
        assertSortBy("LL", 
                Arrays.asList(new OrderRule("lastName", true)), 
                "Cavalli", "Corelli", "Purcell");
        
        // by lastname desc
        assertSortBy("LL", 
                Arrays.asList(new OrderRule("lastName", false)), 
                "Purcell", "Corelli", "Cavalli");
        
        // by genre, lastname
        assertSortBy("LL", 
                Arrays.asList(new OrderRule("genre", true), new OrderRule("lastName", true)), 
                "Corelli", "Purcell", "Cavalli");
        
        // by genre desc, lastname
        assertSortBy("LL", 
                Arrays.asList(new OrderRule("genre", false), new OrderRule("lastName", true)), 
                "Cavalli", "Corelli", "Purcell");        
        
        // by genre desc, lastname desc
        assertSortBy("LL", 
                Arrays.asList(new OrderRule("genre", false), new OrderRule("lastName", false)), 
                "Cavalli",  "Purcell", "Corelli");                
    }

    private void assertSortBy(String query, List<OrderRule> orderRules, String... lastnames) {
        List<Composer> composers = composerFacade.findRange(getRange(), query, orderRules);
        assertPageOfResults(composers, lastnames.length);  // Cavalli, Corelli, Purcell
        Iterator<Composer> it = composers.iterator();
        for (String lastname : lastnames) {
            final Composer composer = it.next();
            Assert.assertNotNull(composer);
            Assert.assertTrue(lastname.equals(composer.getLastName()));            
        }        
    }
    
    @Test
    public void shouldNotFilterIfSearchTextIsInvalid() {
        int[] range = getRange();
        assertFullResults(composerFacade.findRange(range, "  "));
        assertFullResults(composerFacade.findRange(range, " a  "));
        assertFullResults(composerFacade.findRange(range, null));
    }
            
    private void assertPageOfResults(List<Composer> pageOfResults, int expectedSize) {
        Assert.assertNotNull(pageOfResults);
        Assert.assertTrue(pageOfResults.size() == expectedSize);
    }
    
    private void assertFullResults(List<Composer> composers) {
        Assert.assertNotNull(composers);
        Assert.assertTrue(composers.size() == composerFacade.count());
    }
    
    private int[] getRange() {
        return new int[] {0, 10};
    }
}
