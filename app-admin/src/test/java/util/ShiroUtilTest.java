package util;

import com.jtech.marble.util.PasswordUtil;
import com.xiaoleilu.hutool.crypto.SecureUtil;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class ShiroUtilTest {

    @Test
    public void createPassword() throws Exception {
        final byte[] randomSaltBytes = PasswordUtil.generateSalt();
        final String md5password = SecureUtil.md5("123456");
        final String password = PasswordUtil.password(randomSaltBytes, md5password);
        final String randomSalt = PasswordUtil.salt(randomSaltBytes);
        System.out.println("password = " + password);
        System.out.println("randomSalt = " + randomSalt);
    }


    @Test
    public void testCheckPassword() throws Exception {
        final String md5password = SecureUtil.md5("123456");
        final boolean password = PasswordUtil.checkPassword(StringUtils.upperCase("ef0bd733a7a87a6c"),
                "0668088699c11966732188699f7b03d0bf7b69a8", md5password);
        Assert.assertTrue(password);
    }

    @Test
    public void testProps() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2017);
        calendar.set(Calendar.MONTH,11);
        calendar.set(Calendar.DAY_OF_MONTH,23);
        System.out.print(calendar.getTime().getTime());
    }
}
