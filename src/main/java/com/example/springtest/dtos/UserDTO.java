package com.example.springtest.dtos;


import java.util.Date;
import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private Date created;
    private Date modified;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean is_active;
    private UUID roleUuid;
    private String image_url;

    public UserDTO() {
    }

    public UserDTO(UUID uuid, Date created, Date modified, String username, String password, String firstName, String lastName, boolean is_active, UUID roleUuid, String image_url) {
        this.uuid = uuid;
        this.created = created;
        this.modified = modified;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.is_active = is_active;
        this.roleUuid = roleUuid;
        this.image_url = image_url;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public UUID getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(UUID roleUuid) {
        this.roleUuid = roleUuid;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "uuid=" + uuid +
                ", created=" + created +
                ", modified=" + modified +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", is_active=" + is_active +
                ", roleUuid=" + roleUuid +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
