package com.b2me.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto { //Used to pass all data to service from User Entity

    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
