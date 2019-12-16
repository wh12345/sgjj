
package com.hq.modules.sys.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 密码表单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

}
