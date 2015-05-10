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
    
    public static final int DEFAULT_PAGE = 0;
    
    @Inject
    private ComposerRepository repository;
    
    public List<Composer> getComposers(Integer page) {
        return repository.findAll(page == null ? DEFAULT_PAGE : page);
    }
}
