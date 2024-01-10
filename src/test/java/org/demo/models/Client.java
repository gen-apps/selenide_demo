package org.demo.models;

public record Client(long id,
                     String email,
                     String password,
                     String firstName,
                     String lastName,
                     int birthYear,
                     String phone) {
}
