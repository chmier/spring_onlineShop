package com.example.projektKoncowy.configuration;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.function.IntPredicate;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@ConfigurationProperties(prefix = "users")
@Configuration
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConfiguration {


    private List<String> usernames;
    private List<String> passwords;



    @AssertTrue
    public boolean isUserCorrect(){
        return usernames.size() == passwords.size()
                && new HashSet<>(usernames).size() == usernames.size()
                && usernames.stream().allMatch(user->nonNull(user) && user.length() >= 2)
                && passwords.stream().allMatch(this::isPasswordCorrect);
    }


    @AssertTrue
    public boolean isPasswordCorrect(final String password) {
        if (isNull(password)) {
            return false;
        }
        return password.length() >= 8 && passwordASatisfiesPredicate(password, Character::isDigit)
                && passwordASatisfiesPredicate(password, Character::isUpperCase)
                && passwordASatisfiesPredicate(password, Character::isLowerCase);

    }

    private boolean passwordASatisfiesPredicate(final String password, final IntPredicate intPredicate) {
        return password.chars().anyMatch(intPredicate);


    }


}
