package ru.itis.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Visitor {
    private Long visitorId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    public Visitor(Long visitorId, String name, String email,String username,String password,String phoneNumber) {
        this.visitorId = visitorId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}