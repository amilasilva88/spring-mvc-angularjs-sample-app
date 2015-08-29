/*
 * Copyright 2015 Amila Silva (amilasilva88@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.munginlabs.mgmt.console.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.munginlabs.mgmt.console.model.User;

/**
 *
 * Repository class for the User entity
 *
 */
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * finds a user given its username
     *
     * @param username
     *            - the username of the searched user
     * @return a matching user, or null if no user found.
     */
    public User findUserByUsername(String username) {

        List<User> users = em.createNamedQuery(User.FIND_BY_USERNAME, User.class).setParameter("username", username)
                .getResultList();

        return users.size() == 1 ? users.get(0) : null;
    }

    /**
     *
     * save changes made to a user, or insert it if its new
     *
     * @param user
     */
    public void save(User user) {
        em.merge(user);
    }

    /**
     * checks if a username is still available in the database
     *
     * @param username
     *            - the username to be checked for availability
     * @return true if the username is still available
     */
    public boolean isUsernameAvailable(String username) {

        List<User> users = em.createNamedQuery(User.FIND_BY_USERNAME, User.class).setParameter("username", username)
                .getResultList();

        return users.isEmpty();
    }
}
