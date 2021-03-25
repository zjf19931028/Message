package com.future.message.java;

import com.future.message.util.ShowLogUtil;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/19 10:11
 * Description:
 */
public class Animal {
    private String color;
    public  void eat(){
        ShowLogUtil.info("动物吃饭");
    }

    public class Panda {
        public void eat() {
            ShowLogUtil.info("熊猫吃竹子");
        }
    }
}
