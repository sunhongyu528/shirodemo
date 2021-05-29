package com.example.shirodemo.utils;


import com.example.shirodemo.entity.User;

import java.util.List;

public class UserPageUtils {

    public static List<User> pageBySubList(List list, int pagesize, int currentPage) {
        int totalcount = list.size();
        int pagecount = 0;
        List<User> subList;
        int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        if (m == 0) {
            subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
        } else {
            if (currentPage == pagecount) {
                subList = list.subList((currentPage - 1) * pagesize, totalcount);
            } else {
                subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
            }
        }

        return subList;
        }
    }
