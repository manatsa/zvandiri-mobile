/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.util.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.UserRole;
import zw.org.zvandiri.business.domain.util.UserLevel;

/**
 *
 * @author tasu
 */
public class UserDTO implements Serializable{
    
    private String id;
    private Long version;
    private String firstName;
    private String lastName;
    private String userName;
    private UserLevel userLevel;
    private Province province;
    private District district;
    private String roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public static UserDTO getInstance(User user) {
        UserDTO item = new UserDTO();
        item.setId(user.getId());
        item.setVersion(user.getVersion());
        item.setFirstName(user.getFirstName());
        item.setLastName(user.getLastName());
        item.setUserName(user.getUserName());
        item.setUserLevel(user.getUserLevel());
        item.setDistrict(user.getDistrict());
        item.setProvince(user.getProvince());
        item.setRoles(getRolesString(user));
        return item;
    }
    
    private static String getRolesString(User user) {
        String result = "";
        if (user.getUserRoles() != null) {
            int position = 0;
            int size = user.getUserRoles().size();
            for (UserRole item : user.getUserRoles()) {
                result += item.getName();
                position++;
                if (position < size) {
                    result += ",";
                }
            }
        }
        return result;
    }
    
    public static List<UserDTO> getInstance(List<User> users) {
        List<UserDTO> items = new ArrayList<>();
        for(User user : users){
            items.add(getInstance(user));
        }
        return items;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", userLevel=" + userLevel + ", province=" + province + ", district=" + district + '}';
    }
    
}
