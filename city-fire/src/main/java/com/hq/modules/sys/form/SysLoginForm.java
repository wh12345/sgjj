
package com.hq.modules.sys.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录表单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
