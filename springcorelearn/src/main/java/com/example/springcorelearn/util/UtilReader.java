package com.example.springcorelearn.util;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.springcorelearn.domain.Player;
import com.example.springcorelearn.domain.User;

import lombok.Data;

@Data
@Configuration
//@PropertySource("classpath:init.properties")
@ConfigurationProperties("app")
public class UtilReader {
 private User user;
//	private List<User> user;
}
