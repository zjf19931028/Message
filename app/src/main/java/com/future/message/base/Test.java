package com.future.message.base;

import com.future.message.util.ShowLogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/26 15:30
 * Description:
 */
public class Test {
    public static void test(Class<?> clazz) {
        try {
            Object obj = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
            for (Method method : clazz.getDeclaredMethods()) {
                ShowLogUtil.info("method="+method.getName());
                HelloWorld helloWorld = method.getAnnotation(HelloWorld.class);
                if (helloWorld != null) {
                    String name = helloWorld.name();
                    ShowLogUtil.info(name);
                    method.invoke(obj, name);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
