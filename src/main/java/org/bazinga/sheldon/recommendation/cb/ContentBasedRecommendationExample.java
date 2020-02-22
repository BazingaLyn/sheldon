package org.bazinga.sheldon.recommendation.cb;

import org.bazinga.sheldon.utils.SimpleFileUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * 基于内容的推荐示例
 */
public class ContentBasedRecommendationExample {


    public void dataProcess() throws IOException {
        System.out.println("开始转换用户数据(users.dat)...");
        processUserData("data.m1-1m/users.dat");
    }

    private void processUserData(String userDataPath) throws IOException {
        List<String> eachLineFromFile = SimpleFileUtils.getEachLineFromFile(userDataPath);
        for(String eachLine:eachLineFromFile){
            String[] split = StringUtils.split(eachLine, "::");

        }


    }


}
