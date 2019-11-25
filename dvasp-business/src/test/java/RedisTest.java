import com.glsx.vasp.BusinessApplication;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class RedisTest {

    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    public RedisCacheUtils redisCacheUtil;

    @Test
    public void testRedis() throws Exception {
        redisCacheUtil.setCacheObject("1234578", "111345", Constants.REDIS_INITDATA_VALID_TIME, TimeUnit.DAYS);
        String a = (String) redisCacheUtil.getCacheObject( "1234578");
        System.out.println("+==="+a);
         StoreUser redisUser = (StoreUser) redisCacheUtil.getCacheObject(Constants.REDIS_USER_PHONE_KEY + "13975982642");
        System.out.println("*********"+redisUser.getPhone());

        //List<SysProvince> list = redisCacheUtil.getListByPrex(Constants.REDIS_PROVINCE_KEY);
        //System.out.println("*****"+list.get(0).getName());
        //SysProvince sysProvince = (SysProvince) redisCacheUtil.getCacheObject(Constants.REDIS_PROVINCE_KEY+"120000");
        //System.out.println("******"+sysProvince.getName());
    }
}
