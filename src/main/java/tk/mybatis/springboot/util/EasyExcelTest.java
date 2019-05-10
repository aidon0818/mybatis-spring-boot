package tk.mybatis.springboot.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.tomcat.util.file.ConfigFileLoader.getInputStream;

/**
 * @Auther: ld
 * @Date: 2019/3/13 17:08
 * @Param ${tags}
 * @Description:
 */
public class EasyExcelTest {
    public static void simpleReadListStringV2007() throws IOException {
        InputStream inputStream = getInputStream("D:\\cert\\aa.xls");
        try {
            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);

            excelReader.read(new Sheet(1, 2, LoanInfo.class));
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            simpleReadListStringV2007();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
