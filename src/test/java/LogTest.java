
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: batchsql2excel
 * @description:
 * @author: xie.zhr
 * @create: 2024-01-30 16:00
 **/

public class LogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void logTest(){

        LOGGER.info("测试日志");

    }
}
