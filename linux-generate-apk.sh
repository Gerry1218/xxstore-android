#!/bin/bash
#进入当前脚本所在目录
cd $(dirname $0);
clear
echo --------------------- start -----------------------
echo
echo
echo ######################请选择运行环境###################
echo ----------------------0: 正式环境----------------------
echo ----------------------1: 测试环境--------------------
echo
echo

#处理输入结果
readChooseUtlType(){
    read -p "请选择要执行的操作:" urlType
    if [[ "${urlType}" = "0" ]];then
        echo "您选择了【正式环境】"
        modifyUrl "${urlType}"
    elif [[ "${urlType}" = "1" ]];then
        echo "您选择了【测试环境】"
        modifyUrl "${urlType}"
    else
        echo "您输入的有误,请重新输入"
        readChooseUtlType
    fi
}

#转换/统一系统名称
#参数：1系统名称
#例子：
#    获取并转换
#    xrsh_cvt_osname `xrsh_get_osname`
#    如在 linux 下系统名称通常为 Linux，转换后为 linux
#注解：不在函数内处理的系统名称，不做转换
cvt_os_name(){
    _tmp=$1
    case ${_tmp} in
      Linux)        _tmp=linux   _result=1   ;;
      SunOS)        _tmp=sun     _result=2   ;;
      AIX)          _tmp=aix     _result=3   ;;
      HP-UX)        _tmp=hpux    _result=4   ;;
      Darwin)       _tmp=osx     _result=5   ;;
      FreeBSD)      _tmp=freebsd _result=6   ;;
      QNX)          _tmp=qnx     _result=7   ;;
      *)        ;;
    esac

    echo ${_result}
}

#修改环境配置文件
modifyUrl(){
    name=`uname`
    cvName=`cvt_os_name ${name}`

    if [[ "${cvName}" = "1" ]];then
        echo 当前系统\：linux
        sed -i "s/serverUrlType=0/serverUrlType=$1/g" build.properties
        sed -i "s/serverUrlType=1/serverUrlType=$1/g" build.properties
    elif [[ "${cvName}" = "5" ]];then
        echo 当前系统\：osx
        LC_CTYPE=C
        sed -i '' "s/serverUrlType=0/serverUrlType=$1/g" build.properties
        sed -i '' "s/serverUrlType=1/serverUrlType=$1/g" build.properties
    fi
    chooseBuildType
}

# 选择编译类型
chooseBuildType(){
    read -p "请输入编译类型（d 表示 debug；r 表示 release）:" buildtype
    if [[ "${buildtype}" = "d" ]];then
        echo "即将编译debug版本"
        startBuildDebug
    elif [[ "${buildtype}" = "D" ]];then
        echo "即将编译debug版本"
        startBuildDebug
    elif [[ "${buildtype}" = "r" ]];then
        echo "即将编译release版本"
        startBuildRelease
    elif [[ "${buildtype}" = "R" ]];then
        echo "即将编译release版本"
        startBuildRelease
    else
        echo "您输入的有误,请重新输入"
        chooseBuildType
    fi
}
# 开始编译Debug版本
startBuildDebug(){
    gradle app:assembleDebug
    echo "输出路径:./app/build/outputs/apk/debug/"
}

# 开始编译release版本
startBuildRelease(){
    gradle app:assembleRelease
    echo "输出路径:./app/build/outputs/apk/release/"
}

# 执行脚本
readChooseUtlType







