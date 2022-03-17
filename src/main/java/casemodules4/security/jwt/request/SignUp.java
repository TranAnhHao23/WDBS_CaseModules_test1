package casemodules4.security.jwt.request;

import java.util.Set;

public class SignUp {
    private String fullName;
    private String phoneNumber;
    private String address;

    private String account;
    private String email;
    private String password;
    private Set<String> roles;

    public SignUp() {
    }

    public SignUp(String fullName, String phoneNumber, String address, String account, String email, String password, Set<String> roles) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.account = account;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
