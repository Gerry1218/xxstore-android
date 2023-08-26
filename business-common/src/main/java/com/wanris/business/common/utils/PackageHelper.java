package com.wanris.business.common.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dalvik.system.DexFile;

public class PackageHelper {

    /**
     * 获取当前apk下的所有类
     * @param context
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    List<String> getSourcePaths(Context context) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        List<String> sourcePaths = new ArrayList<>();
        sourcePaths.add(applicationInfo.sourceDir);
        if (applicationInfo.splitSourceDirs != null) {
            sourcePaths.addAll(Arrays.asList(applicationInfo.splitSourceDirs));
        }
        return sourcePaths;
    }

    /**
     * @brief 获取指定包名下的类名
     * @param context
     * @param packageName
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    Set<String> getClassNamesByPackageName(Context context, String packageName) throws PackageManager.NameNotFoundException {
        Set<String> classNames = new HashSet<>();
        List<String> sourcePaths = getSourcePaths(context);
        for (String path : sourcePaths) {
            DexFile dexFile = null;
            try {
                dexFile = new DexFile(path);
                Enumeration<String> dexEntries = dexFile.entries();
                while (dexEntries.hasMoreElements()) {
                    String className = dexEntries.nextElement();
                    if (className.startsWith(packageName)) {
                        classNames.add(className);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    dexFile.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return classNames;
    }
}
