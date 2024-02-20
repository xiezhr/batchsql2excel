import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @program: batchsql2excel
 * @description:
 * @author: xie.zhr
 * @create: 2024-01-31 17:30
 **/
public class PathTest {

    @Test
    public void testPath(){
        String sqlpath =  FileUtil.normalize(System.getProperty("user.dir")+"\\basefile\\查询sql.txt");
        String excelpath = FileUtil.normalize(System.getProperty("user.dir")+"\\basefile\\替换信息.xlsx");
        boolean exists = FileUtil.exist(sqlpath);
        System.out.println(exists);
        String content = FileUtil.readUtf8String(sqlpath);
        System.out.println(content);

        ExcelReader reader = ExcelUtil.getReader(excelpath);
        List<Map<String, Object>> readAll = reader.readAll();
        readAll.get(0).keySet();
        System.out.println(readAll);
    }
}
