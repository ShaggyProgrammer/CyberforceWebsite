package com.vitavehiculum.web.initializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.vitavehiculum.web.orm.User;
import com.vitavehiculum.web.service.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class UserInitializer extends Initializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInitializer.class);

    @Autowired
    CryptoService cryptoService;

    public void init(){
        userRepository.deleteAll();

        JsonNode users = config.get("users");

        ArrayNode admins = (ArrayNode) config.get("admins");
        List<String> adminlist = new ArrayList<>();
        admins.elements().forEachRemaining((ele) -> {
            adminlist.add(ele.asText());
        });

        Iterator<String> keyIterator = users.fieldNames();
        while(keyIterator.hasNext()){
            String user = keyIterator.next();
            String password = users.get(user).asText();

            User newUser = new User();
            newUser.setName(user);

            if (adminlist.contains(user)){
                newUser.setRole("admin");

            }
            else {
                newUser.setRole("user");
            }

            newUser.setSecret(cryptoService.hash(password.getBytes(StandardCharsets.UTF_8)));

            userRepository.save(newUser);
        }

    }
}
