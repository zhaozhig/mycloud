package com.example.getway.tools;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.HashSet;
import java.util.Set;


@Component
@Data
public class MgtWhiteList {
    private AntPathMatcher pathMatcher;

    private Set<String> openUrlSet;
    private Set<String> authUrlSet;

    public MgtWhiteList() {
        pathMatcher = new AntPathMatcher();

        initOpenUrlSet();

        initAuthUrlSet();
    }

    private void openSwagger(boolean flag) {
        if (flag) {
            openUrlSet.add("/**/swagger*/**");
            openUrlSet.add("/**/v2/**");
            openUrlSet.add("/**/webjars/**");
            openUrlSet.add("/**/csrf/**");

        }
    }

    /**
     * 初始化开放 url 规则
     */
    public void initOpenUrlSet() {
        openUrlSet = new HashSet<>();

        openUrlSet.add("/auth-service/**");
        openUrlSet.add("/**/anon/**");

        openSwagger(true);

    }

    /**
     * 初始化仅需登录权限 url 规则
     */
    public void initAuthUrlSet() {
        authUrlSet = new HashSet<>();

        authUrlSet.add("/**/auth/**");

    }

    /**
     * 是否是开放url
     *
     * @param url
     * @return
     */
    public boolean isOpenUrl(String url) {
        boolean result = false;

        if (StringUtils.isNotBlank(url)) {
            for (String urlPattern : openUrlSet) {
                result = pathMatcher.match(urlPattern, url);

                if (result) {
                    break;
                }

            }
        }

        return result;
    }

    /**
     * 是否是仅需登录权限 url
     *
     * @param url
     * @return
     */
    public boolean isAuthUrl(String url) {
        boolean result = false;

        if (StringUtils.isNotBlank(url)) {
            for (String urlPattern : openUrlSet) {
                result = pathMatcher.match(urlPattern, url);

                if (result) {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String testUrl = "/swagger/saws.html";

        MgtWhiteList wl = new MgtWhiteList();

        boolean b = wl.isOpenUrl(testUrl);

        System.out.println(b);

    }

}
