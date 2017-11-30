package generator;

import freemarker.FMTemplateFactory;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.GenProperty;
import model.GenTable;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: liumangafei
 * Date: 2014/10/25
 * Project Name: generator
 * Description:
 */
public class DaoGenerator implements Generator {

//    private static Logger logger = LoggerFactory.getLogger(ModelGenerator.class);

    private GenTable genTable = null;

    public DaoGenerator(GenTable genTable) {
        this.genTable = genTable;
    }

    public String getPackage() {
        return genTable.getDaoPackage();
    }

    /**
     * 获取到需要import的class的字符串
     *
     * @return
     */
    public List<String> getImportList() {
        List<GenProperty> genPropertyList = genTable.getGenPropertyList();

        if (genPropertyList != null && genPropertyList.size() > 0) {
            List<String> importList = new ArrayList<String>();

            String dateStr = null;
            String bigDecimalStr = null;
            for (GenProperty genProperty : genPropertyList) {
                if ("Date".equals(genProperty.getPropertyType()) && dateStr == null) {
                    dateStr = "java.util.Date";
                    importList.add(dateStr);
                } else if ("BigDecimal".equals(genProperty.getPropertyType()) && bigDecimalStr == null) {
                    bigDecimalStr = "java.math.BigDecimal";
                    importList.add(bigDecimalStr);
                }
            }

            return importList;
        }

        return null;
    }

    public String getClassName() {
        return genTable.getClassName();
    }

    public List<GenProperty> getPropertyList() {
        return genTable.getGenPropertyList();
    }

    @Override
    public void generateFile(Writer out) throws IOException, TemplateException {
        Template temp = FMTemplateFactory.getTemplate("dao.ftl");

        Map root = new HashMap();
        root.put("package", getPackage());
        root.put("importList", getImportList());
        root.put("tableName", genTable.getTableName());
        root.put("className", getClassName());
        temp.process(root, out);
    }

    @Override
    public void generateFile(String filePath) throws IOException, TemplateException {
        Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(filePath)));
        generateFile(out);
        out.flush();
    }

    @Override
    public void generateFile() throws IOException, TemplateException {
        generateFile(genTable.getDaoPath());
    }

    public GenTable getGenTable() {
        return genTable;
    }

    public void setGenTable(GenTable genTable) {
        this.genTable = genTable;
    }
}
