package org.helpusdefend.model;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUser extends User{

    private static final String ID = RandomStringUtils.random(50, false, true);
    private static final String TOKEN = RandomStringUtils.random(50, true, true);



    public RandomUser() {
        super(ID, TOKEN);
    }


    }

