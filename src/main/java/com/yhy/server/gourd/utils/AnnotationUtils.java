package com.yhy.server.gourd.utils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2018-10-30 10:20
 * version: 1.0.0
 * desc   :
 */
public class AnnotationUtils {
    private static final String PROTOCOL_FILE = "file";
    private static final String PROTOCOL_JAR = "jar";
    private static final String CLASS_SUFFIX = ".class";
    private static final String PACKAGE_SEPARATOR = ".";

    private AnnotationUtils() {
        throw new UnsupportedOperationException("Can not instantiate utils class");
    }

    public static boolean isAnnotated(Class<?> clazz, Class<? extends Annotation> annotation) {
        return null != clazz && null != annotation && null != clazz.getAnnotation(annotation);
    }

    @SafeVarargs
    public static List<Class<?>> getClassList(Package pkg, Class<? extends Annotation>... annotations) {
        return getClassList(pkg.getName(), annotations);
    }

    @SafeVarargs
    public static List<Class<?>> getClassList(String packageName, Class<? extends Annotation>... annotations) {
        List<Class<?>> classList = new ArrayList<>();
        String packagePath = packageName.replace(PACKAGE_SEPARATOR, File.separator);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            Enumeration<URL> urls = loader.getResources(packagePath);
            URL url;
            String protocol;
            String dirPath;
            while (urls.hasMoreElements()) {
                url = urls.nextElement();
                if (null != url) {
                    protocol = url.getProtocol();
                    if (PROTOCOL_FILE.equals(protocol)) {
                        // 普通文件
                        dirPath = URLDecoder.decode(url.getFile(), "UTF-8");
                        loadClassListInPackage(packageName, dirPath, classList);
                    } else if (PROTOCOL_JAR.equals(protocol)) {
                        // jar包
                        loadClassListInJar(packageName, packagePath, url, classList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getClassList(classList, annotations);
    }

    @SafeVarargs
    public static List<Class<?>> getClassList(List<Class<?>> classList, Class<? extends Annotation>... annotations) {
        List<Class<?>> temp = new ArrayList<>(classList);
        classList = new ArrayList<>();

        if (null != annotations && annotations.length > 0) {
            classList = new ArrayList<>();
            Annotation[] tempAnnotations;
            List<Class<? extends Annotation>> annotationList;
            for (Class<?> clazz : temp) {
                tempAnnotations = clazz.getAnnotations();
                if (null != tempAnnotations) {
                    annotationList = new ArrayList<>();
                    for (Annotation annotation : tempAnnotations) {
                        annotationList.add(annotation.annotationType());
                    }
                    annotationList.retainAll(new ArrayList<>(Arrays.asList(annotations)));
                    if (annotationList.size() == annotations.length) {
                        classList.add(clazz);
                    }
                }
            }
        } else {
            classList.addAll(temp);
            temp = null;
        }
        return classList;
    }

    private static void loadClassListInPackage(String packageName, String dirPath, List<Class<?>> classList) {
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles(temp -> temp.isDirectory() || temp.getName().endsWith(CLASS_SUFFIX));
        if (null == files) {
            return;
        }

        String className;
        for (File file : files) {
            if (file.isDirectory()) {
                loadClassListInPackage(packageName + PACKAGE_SEPARATOR + file.getName(), file.getAbsolutePath(), classList);
            } else {
                className = file.getName().replace(CLASS_SUFFIX, "");
                try {
                    classList.add(Class.forName(packageName + PACKAGE_SEPARATOR + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void loadClassListInJar(String packageName, String packagePath, URL url, List<Class<?>> classList) {
        JarFile jar;
        try {
            jar = ((JarURLConnection) url.openConnection()).getJarFile();
            Enumeration<JarEntry> entries = jar.entries();
            JarEntry entry;
            String name;
            int index;
            String className;
            while (entries.hasMoreElements()) {
                entry = entries.nextElement();
                name = entry.getName();
                if (name.startsWith(File.separator)) {
                    name = name.substring(1);
                }
                if (name.startsWith(packagePath)) {
                    index = name.lastIndexOf(File.separator);
                    if (index != -1) {
                        packageName = name.substring(0, index).replace(File.separator, PACKAGE_SEPARATOR);
                        if (name.endsWith(CLASS_SUFFIX) && !entry.isDirectory()) {
                            className = name.substring(packageName.length() + 1, name.length() - CLASS_SUFFIX.length());
                            try {
                                classList.add(Class.forName(packageName + PACKAGE_SEPARATOR + className));
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
