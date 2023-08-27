package com.caicai.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SubScriptEnum {
    Society("社会","336de9c11d6d11ee8a9a448a5b430767","Society"),
    Finance("财经","a49331321d6e11ee8a9a448a5b430767","Finance"),
    Entertainment("娱乐","ae9db4541d6e11ee8a9a448a5b430767","Entertainment"),
    Technology("科技","b5aeb00b1d6e11ee8a9a448a5b430767","Technology"),
    International("国际","bc19b6b01d6e11ee8a9a448a5b430767","International"),
    Domestic("国内","c30d1c521d6e11ee8a9a448a5b430767","Domestic"),
    Military("军事","da70b6561d6e11ee8a9a448a5b430767","Military"),
    ;

    String name;
    String code;
    String inner;


    //根据传入的name动态获取Code
    public static String getCodeByInner(String inner) {
        SubScriptEnum [] enums = SubScriptEnum .values();    //获取所有枚举集合
        for (SubScriptEnum  testEnum : enums) {
            if (testEnum.getInner().equals(inner)) {
                return testEnum.getCode();
            }
        }
        return null;
    }




}
