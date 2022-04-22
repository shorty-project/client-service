package ru.geekbrains.clientservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 19.04.2022
 */

@Entity
@Table(name = "client_role")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    private String role_name;

}