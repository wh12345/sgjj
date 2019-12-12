package com.hq.modules.wi.proxy;

import com.hq.modules.wi.entity.JavaSourceFromCodeString;
import com.qy.api.RequestApi;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Because of you on 2019/11/18.
 */
@Component
public class ApiProxy {
    /**
     *
     * @param fullClassName 完整文件名
     * @param codeFilePath 文件路径
     * @param charsetName 编码格式
     * @param buildOutput 输出路径
     * @return
     */
    public RequestApi newProxyInstance(String fullClassName, String codeFilePath, String charsetName, String buildOutput) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String code = FileUtils.readFileToString(FileUtils.getFile(codeFilePath),charsetName);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new JavaSourceFromCodeString(fullClassName, code));
        List<String> options = new ArrayList<String>();
        options.add("-classpath");
        StringBuilder sb = new StringBuilder();
        URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        for(URL url : urlClassLoader.getURLs()) {
            sb.append(url.getFile()).append(File.pathSeparator);
        }
        options.add(sb.toString());
        options.add("-d");
        options.add(buildOutput);
        boolean isok = compiler.getTask(null,fileManager,null,options,null,files).call();
        if(isok) {
            File root = new File(buildOutput);
            if(!root.exists()) {
                root.mkdirs();
            }
            URL[] urls = new URL[]{root.toURI().toURL()};
            ClassLoader classLoader = URLClassLoader.newInstance(urls);
            Class clazz = Class.forName(fullClassName,true,classLoader);
            Constructor constructor = clazz.getConstructor();
            return  (RequestApi) constructor.newInstance();
        }
        return null;
    }
}
