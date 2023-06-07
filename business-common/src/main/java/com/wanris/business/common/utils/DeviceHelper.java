package com.wanris.business.common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import com.wanris.business.common.constant.Globals;

import java.util.UUID;

/**
 * 获取设备信息
 */
public class DeviceHelper {
    private static String deviceId;

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getDeviceId() {
        if (deviceId != null) {
            return deviceId;
        }
        deviceId = PreferenceHelper.getInstance().getString(Globals.DEVICE_ID);
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }

        if (TextUtils.isEmpty(deviceId)) {
            deviceId = UUID.randomUUID().toString();
            PreferenceHelper.getInstance().put(Globals.DEVICE_ID, deviceId);
        }

        return deviceId;
    }

}
