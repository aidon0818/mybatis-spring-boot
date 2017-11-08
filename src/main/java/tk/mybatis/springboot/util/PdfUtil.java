package tk.mybatis.springboot.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Dong_Liu
 * date：2017/11/8
 * 生产简单的PDF文件
 * 源码：http://git.oschina.net/lujianing/java_pdf_demo
 */
public class PdfUtil {
    private static final String DEST = "target/HelloWorld.pdf";

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        document.add(new Paragraph("hello world"));
        document.close();
        writer.close();
    }
}
