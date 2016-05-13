package com.etone.atm;

/**
 * Created by Maggie on 2016/5/13.
 */
public class Test {
    private static Test ourInstance = new Test();

    public static Test getInstance() {
        return ourInstance;
    }

    private Test() {
    }
}
