/*
 * Copyright 2015 Edward Zengeni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.UserRole;
import zw.org.zvandiri.business.domain.util.UserType;
import zw.org.zvandiri.business.util.IRepoConstant;

/**
 *
 * @author Edward Zengeni
 */
public interface UserRepo extends CrudRepository<User, String> {

    @Override
    public List<User> findAll();
    
    @Query("Select Distinct u from User u "+IRepoConstant.USER_CONSTANT+" where u.id =:id")
    public User findUser(@Param("id") String id);

    @Query("Select Distinct u from User u "+IRepoConstant.USER_CONSTANT+" where u.active=:active Order By u.userName ASC")
    public List<User> getOptAll(@Param("active") Boolean active);

    @Query("Select Distinct u from User u "+IRepoConstant.USER_CONSTANT+" where u.userName=:userName and u.active=:active")
    public User findByUserName(@Param("userName") String userName, @Param("active") Boolean active);
   
    @Query("Select Distinct u from User u "+IRepoConstant.USER_CONSTANT+" where u.userType in:userTypes")
    public List<User> findByUserType(@Param("userTypes") List<UserType> userTypes);
    
    @Query("Select Distinct u from User u "+IRepoConstant.USER_CONSTANT+" where u.userRoles in:userRoles")
    public List<User> findByUserRoles(@Param("userRoles") Set<UserRole> userRoles);
    
    @Query("Select Distinct u from User u "+IRepoConstant.USER_CONSTANT+" where (u.firstName LIKE :firstName% and u.lastName LIKE :lastName%) OR (u.firstName LIKE :lastName% and u.lastName LIKE :firstName%)")
    public List<User> findByNames(@Param("firstName") String firstName, @Param("lastName") String lastName);

    public List<User> findByUserNameLike(String string);
}
