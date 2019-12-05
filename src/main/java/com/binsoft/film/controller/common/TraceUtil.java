package com.binsoft.film.controller.common;


import lombok.AllArgsConstructor;
import lombok.Data;

public final class TraceUtil {
    private TraceUtil() {
    }

    private static ThreadLocal<LocalUser> threadLocal = new ThreadLocal();

    public static void initThread(String userId,String userName){
        LocalUser user = new LocalUser(userId,userName);
        threadLocal.set(user);
    }
    public static String getUserId() {
        LocalUser user = threadLocal.get();
        return user.getUserId();
    }

    public static String getUserName(){
        LocalUser user = threadLocal.get();
        return user.getUserName();
    }
}


@Data
@AllArgsConstructor
 class LocalUser{
    private String userId;
    private String userName;

}

