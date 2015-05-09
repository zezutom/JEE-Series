package org.zezutom.blog.series.jee.ajaxrest.service;

import org.zezutom.blog.series.jee.ajaxrest.model.Composer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author tom
 */
@Stateless
public class ComposerService {
    
    @Inject
    private ComposerRepository repository;
    
    public List<Composer> getComposers() {
        return repository.findAll();
    }
}
