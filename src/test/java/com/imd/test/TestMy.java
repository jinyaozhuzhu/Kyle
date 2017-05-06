package com.imd.test;


import org.junit.Test;

import java.net.URL;

/**
 *
 * Created by jinyao on 2017/4/17.
 */
public class TestMy {

    @Test
    public void currentPath() {
        String path1 = this.getClass().getClassLoader().getResource("").toString();
        URL path2 = this.getClass().getResource("");

        System.out.println("path1 = "+path1);
        System.out.println("path1 = "+path2);
    }

}
