package core.productcatalogcache.service;


import core.productcatalogcache.entity.User;

public interface UserService {
    User findByUsername(String username);
}
